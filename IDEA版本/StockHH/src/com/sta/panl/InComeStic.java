package com.sta.panl;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.sta.InPieChart;
import com.sta.inBarChart;
import com.sta.inBarChart2;
import com.sta.outBarChart;
import com.sta.outBarChart2;
import com.sta.outPieChart;

public class InComeStic extends JPanel{
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top level frame


	public InComeStic(int x,int y,int width,int height) {
		//Set the location and size
		this.setBounds(x, y, width, height-60);
		init();
	}


	//initialize the layout and Pie-chart
	void init() {
		
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(new InPieChart().getChartPanel());
		this.add(new outPieChart().getChartPanel());
		 
		
	}
	//method of removing all components from the container and redraw the component
	public void rep() {
		this.removeAll();
		this.repaint();
	}
	//Add inbound and outbound Pie-chart and repaint method
	public void rep1() {

		this.add(new InPieChart().getChartPanel());
		this.add(new outPieChart().getChartPanel());
		this.repaint();
	}

}
