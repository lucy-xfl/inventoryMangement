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
	
	public InOutTimeSeriesChart2(String supname,String sto) {
		//The string that is passed is the suppliers' name and the string to determine if the reading is inbound or outbound
		//create XYDataset object to generate data
		XYDataset xydataset = createDataset(supname,sto);

		//create JFreeChart object and set the parameters in the object
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Warehouse Flow Curve Chart", "Date", "Quantity",xydataset, true, true, true);

		//Display the data of jfreechart
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		//Get the horizontal axis (X-axis) and set the label font, tick label font and date format
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
		dateaxis.setLabelFont(new Font("bold",Font.BOLD,14));
		dateaxis.setTickLabelFont(new Font("times new roman",Font.BOLD,12));

		//Create a new ChartPanel to display the jfreechart and set it as visible
		frame1=new ChartPanel(jfreechart,true);

		//Get the vertical axis (Y-axis) and set the label font
        ValueAxis rangeAxis=xyplot.getRangeAxis();//Get Column
		rangeAxis.setLabelFont(new Font("bold",Font.BOLD,15));

		//set the font for the jfreechart's legend and title, respectively
        jfreechart.getLegend().setItemFont(new Font("bold", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("times new roman",Font.BOLD,20));

	}

	private XYDataset createDataset(String supname, String sto) {
		 // TODO Auto-generated method stub
		 //SQL query that selects distinct stock names from 'sto' where the supplier name is equal to the 'supname'
		 String sqlstr="select DISTINCT stockname from "+sto +" where supname=? ";

		 //Define a string array to hold the supplier name argument for the SQL query, and execute the sql query and store the result in rs
		 String data[]=new String[1];
		 data[0]=supname;
		 ResultSet rs = Tool.showData(sqlstr, data);

		 //Create initial Curve Chart
		 TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		
		 try {
			//Loop through the ResultSet to get the stocknames
			while(rs.next()) {
				//Create a new TimeSeries object for each stockname
				String sun=rs.getString(1);
				TimeSeries timeseries = new TimeSeries(sun,
			                org.jfree.data.time.Day.class);

				//Define a new SQL query string to get the earliest and latest date for the given supplier and stockname
				String data1[]=new String[1];
				data1[0]=supname;
				String afterData;
				String beforeData;

				//Get the latest date for the given supplier and stockname from the instock or outstock table
				 if(sto.equals("instock")) {
					 sqlstr="select intime from instock   where supname=? ORDER BY intime desc LIMIT 0 , 1";
				 }else {
					 sqlstr="select outtime from outstock   where supname=? ORDER BY outtime desc LIMIT 0 , 1";
				 }
				 ResultSet rs1 = Tool.showData(sqlstr, data1);
				 if(rs1.next()) {
					 afterData= rs1.getString(1);
				 }
				 else {
					 afterData="2019-01-08 00:00:00";
				 }
				 rs1.close();

				 //Get the earliest date for the given supplier and stockname from the instock or outstock table
				 if(sto.equals("instock")) {
					 sqlstr="select intime from instock   where supname=? ORDER BY intime asc LIMIT 0 , 1";
				 }else {
					 sqlstr="select outtime from outstock   where supname=? ORDER BY outtime asc LIMIT 0 , 1";
				 }
				 rs1 = Tool.showData(sqlstr, data1);
				 if(rs1.next()) {
					 beforeData= rs1.getString(1);
					 
				 }else {
					 beforeData="2019-01-08 00:00:00";
				 }
				 rs1.close();


				 //Get the date range between the earliest and latest dates
				 beforeData = getSpecifiedDayAfter(getSpecifiedDayBefore(beforeData));
				 afterData = getSpecifiedDayAfter(getSpecifiedDayBefore(afterData));
				 beforeData = getSpecifiedDayBeforeTthree(afterData);
				
				 //Loop through the date range and get the data for the given supplier, stockname and date from the instock or outstock table
				 while(!afterData.equals(beforeData)) {
					 //Set the SQL query string based on the stock type (instock or outstock)
					 if(sto.equals("instock")) {
						 sqlstr="select stockname ,sum(num) from instock where  supname=? and intime>=? and intime<=? and   stockname=?";
					 }else {
						 sqlstr="select stockname ,sum(num) from outstock where  supname=? and outtime>=? and outtime<=? and   stockname=?";
					 }
					 //Set the values to be passed in as parameters to the SQL query
					 String[] dasun=new String [4];
					 dasun[0]=supname;
					 dasun[1]=beforeData;
					 dasun[2]=afterData;
					 dasun[3]=rs.getString("stockname");

					 //Execute the SQL query and loop through the dasun
					 rs1=Tool.showData(sqlstr,dasun);

					 	while(rs1.next()) {
						 try {
							//Parse the date in the correct format and get year, month and day
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							Date date = sf.parse(beforeData);
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date);
					        int year = calendar.get(Calendar.YEAR);
					        int month = calendar.get(Calendar.MONTH) + 1;
					        int day1 = calendar.get(Calendar.DAY_OF_MONTH);

							//Create a Day object using the year, month and day and add it to the TimeSeries object with the corresponding value
					        Day day = new Day(day1,month,year);
					        timeseries.add(day, rs1.getFloat(2));
					        } catch (ParseException e) {
					            e.printStackTrace();
					        }
				
					 }
					 //Close the result set and move the date forward by one day
					 rs1.close();
					 beforeData= getSpecifiedDayAfter(beforeData);

					 
				 }

				 //Check if the last date in the range still needs to be processed
				 if(afterData.equals(beforeData)) {
					 //Set the SQL query string based on the stock type (instock or outstock)
					 if(sto.equals("instock")) {
						 sqlstr="select stockname ,sum(num) from instock where  supname=? and intime>=? and intime<=? and   stockname=?";
					 }else {
						 sqlstr="select stockname ,sum(num) from outstock where  supname=? and outtime>=? and outtime<=? and   stockname=?";
					 }
					 //Set the values to be passed in as parameters to the SQL query
					 String[] dasun=new String [4];
					 dasun[0]=supname;
					 dasun[1]=beforeData;
					 dasun[2]=afterData;
					 dasun[3]=rs.getString("stockname");

					 //Execute the SQL query and loop through the dasun
					 rs1=Tool.showData(sqlstr,dasun);

					 	while(rs1.next()) {
							 //Parse the date in the correct format and get year, month and day
						 try {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					        Date date = sf.parse(beforeData);
					        Calendar calendar = Calendar.getInstance();
					        calendar.setTime(date);
					           
					        int year = calendar.get(Calendar.YEAR);
					        int month = calendar.get(Calendar.MONTH) + 1;
					        int day1 = calendar.get(Calendar.DAY_OF_MONTH);

							//Create a Day object using the year, month and day and add it to the TimeSeries object with the corresponding value
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


	  //A method of looking for the day after
	  public static String getSpecifiedDayAfter(String specifiedDay) {
		//Creating an instance of the Calendar class and declare the date variable and initialize it
		Calendar c = Calendar.getInstance();
	    Date date = null;

		try {
			//Parse the 'specifiedDay' to a Date object using SimpleDateFormat with pattern "yy-MM-dd"
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		}
		//If any error, print the stack trace
		catch (ParseException e) {
			e.printStackTrace();
		}
		//Set time to the date
		c.setTime(date);
		//Get the day of the month and set the calendar instance's day of the month to the next day
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		//Format the updated calendar instance's time to the pattern "yyyy-MM-dd"
		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	    }



	  //A method of looking for the day before
	  public static String getSpecifiedDayBefore(String specifiedDay) {
		    //Creating an instance of the Calendar class and declare the date variable and initialize it
			Calendar c = Calendar.getInstance();
	        Date date = null;

	        try {
				//Parse the 'specifiedDay' to a Date object using SimpleDateFormat with pattern "yy-MM-dd"
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			//Set time to the date
	        c.setTime(date);

			//Get the day of the month from the calendar instance
	        int day = c.get(Calendar.DATE);

			//Set the calendar instance's day of the month to the previous day
	        c.set(Calendar.DATE, day-1);

			//Format the updated calendar instance's time to the pattern "yyyy-MM-dd"
	        String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
	                .format(c.getTime());
	        return dayBefore;
	    }

	  //A method of looking for the three months before
	  public static String getSpecifiedDayBeforeTthree(String specifiedDay) {
	        Calendar c = Calendar.getInstance();
	        Date date = null;
	        try {
	            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        c.setTime(date);
			//Get the day of the month and the month from the calendar instance
	        int day = c.get(Calendar.DATE);
	        int mon=c.get(Calendar.MONTH);

			//Set the calendar instance's month to three months before the current month
	        c.set(Calendar.MONDAY, mon-3);

			//Format the updated calendar instance's time to the pattern "yyyy-MM-dd"
	        String dayBeforeThree = new SimpleDateFormat("yyyy-MM-dd")
	                .format(c.getTime());
	        return dayBeforeThree;
	    }
}
