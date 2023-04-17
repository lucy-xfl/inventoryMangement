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

public class inBarChart2 {

	ChartPanel frame1;
	public inBarChart2() {
		//Retrieve the dataset for the chart
		CategoryDataset dataset = getDataSet();

		//Create a new bar chart with 3D effects
		JFreeChart chart = ChartFactory.createBarChart3D(
	            "Number of inbound goods", //Chart Title
                "Product Categories", //x-axis labels
                "Quantity", //y-axis labels
                dataset, //Dataset to be plotted
                PlotOrientation.VERTICAL, //Chart direction: vertical
                true,//Display legend
                false,//Do not generate tooltips
                false//Do not generate URLs
                );
		//get the chart plot
		CategoryPlot plot=chart.getCategoryPlot();
		//Get the horizontal axis (X-axis) and set the label font and tick label font
		CategoryAxis domainAxis=plot.getDomainAxis();//Horizontal bottom list
		domainAxis.setLabelFont(new Font("bold",Font.BOLD,14));//Horizontal bottom header
		domainAxis.setTickLabelFont(new Font("times new roman",Font.BOLD,12)); //Vertical Title

		//Get the vertical axis (Y-axis) and set the label font
		ValueAxis rangeAxis=plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("bold",Font.BOLD,15));

		//Set the font for the chart title and legend
		chart.getLegend().setItemFont(new Font("bold", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("times new roman",Font.BOLD,20));

		//Create a new ChartPanel to display the chart and set it as visible
		frame1=new ChartPanel(chart,true);
	      
	}
	
	//A method of getting the data to create the dataset for the bar chart
	private  CategoryDataset getDataSet() {
		// TODO Auto-generated method stub

		//Store the data retrieved from the database.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int i=0;//unique column keys for the dataset
		//SQL query to retrieve top 9 inbound goods by quantity
        String SqlStr= "select * from instock ORDER BY num desc LIMIT 0, 9";

		//Create ResultSet object to iterate over the results and accessing the values in each row
		ResultSet rs = Tool.showData(SqlStr, null);
        try {
			//Iterate over each row in rs
			while(rs.next()) {
				//Get the number of items in stock in rs
				int num = rs.getInt("num");

				//Get the supplier name and item name from the current row in rs
				String sup=rs.getString("supname");
				String sun=rs.getString("stockname");
				//Add current item to the dataset with the supplier name as the row key, and the item name + a counter variable as the column key
			   	dataset.addValue(num, sup, sun+i);
				i++;
			}
			// Close the ResultSet for the second SQL query
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// Handle any SQLExceptions
			e.printStackTrace();
		}

        return dataset;
	}
	public ChartPanel getChartPanel(){
		return frame1;
		
	}
}
