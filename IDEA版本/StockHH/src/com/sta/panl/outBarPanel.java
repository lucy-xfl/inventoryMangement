package com.sta.panl;

import java.awt.GridLayout;

import javax.swing.JPanel;


import com.sta.outBarChart;
import com.sta.outBarChart2;

public class outBarPanel extends JPanel{
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top level frame
	
	
	
	public outBarPanel(int x, int y, int width, int height) {
		//Set the location and size
		this.setBounds(x, y, width, height-60);
		init();
	}
	

	//initializes the layout of a pane
	void init() {
		//set the layout and add the barcharts
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(new outBarChart().getChartPanel());
		this.add(new outBarChart2().getChartPanel());
		 
		
	}

	//method of removing all components from the container and redraw the component
	public void rep() {
		this.removeAll();
		this.repaint();
	}
	//Add inbound and outbound Pie-chart and repaint method
	public void rep1() {
		this.add(new outBarChart().getChartPanel());
		this.add(new outBarChart2().getChartPanel());
		this.repaint();
	}




}
