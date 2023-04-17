package com.sta;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.tool.Tool;

public class outBarChart {
	 ChartPanel frame1;
	public outBarChart() {
		 //Retrieve the dataset for the chart
		 CategoryDataset dataset = getDataSet();
		 //Create a new bar chart with 3D effects
		 JFreeChart chart = ChartFactory.createBarChart3D(
	            "Number of outbound goods", // Chart Title
                "Product Categories", //x-axis labels
                "Quantity", //y-axis labels
                dataset, //Dataset to be plotted
                PlotOrientation.VERTICAL, //Chart direction: vertical
                true,//Display legend
                false,//Do not generate tooltips
                false //Do not generate URLs
                );
		  //Get the chart plot
		  CategoryPlot plot=chart.getCategoryPlot();

		  //Get the horizontal axis (X-axis) and set the label font and tick label font
	      CategoryAxis domainAxis=plot.getDomainAxis();
	      domainAxis.setLabelFont(new Font("bold",Font.BOLD,14));
	      domainAxis.setTickLabelFont(new Font("times new roman",Font.BOLD,12));

		  //Get the vertical axis (Y-axis) and set the label font
	      ValueAxis rangeAxis=plot.getRangeAxis();
	      rangeAxis.setLabelFont(new Font("bold",Font.BOLD,15));

		  //Set the font for the chart title and legend
	      chart.getLegend().setItemFont(new Font("bold", Font.BOLD, 15));
	      chart.getTitle().setFont(new Font("times new roman",Font.BOLD,20));

		  //Create a new ChartPanel to display the chart and set it as visible
	      frame1=new ChartPanel(chart,true);  
	      
	}
	

	private  CategoryDataset getDataSet() {
		// TODO Auto-generated method stub

		//Create a new DefaultCategoryDataset object to store the data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int i=0;//unique column keys for the dataset

		//Define a SQL query to retrieve the top 3 suppliers by total quantity sold
        String SqlStr= "SELECT supname, sum(num) FROM outstock GROUP BY supname order by sum(num) desc LIMIT 0, 3";
        ResultSet rs = Tool.showData(SqlStr, null);
        try {
			//Iterate over each row in rs
			while(rs.next()) {
				String supname=rs.getString("supname");
				//Create an array to hold the supplier name as a parameter for the next SQL query
				String data[]=new String[1];
				data[0]=supname;

				//SQL query to retrieve the top 3 suppliers with the highest total number of items sold from the 'outstock' table
				String sql="select * from outstock  where  supname=? order by num desc LIMIT 0, 3";

				//Execute the SQL query with the data array as a parameter and stores the results in a new rs1.
				ResultSet rs1 = Tool.showData(sql,data);
				 
				 while(rs1.next()) {
					 //Get the values of the 'num', 'supname', and 'stockname' columns
					 int num = rs1.getInt("num");
					 String sup=rs1.getString("supname");
					 String sun=rs1.getString("stockname");

					 //Add the data to the dataset, using the supplier name as the row key, 'stockname+i' as the column key, and the 'num' value as the data value
					dataset.addValue(num, sup, sun+i);
					i++;
					
				 }
				rs1.close();
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return dataset;
	}
	public ChartPanel getChartPanel(){
		return frame1;
		
	}
}
