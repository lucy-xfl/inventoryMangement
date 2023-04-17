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

public class outBarChart2 {
	 ChartPanel frame1;
	public outBarChart2() {
		//Retrieve the dataset for the chart
		CategoryDataset dataset = getDataSet();

		 //Create a new bar chart with 3D effects
		 JFreeChart chart = ChartFactory.createBarChart3D(
				 "Number of outbound goods", // Chart Title
                "Product Categories", //x-axis labels
                "Quantity", //y-axis labels
                dataset, //Dataset to be plotted
                PlotOrientation.VERTICAL,//Chart direction: vertical
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
		//SQL query to retrieve the top 9 rows of data from the 'outstock' table, ordered by the 'num' column in descending order
        String SqlStr= "select * from outstock ORDER BY num desc LIMIT 0, 9";

		ResultSet rs = Tool.showData(SqlStr, null);
        try {
			//Iterate over each row in rs
			while(rs.next()) {

				//Get the values of the 'num', 'supname', and 'stockname' columns
				int num = rs.getInt("num");
				String sup=rs.getString("supname");
				String sun=rs.getString("stockname");

				//Add the data to the dataset, using the supplier name as the row key, 'stockname+i' as the column key, and the 'num' value as the data value
			   	dataset.addValue(num, sup, sun+i);
				i++;

			}
			//Close rs
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
