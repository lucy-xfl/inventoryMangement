package com.sta;

import java.awt.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.tool.Tool;

public class InOutTimeSeriesChart2 {

	ChartPanel frame1;
	
	public InOutTimeSeriesChart2(String supname,String sto) {//传递过来的是supnam 和判读入库 出库的字符串
		
		XYDataset xydataset = createDataset(supname,sto);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("仓库流动曲线图", "日期", "数量",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        frame1=new ChartPanel(jfreechart,true);
        
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        
		
	}

	private XYDataset createDataset(String supname, String sto) {//第一个参数传入一个 供应商的名字 ，传入数据库的名字
		// TODO Auto-generated method stub
		 String sqlstr="select DISTINCT stockname from "+sto +" where supname=? ";//根据传入不同参数来查找不同的库  查找商品的名称
		 String data[]=new String[1];
		 data[0]=supname;
		 ResultSet rs = Tool.showData(sqlstr, data);
		 TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();//初始曲线图
		
		 try {
			 
			while(rs.next()) {
				String sun=rs.getString(1);//读取某个供应商的产品名称
				TimeSeries timeseries = new TimeSeries(sun,
			                org.jfree.data.time.Day.class);//添加曲线名称和曲线  
				//向曲线添加数据
				String data1[]=new String[1];
				data1[0]=supname;
				 if(sto.equals("instock")) {
					 sqlstr="select intime from instock   where supname=? ORDER BY intime desc LIMIT 0 , 1";//读取最早的时间
				 }else {
					 sqlstr="select outtime from outstock   where supname=? ORDER BY outtime desc LIMIT 0 , 1";//读取最早的时间
				 }
				 // 查找最早时间和最晚时间
				 ResultSet rs1 = Tool.showData(sqlstr, data1);
				 String afterData;//最晚时间
				 String beforeData;//最早时间
				 if(rs1.next()) {
					 
					 afterData= rs1.getString(1);
					 
				 }else {
		
					 afterData="2019-01-08 00:00:00";
				 }
				 rs1.close();
				 //查找最早时间2018 
				 if(sto.equals("instock")) {
					 sqlstr="select intime from instock   where supname=? ORDER BY intime asc LIMIT 0 , 1";//读取最早的时间
				 }else {
					 sqlstr="select outtime from outstock   where supname=? ORDER BY outtime asc LIMIT 0 , 1";//读取最早的时间
				 }
				 
				 rs1 = Tool.showData(sqlstr, data1);
				 if(rs1.next()) {
					 
					 beforeData= rs1.getString(1);
					 
				 }else {
		
					 beforeData="2019-01-08 00:00:00";
				 }
				 rs1.close();
				 //最早的时间和最晚的时间都获取到了
				// getSpecifiedDayBeforeTthree(String specifiedDay) {
				 beforeData= getSpecifiedDayAfter(getSpecifiedDayBefore(beforeData));
				 afterData= getSpecifiedDayAfter(getSpecifiedDayBefore(afterData));
				
				 beforeData=getSpecifiedDayBeforeTthree(afterData);
				
				 // 2021 8 8 0时0分0秒    2021 8 8 23 59 59 
				 while(!afterData.equals(beforeData)) {
					 
					 if(sto.equals("instock")) {
						 sqlstr="select stockname ,sum(num) from instock where  supname=? and intime>=? and intime<=? and   stockname=?";
					 }else {
						 sqlstr="select stockname ,sum(num) from outstock where  supname=? and outtime>=? and outtime<=? and   stockname=?";
					 }
					 
					 String[] dasun=new String [4];
					 
					 dasun[0]=supname;
					 dasun[1]=beforeData;
					 dasun[2]=afterData;
					 dasun[3]=rs.getString("stockname");
					 rs1=Tool.showData(sqlstr,dasun);
					 	while(rs1.next()) {
						 try {
							 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					           Date date = sf.parse(beforeData);
					          Calendar calendar = Calendar.getInstance();
					            calendar.setTime(date);
					           
					        int year=   calendar.get(Calendar.YEAR);
					        int month= calendar.get(Calendar.MONTH) + 1;
					        int day1=  calendar.get(Calendar.DAY_OF_MONTH);
					        Day day = new Day(day1,month,year);
					        timeseries.add(day, rs1.getFloat(2));
					        
					        
			
					        } catch (ParseException e) {
					            e.printStackTrace();
					        }
				
					 }
					 rs1.close();
					 beforeData= getSpecifiedDayAfter(beforeData);//天数向后移动一天

					 
				 }
				 
				 if(afterData.equals(beforeData)) {
					 
					 if(sto.equals("instock")) {
						 sqlstr="select stockname ,sum(num) from instock where  supname=? and intime>=? and intime<=? and   stockname=?";
					 }else {
						 sqlstr="select stockname ,sum(num) from outstock where  supname=? and outtime>=? and outtime<=? and   stockname=?";
					 }
					 
					 String[] dasun=new String [4];
					 
					 dasun[0]=supname;
					 dasun[1]=beforeData;
					 dasun[2]=afterData;
					 dasun[3]=rs.getString("stockname");
					 rs1=Tool.showData(sqlstr,dasun);
					 	while(rs1.next()) {
						 try {
							 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					           Date date = sf.parse(beforeData);
					          Calendar calendar = Calendar.getInstance();
					            calendar.setTime(date);
					           
					        int year=   calendar.get(Calendar.YEAR);
					        int month= calendar.get(Calendar.MONTH) + 1;
					        int day1=  calendar.get(Calendar.DAY_OF_MONTH);
					        Day day = new Day(day1,month,year);
					        timeseries.add(day, rs1.getFloat(2));
					        
					        
			
					        } catch (ParseException e) {
					            e.printStackTrace();
					        }
				
					 }
					 rs1.close();

					 
				 }
				 timeseriescollection.addSeries(timeseries);
			 }
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 return timeseriescollection;
	}
	
	  public ChartPanel getChartPanel(){
	    	return frame1;
	    	
	    }
	  public static String getSpecifiedDayAfter(String specifiedDay) {
	        Calendar c = Calendar.getInstance();
	        Date date = null;
	        try {
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        c.setTime(date);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day + 1);
	 
	        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
	                .format(c.getTime());
	        return dayAfter;
	    }  
	  //查找前一天的
	  public static String getSpecifiedDayBefore(String specifiedDay) {
	        Calendar c = Calendar.getInstance();
	        Date date = null;
	        try {
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        c.setTime(date);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day - 1);
	 
	        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
	                .format(c.getTime());
	        return dayAfter;
	    }
	  //查找前三个月
	  public static String getSpecifiedDayBeforeTthree(String specifiedDay) {
	        Calendar c = Calendar.getInstance();
	        Date date = null;
	        try {
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        c.setTime(date);
	        int day = c.get(Calendar.DATE);
	        int mon=c.get(Calendar.MONTH);
	        //c.set(Calendar.DATE, day - 1);
	        c.set(Calendar.MONDAY, mon-3);
	        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
	                .format(c.getTime());
	        return dayAfter;
	    }
}
