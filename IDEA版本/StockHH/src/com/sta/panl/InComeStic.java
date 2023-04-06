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
	final int WIDTH=730;//设置顶层框架的宽度
	final int HEIGHT=50;//设置顶层框架的高度
	
	
	
	public InComeStic(int x,int y,int width,int height) {
		//第一个 w  h 是表示所在位置 第二个表示 //设置他的大小
		this.setBounds(x, y, width, height-60);
		init();
	}
	

	
	void init() {
		
		this.setLayout(new GridLayout(2,2,10,10));
		this.add(new InPieChart().getChartPanel());
		this.add(new outPieChart().getChartPanel());
		 
		
	}
	public void rep() {
		this.removeAll();
		this.repaint();
	}
	public void rep1() {

		this.add(new InPieChart().getChartPanel());
		this.add(new outPieChart().getChartPanel());
		this.repaint();
	}




}
