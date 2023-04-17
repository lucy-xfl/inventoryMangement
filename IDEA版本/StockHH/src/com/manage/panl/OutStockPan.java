package com.manage.panl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.InStockDao;
import com.dao.OutStockDao;
import com.dao.SupManDao;
import com.tool.Tool;
import com.windows.Login;

public class OutStockPan extends JPanel {
	
	final int WIDTH=730;//设置顶层框架的宽度
	final int HEIGHT=50;//设置顶层框架的高度

	
	//表格的数据
	Object columns[] ={"ID","Supplier","Stock Name","Out-time","Num","Price","Total-num","Account"};//标题信息
	JTable tableL=null;//表格
	JScrollPane jscrollpane;//滚动条
	public static DefaultTableModel  model;//定义表格的控制权
	
	public static JTextField stockPricOut;
	public static JTextField stockNumOut;
	public static JTextField stockUser;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	public OutStockPan(int x,int y,int width,int height) {
		//第一个 w  h 是表示所在位置 第二个表示 //设置他的大小
		this.setBounds(x, y, width, height);
		init();
	}
	
	
	void init() {
		//设置空布局
		this.setLayout(null);
		//产品入库信息：商品名称，商品入库时间，商品入库价格，商品入库数量，商品库存，商品供应商
	
		
		//需要三个盘子
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));//左对齐的意思
		//设置盘子的大小和位置
		jpan1.setBounds(0, 0, WIDTH-20, 50);

		
		this.add(jpan1);
	
		// 定义5个按钮 
		JButton JB1=new JButton("Depot");
		jpan1.add(JB1);
		
		// 定义5个按钮 
		JButton JB2=new JButton("Delete");
		jpan1.add(JB2);
		
		// 定义5个按钮 
		JButton JB3=new JButton("Change");
		jpan1.add(JB3);
		
		// 定义5个按钮 
		JButton JB4=new JButton("Find");
		jpan1.add(JB4);
		
		
		
		//添加  4个标签  3个文本框  1个下拉框  定义一个盘子二
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,12,15));//左对齐的意思
		//设置盘子的大小和位置
		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);
		this.setBorder(BorderFactory.createTitledBorder(""));
		
		
	
		cmbSupName=new JComboBox();    //创建JComboBox
		cmbSupName.addItem("--Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Stock Name");
		jpan2.add(JL2);
		
		
		cmbStockName=new JComboBox();    //创建JComboBox
		cmbStockName.addItem("--Stock Name--");
		jpan2.add(cmbStockName);
		
		
		
		JLabel JL3=new JLabel("Number");
		jpan2.add(JL3);
		
		stockNumOut=new JTextField(6);
		jpan2.add(stockNumOut);
		
		
		
		JLabel JL4=new JLabel("Price");
		jpan2.add(JL4);
		
		stockPricOut=new JTextField(6);
		jpan2.add(stockPricOut);
		
		//购买人
		JLabel JL5=new JLabel("Customer");
		jpan2.add(JL5);
		
		stockUser=new JTextField(8);
		jpan2.add(stockUser);
		
		
		
		
		JLabel JL6=new JLabel("ID");
		jpan2.add(JL6);
		
		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);
		
		//_____________________________________________

		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		
		
		
		
		this.add(jpan2);
		table();
		this.add(jscrollpane);
		
		//放一个表格
		//下拉监听
		//下拉框的监听
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
			}
			
		});
		//增加数据的监听
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将数据获取  写入到数据库里面
				//InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a supplier", "message",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a product", "message",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the number", "message",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the price", "message",JOptionPane.WARNING_MESSAGE);
				}else if(stockUser.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please enter the account", "message",JOptionPane.WARNING_MESSAGE);
					
				}
				else{
					String sup=(String)cmbSupName.getSelectedItem();
					String sun=(String)cmbStockName.getSelectedItem();
					String num=stockNumOut.getText();
					String pri=stockPricOut.getText();
					String usrname=stockUser.getText();
					String peo=Login.jtextfield.getText();
					
					
					int a=OutStockDao.writeStock(sup, sun, num, pri, usrname,peo);
					
					
					
					
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Add Failed", "message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Enter a number", "message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Add successful", "message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Can't outbound because of insufficient stock", "message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
				}
				
				
			}
			
		});
		//查询功能的实现
		
		//查询查询所有吧
		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//两种查法，查找全部 和查找单个 
			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {
				//则查找全部
				rs = OutStockDao.findStockallData();
				//传递一个存储数据的rs  和一个表格   还需要一个表格的 长度
			int a=Tool.addDataTable(rs, model,8 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relevant data available", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {
				//则查找单个
				
				rs=OutStockDao.findStockoneData(num);
				ResultSet rs1 = OutStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					String user=rs1.getString("user");
					
					//遍历两个下拉框
					
			
					
					for(int i=0;i<cmbSupName.getItemCount();i++) {
						String a=(String) cmbSupName.getItemAt(i);
						if(a.equals(sup)) {
							cmbSupName.setSelectedIndex(i);
							cmbSupName.repaint();
							for(int j=0;j<cmbStockName.getItemCount();j++) {
								String c=(String) cmbStockName.getItemAt(j);
								if(c.equals(sun)) {
									cmbStockName.setSelectedIndex(j);
									cmbStockName.repaint();
								}
									
							}
						}
							
					}
					
					
					
			
				
					stockPricOut.setText(pr);
					stockNumOut.setText(nu);
					stockUser.setText(user);
				
					
					
					
					
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			int a=Tool.addDataTable(rs, model,8 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relative data", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}
			
			
		}
			
		});
		//删除出库记录
		
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//直接输入  ID进行删除
				String num=stockNum.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the deletion ID", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					int a=OutStockDao.dellStockData(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Check the ID", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Deleted successfully", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please check if the input number is a number", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
				
				
				
			}
			
		});
		
		//更改
		//更改数据
		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sup=null;//供应商
				String sun=null;//子产品
				String num=null;//数量
				String pric=null;//价格
				String ID=null;//价格
				String user=null;
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {
						//则说明没有选择
						
						JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "The number of products cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Product price cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockUser.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "User name cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
						
						
					}else{
						
						//写入 
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumOut.getText();
						pric=stockPricOut.getText();
						ID=stockNum.getText();
						user=stockUser.getText();
						//将四个值传入数据库
						int a=OutStockDao.changeStockData(sup, sun, num, pric,user, ID);
						if(a==0) {
							JOptionPane.showMessageDialog(null, "No change in data", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "Change successful", "Message",JOptionPane.WARNING_MESSAGE);
						}
						
						if(a==3) {
							JOptionPane.showMessageDialog(null, "Please check the input format", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==4) {
							JOptionPane.showMessageDialog(null, "Insufficient inventory to make changes", "Message",JOptionPane.WARNING_MESSAGE);
						}
					
						
					}
					
					
					
				}
				
			}
			
			
			
			
			
		});
		
		
		
		
		
		
		
		
		
			
		
	}
	
	
	void table() {
		
		tableL=getTable();//初始化表格
		jscrollpane=new JScrollPane(tableL);//添加一个浏览窗格
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));//给表格设置大小
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		jscrollpane.setBounds(0, 170, WIDTH-20, 360);
		
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
