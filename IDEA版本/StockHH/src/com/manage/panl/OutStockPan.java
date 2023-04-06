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
	Object columns[] ={"订单编号","供应商","商品名字","出库时间","商品数量","商品价格","商品库存","用户"};//标题信息
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
		JButton JB1=new JButton("开始出库");
		jpan1.add(JB1);
		
		// 定义5个按钮 
		JButton JB2=new JButton("删除出库");
		jpan1.add(JB2);
		
		// 定义5个按钮 
		JButton JB3=new JButton("更改出库");
		jpan1.add(JB3);
		
		// 定义5个按钮 
		JButton JB4=new JButton("查找出库");
		jpan1.add(JB4);
		
		
		
		//添加  4个标签  3个文本框  1个下拉框  定义一个盘子二
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,12,15));//左对齐的意思
		//设置盘子的大小和位置
		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("商品供应商");
		jpan2.add(JL1);
		this.setBorder(BorderFactory.createTitledBorder(""));
		
		
	
		cmbSupName=new JComboBox();    //创建JComboBox
		cmbSupName.addItem("--请选择供应商--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("商品名称");
		jpan2.add(JL2);
		
		
		cmbStockName=new JComboBox();    //创建JComboBox
		cmbStockName.addItem("--请选择商品--");
		jpan2.add(cmbStockName);
		
		
		
		JLabel JL3=new JLabel("商品数量");
		jpan2.add(JL3);
		
		stockNumOut=new JTextField(6);
		jpan2.add(stockNumOut);
		
		
		
		JLabel JL4=new JLabel("商品价格");
		jpan2.add(JL4);
		
		stockPricOut=new JTextField(6);
		jpan2.add(stockPricOut);
		
		//购买人
		JLabel JL5=new JLabel("客户");
		jpan2.add(JL5);
		
		stockUser=new JTextField(8);
		jpan2.add(stockUser);
		
		
		
		
		JLabel JL6=new JLabel("订单编号");
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
					JOptionPane.showMessageDialog(null, "请选择供应商", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "请选择商品", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入数量", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入价格", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(stockUser.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请输入用户名", "消息",JOptionPane.WARNING_MESSAGE);
					
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
						JOptionPane.showMessageDialog(null, "添加失败", "消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "请将价格或数据填数字类型", "消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "添加成功", "消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "库存不足不能出库", "消息",JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "没有查到相关数据", "消息",JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "没有查到相关数据", "消息",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "请输入删除编号", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					int a=OutStockDao.dellStockData(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "请检查输入编号是否存在", "消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "删除成功", "消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "请检查输入编号是否为数字", "消息",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "请输入ID编号", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {
						//则说明没有选择
						
						JOptionPane.showMessageDialog(null, "请选择供应商", "消息",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "请选择子产品", "消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品数量不能为空", "消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品价格不能为空", "消息",JOptionPane.WARNING_MESSAGE);
					}else if(stockUser.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "用户姓名不能为空", "消息",JOptionPane.WARNING_MESSAGE);
						
						
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
							JOptionPane.showMessageDialog(null, "数据未变动", "消息",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "更改成功", "消息",JOptionPane.WARNING_MESSAGE);
						}
						
						if(a==3) {
							JOptionPane.showMessageDialog(null, "请检查输入格式", "消息",JOptionPane.WARNING_MESSAGE);
						}
						if(a==4) {
							JOptionPane.showMessageDialog(null, "库存不足不能进行更改", "消息",JOptionPane.WARNING_MESSAGE);
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
