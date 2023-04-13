package com.manage.panl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.tool.Tool;

public class ShowInOutStock extends JPanel{
	
	final int WIDTH=730;//设置顶层框架的宽度
	final int HEIGHT=50;//设置顶层框架的高度
	
	
	//表格的数据
	Object columns[] ={"ID","Supplier","Stoke name","InTime","Num","Price","Account"};//标题信息
	JTable tableL=null;//表格
	JScrollPane jscrollpane;//滚动条
	public static DefaultTableModel  model;//定义表格的控制权
	
	
	
	public ShowInOutStock(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
		
	}
	void init() {
		//一个表格实现查询两个的事情  按账号查询
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,25,30));
		
		JLabel JL=new JLabel("Orders");
		this.add(JL);
		JTextField JT1=new JTextField(12);
		this.add(JT1);
		JRadioButton jrb1=new JRadioButton("Find inbound information");
		JRadioButton jrb2=new JRadioButton("Find outbound information");
		
		ButtonGroup bg=new ButtonGroup();;	
		bg.add(jrb1);			//必须要把单选框放入按钮组作用域中才能实现单选！！！！
		bg.add(jrb2);
		this.add(jrb1);
		this.add(jrb2);
		jrb1.setSelected(true);
		JButton JB=new JButton("History");
		this.add(JB);
		table();
		this.setBorder(BorderFactory.createTitledBorder("Search"));
		
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//判断 文本框里面是否有东西，有则进行查找，没有则进行，则查找全部
				//判断单选框是选择的那个
				
				if(JT1.getText().equals("")) {
					//查找全部
					if(jrb1.isSelected()) {//第一个选择了
						//则查找第一个
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//查找第二个
						//则查找第一个
						String sqlStr="select id,supname,stockname,outtime,num,pric,oper from outstock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}
				}else {
					
					if(jrb1.isSelected()) {//第一个选择了
						//则查找第一个
						String  data[]=new String [1];
						data[0]=JT1.getText();
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock where id=?";
						ResultSet rs = Tool.showData(sqlStr, data);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//查找第二个
						//则查找第一个
						String  data[]=new String [1];
						data[0]=JT1.getText();
						String sqlStr="select id,supname,stockname,outtime,num,pric,oper from outstock where id=?";
						ResultSet rs = Tool.showData(sqlStr, data);
						Tool.addDataTable(rs, model, 7);
						
					}
					
				}
				
				
				
			}
			
		});
		
		
	}
	void table() {
		
		tableL=getTable();//初始化表格
		jscrollpane=new JScrollPane(tableL);//添加一个浏览窗格
		tableL.setPreferredSize(new Dimension(WIDTH-40,10000));//给表格设置大小
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		jscrollpane.setPreferredSize(new Dimension(WIDTH-40,400));//给表格设置大小
		this.add(jscrollpane);
		
	}
	
	JTable getTable() {
		//如果表格为空则创建表格
		if(tableL==null) {
			tableL=new JTable();//创建 
			model=new DefaultTableModel() {
				//添加一些对表格的控制 设置表格 不可动  不可编辑
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//设置为表格的模式
		
		tableL.getTableHeader().setReorderingAllowed(false);//让表格不可拖动
		tableL.getTableHeader().setResizingAllowed(false);//让表格不可拖动
			
		//列宽 和行数  并且让表格不可编辑
			
			
		}
		
		
		
		return tableL;
	}
	
}
