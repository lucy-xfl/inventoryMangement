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

public class outPieChart {
	ChartPanel frame1;
	
	public  outPieChart() {

		  //Get the data set to be used for the chart
		  DefaultPieDataset data = getDataSet();
		  //Create a 3D pie chart with the given title and data set
		  JFreeChart chart = ChartFactory.createPieChart3D("Expenditure Statistics Fan Chart",data,true,false,false);

		  //Get the plot of the chart
		  PiePlot pieplot = (PiePlot) chart.getPlot();

		  //Set the format of the percentage values displayed on the chart
		  DecimalFormat df = new DecimalFormat("0.00%");
		  NumberFormat nf = NumberFormat.getNumberInstance();
		  StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
		  pieplot.setLabelGenerator(sp1);//Set pie chart to show percentage
		  
		  
		  pieplot.setNoDataMessage("No data available");//Set the message to display when no data is available
		  pieplot.setCircular(false);//Set the chart is not circular
		  pieplot.setLabelGap(0.02D);//Set the gap between the labels and the plot
		  
		  
		  pieplot.setIgnoreNullValues(true);//Set not to display null values
	      pieplot.setIgnoreZeroValues(true);//Set not to display negative values

		  //Create a chart panel with the chart and set it to visible
	      frame1=new ChartPanel (chart,true);

		  //Set the font of the chart title
	      chart.getTitle().setFont(new Font("times new roman",Font.BOLD,20));

		  //Get the chart area and set the font of the pie plot labels
	      PiePlot piePlot= (PiePlot) chart.getPlot();
	      piePlot.setLabelFont(new Font("times new roman",Font.BOLD,10));

		  //Set the font of the legend items
	      chart.getLegend().setItemFont(new Font("bold",Font.BOLD,10));
		
	}

	private DefaultPieDataset getDataSet() {
		// TODO Auto-generated method stub
		
		 DefaultPieDataset dataset =new   DefaultPieDataset();
		 //A SQL query is executed to get distinct supplier names from 'outstock' table and the result is stored in 'rs' ResultSet
		 ResultSet rs = Tool.showData("select DISTINCT supname from outstock", null);
		 
		 try {
			 //iterate over the ResultSet object 'rs'
			 while(rs.next()) {
				 // get the supplier name from the ResultSet
				String supname=rs.getString("supname");

				//Construct a SQL statement to calculate the total cost for the supplier
				String sqlstr="select ifnull(supname,?) ,ifnull(sum(num*pric),0)-(select sum(num*pric)from instock where supname=?) from outstock where supname=?";
				String data[]=new String[3];
				data[0]=supname;
				data[1]=supname;
				data[2]=supname;

				//Execute the SQL statement using the 'showData' method from the 'Tool' class and store the result in 'rs1'
				ResultSet rs1 = Tool.showData(sqlstr, data);
				while(rs1.next()) {
					//Get the supplier name and total cost from the ResultSet
					String supname1=rs1.getString(1);
					Float sumpric=rs1.getFloat(2);

					//if the total cost is less than or equal to zero, add a new data item to the pie chart with the supplier name and the absolute value of the total cost
					if(sumpric<=0) {
						sumpric=-sumpric;
						dataset.setValue(supname1,sumpric);
					}

					
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
