package com.sta;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.tool.Tool;

public class InPieChart {
	ChartPanel frame1;
	
	public  InPieChart() {
		
		 DefaultPieDataset data = getDataSet();
		  JFreeChart chart = ChartFactory.createPieChart3D("收入统计扇形",data,true,false,false);
		    //设置百分比
		  PiePlot pieplot = (PiePlot) chart.getPlot();
		  DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
		  NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
		  StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
		  pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
		  
		  
		   pieplot.setNoDataMessage("无数据显示");
		   pieplot.setCircular(false);
		  pieplot.setLabelGap(0.02D);
		  
		  
		  pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	      frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码
	      chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
		
	}

	private DefaultPieDataset getDataSet() {
		// TODO Auto-generated method stub
		//所以对数据一个操作都在这个界面写
		
		 DefaultPieDataset dataset =new   DefaultPieDataset();
		 ResultSet rs = Tool.showData("select DISTINCT supname from instock", null);//查看
		 
		 try {
			 
			 while(rs.next()) {
				 String supname=rs.getString("supname");//获取到供应商的数据
				 String sqlstr="select ifnull(supname,?) ,ifnull(sum(num*pric),0)-(select sum(num*pric)from instock where supname=?) from outstock where supname=?";
				 String data[]=new String[3];
				data[0]=supname;
				data[1]=supname;
				data[2]=supname;
				ResultSet rs1 = Tool.showData(sqlstr, data);
				while(rs1.next()) {
					String supname1=rs1.getString(1);
					Float sumpric=rs1.getFloat(2);
					dataset.setValue(supname1,sumpric);
				}
				rs1.close();
				 
			 }
			 
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return dataset;
	}
	
	public ChartPanel getChartPanel(){
	    	return frame1;
	    	
	    }
	
	
	
	

}
