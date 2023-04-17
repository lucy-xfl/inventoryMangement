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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.InStockDao;
import com.dao.SupManDao;
import com.tool.Tool;
import com.windows.Login;

public class InStockPan extends JPanel {
	
	final int WIDTH=730;//设置顶层框架的宽度
	final int HEIGHT=50;//设置顶层框架的高度

	
	//表格的数据
	Object columns[] ={"ID","Supplier","Name","Time","Num","Price","Total Num"};//标题信息
	JTable tableL=null;//表格
	JScrollPane jscrollpane;//滚动条
	public static DefaultTableModel  model;//定义表格的控制权
	
	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	
	public InStockPan(int x,int y,int width,int height) {
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
		jpan1.setBounds(0, 0, WIDTH-20, 70);
		jpan1.setOpaque(false);
		
		this.add(jpan1);
	
		// 定义5个按钮 
		JButton JB1=new JButton("Save");
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
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));//左对齐的意思
		//设置盘子的大小和位置
		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);

		
		
		cmbSupName=new JComboBox();    //创建JComboBox
		cmbSupName.addItem("--Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Name");
		jpan2.add(JL2);
		//_____________________________________________
	
		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		this.setBorder(BorderFactory.createTitledBorder(""));
	
		
		cmbStockName=new JComboBox();    //创建JComboBox
		cmbStockName.addItem("--Product--");
		jpan2.add(cmbStockName);
		
		
		
		JLabel JL3=new JLabel("Num");
		jpan2.add(JL3);
		
		stockNumIn=new JTextField(8);
		jpan2.add(stockNumIn);
		
		
		
		JLabel JL4=new JLabel("Price");
		jpan2.add(JL4);
		
		stockPricIn=new JTextField(6);
		jpan2.add(stockPricIn);
		
		
		JLabel JL5=new JLabel("ID");
		jpan2.add(JL5);
		
		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);
		
		
		
		this.add(jpan2);
		table();
		this.add(jscrollpane);
		SupManDao.readSup(InStockPan.cmbSupName);
		//放一个表格
		//________________________________________________________________
		//添加监听
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将数据获取  写入到数据库里面
				//InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the num", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the price", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					String sup=(String)cmbSupName.getSelectedItem();
					String sun=(String)cmbStockName.getSelectedItem();
					String num=stockNumIn.getText();
					String pri=stockPricIn.getText();
					String peo=Login.jtextfield.getText();
					int a=InStockDao.writeStock(sup, sun, num, pri,peo);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Add Failed", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please fill in the data in the numeric type", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Add successful", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Cannot delete current order because of insufficient stock", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
					
				}
				
				
			}
			
		});
			//
		//下拉框的监听
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
				
			}
			
		});
		
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
				rs = InStockDao.findStockallData();
				//传递一个存储数据的rs  和一个表格   还需要一个表格的 长度
			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relative data", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {
				//则查找单个
				
				rs=InStockDao.findStockoneData(num);
				ResultSet rs1 = InStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					
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
					
					
					
			
				
					stockPricIn.setText(pr);
					stockNumIn.setText(nu);
				
					
					
					
					
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No Relative data", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}
			
			
		}
			
		});
		
		//删除按钮 
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//直接输入  ID进行删除
				String num=stockNum.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the deletion number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					int a=InStockDao.dellStockData(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Please check if the input number exists", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Deleted successfully", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please check if the input number is a number", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Current stock is not enough to delete an order", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
				}
				
				
				
				
			}
			
		});
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
				
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {
						//则说明没有选择
						
						JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "The number of products cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Product price cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else {
						
						//写入 
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumIn.getText();
						pric=stockPricIn.getText();
						ID=stockNum.getText();
						//将四个值传入数据库
						int a=InStockDao.changeStockData(sup, sun, num, pric, ID);
						
						
						if(a==0) {
							JOptionPane.showMessageDialog(null, "Data unchanged", "Message",JOptionPane.WARNING_MESSAGE);
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
	
	private void myUpdateUI() {
	SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口


	}
	

}
