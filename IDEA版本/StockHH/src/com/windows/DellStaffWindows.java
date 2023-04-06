package com.windows;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.tool.Tool;

public class DellStaffWindows {
	
	
	Object columns[] ={"员工工号","权限","员工姓名","员工住址","员工邮箱"};//标题信息
	JTable tableL=null;//表格
	JScrollPane jscrollpane;//滚动条
	public static DefaultTableModel  model;//定义表格的控制权
	
	final int WIDTH=500;//设置顶层框架的宽度
	final int HEIGHT=400;//设置顶层框架的高度
	JFrame jframe=new JFrame();
	
	public DellStaffWindows() {
		

		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	void init() {
		
		
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);//让窗口居中显示的
		jframe.setTitle("删除员工账号");
		
		//一个标签  一个文本框  一个  按钮    采用流布局
		jframe.setLayout(new FlowLayout(FlowLayout.LEFT));//设置为左对齐
		
		JLabel JL1=new JLabel("员工工号");
		jframe.add(JL1);
		JTextField JT1=new JTextField(12);
		jframe.add(JT1);
		
		JButton JB1=new JButton("删除员工账号");
		jframe.add(JB1);
		JButton JB2=new JButton("查询员工账号");
		jframe.add(JB2);
		table();
		jframe.add(jscrollpane);
		
		JB1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将文本框的内容传入过来
				String sqlStr="delete from users where account=?";
				
				String  data[]=new String [1];
				data[0]=JT1.getText();
				int a=Tool.dellData(sqlStr, data);
				if(a==0) {
					JOptionPane.showMessageDialog(null, "请检查账号是否存在", "消息",JOptionPane.WARNING_MESSAGE);
				}
				if(a==-1) {
					JOptionPane.showMessageDialog(null, "请检查账号输入内容", "消息",JOptionPane.WARNING_MESSAGE);
				}
				if(a>=1) {
					JOptionPane.showMessageDialog(null, "删除成功", "消息",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sqlStr="select account,if(pow=1,'普通用户','管理员'),sname,saddress,semail from users";
				
				
				ResultSet rs = Tool.showData(sqlStr, null);
				 Tool.addDataTable(rs, model, 5);
				
				
				
			}
		
		});
		
		
		
	}
	void table() {
		
		tableL=getTable();//初始化表格
		jscrollpane=new JScrollPane(tableL);//添加一个浏览窗格
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));//给表格设置大小
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		//jscrollpane.setBounds(0, 170, WIDTH-30, 360);
		jscrollpane.setPreferredSize(new Dimension(WIDTH-30,320));//给表格设置大小
		
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
