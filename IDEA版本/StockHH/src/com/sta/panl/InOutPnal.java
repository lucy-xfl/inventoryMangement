package com.sta.panl;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import com.sta.InOutTimeSeriesChart;
import com.sta.InOutTimeSeriesChart2;

public class InOutPnal {

	final int WIDTH=730;//设置顶层框架的宽度
	final int HEIGHT=50;//设置顶层框架的高度
	JPanel  JPM=new JPanel();
	public static JComboBox cmbSupName;//定义一个下拉框
	public  InOutPnal(int x,int y,int width,int height) {
		
		JPM.setBounds(x, y, width, height-60);
		init();
	}
	
	
	void init() {
		
		JPanel JP1=new JPanel();
		JP1.setLayout(new FlowLayout(FlowLayout.CENTER,20,100));//居中
		JLabel JL1=new JLabel("供应商");
		JP1.add(JL1);
		cmbSupName = new JComboBox();    //创建JComboBox
		cmbSupName.addItem("--请选择供应商--");
		JP1.add(cmbSupName);
		
		JLabel JL2=new JLabel("仓库状态");
		JP1.add(JL2);
		

		
		JRadioButton jrb1=new JRadioButton("入库");//定义两个单选按钮
		JRadioButton jrb2=new JRadioButton("出库");
		jrb1.setSelected(true);//设置默认选择入库
		ButtonGroup bg=new ButtonGroup();
		bg.add(jrb1);			//必须要把单选框放入按钮组作用域中才能实现单选！！！！
		bg.add(jrb2);//把单选按钮放到一个组里
		JP1.add(jrb1);
		JP1.add(jrb2);
		
		

		//增加两个按钮
		JButton JB1=new JButton("查看最近三个月");
		JP1.add(JB1);
		
		JButton JB2=new JButton("查看历来数据");
		JP1.add(JB2);
		
		JPM.setLayout(new GridLayout(2,2,10,10));//最外层的组件设置成 这个网格布局
		
		//JP1.setBorder(BorderFactory.createTitledBorder("查看统计图"));
		JPM.add(JP1);
		JPM.add(new InOutTimeSeriesChart("","instock").getChartPanel());//第一个参数是查询那个供应商，第二个参数 是入库
		//this.add(new outBarChart2().getChartPanel());
		JPM.setBorder(BorderFactory.createTitledBorder(""));
		JP1.setBorder(BorderFactory.createTitledBorder("统计数据"));
		
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//读取所有数据 一个运营商的所有的  数据  不是说所有运营
				
				if(cmbSupName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择运营商", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					
					String sup=(String) cmbSupName.getSelectedItem();//获取选择的标题  获取是哪个运营商
					String sqlstr;//用来标志是入库还是出库
					if(jrb1.isSelected()) {//jb1是入库单选按钮
						sqlstr="instock";//数据库的名字 传入数据库名字
					}else {
						sqlstr="outstock";//出库的数据库名字
						
					}
					
					JPM.remove(1);
					SwingUtilities.updateComponentTreeUI(JPM);//添加或删除组件后,更新窗口
					JPM.add(new InOutTimeSeriesChart(sup, sqlstr).getChartPanel());
					
	
				}
			}
			
		});
		
		
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//读取所有数据 一个运营商的所有的  数据  不是说所有运营
				
				if(cmbSupName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "请选择运营商", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					
					String sup=(String) cmbSupName.getSelectedItem();//获取选择的标题  获取是哪个运营商
					String sqlstr;//用来标志是入库还是出库
					if(jrb1.isSelected()) {//jb1是入库单选按钮
						sqlstr="instock";//数据库的名字 传入数据库名字
					}else {
						sqlstr="outstock";//出库的数据库名字
						
					}
					
					JPM.remove(1);
					SwingUtilities.updateComponentTreeUI(JPM);//添加或删除组件后,更新窗口
					JPM.add(new InOutTimeSeriesChart2(sup, sqlstr).getChartPanel());
					

				}
			}
			
		});
		
		

		
	}

public JPanel  JP() {
	return JPM;
}
}
