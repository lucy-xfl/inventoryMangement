package com.sta.panl;

import java.awt.GridLayout;

import javax.swing.JPanel;


import com.sta.inBarChart;
import com.sta.inBarChart2;

public class inBarPanel extends JPanel{
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top level frame
	
	
	
	public inBarPanel(int x, int y, int width, int height) {
		//Set the location and size
		this.setBounds(x, y, width, height-60);
		init();
	}


	//initialize the layout and barchart
	void init() {
		
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(new inBarChart().getChartPanel());
		this.add(new inBarChart2().getChartPanel());
		
		
	}

	//method of removing all components from the container and redraw the component
	public void rep() {
		this.removeAll();
		this.repaint();
	}
	//Add barchart and repaint
	public void rep1() {

		this.add(new inBarChart().getChartPanel());
		this.add(new inBarChart2().getChartPanel());
		this.repaint();
	}
}
