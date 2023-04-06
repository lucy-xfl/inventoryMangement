package com.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.tool.Tool;

public class ChangOwnMeg {
	final int WIDTH=200;//设置顶层框架的宽度
	final int HEIGHT=222;//设置顶层框架的高度
	JFrame jframe=new JFrame();
	public JTextField JT1;
	public JTextField JT2;
	public JTextField JT3;
	public ChangOwnMeg() {
		

		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	//初始化
	void init() {
		
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);//让窗口居中显示的
		jframe.setTitle("更改个人信息");
		
		//一个标签  一个文本框  一个  按钮    采用流布局
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));//设置为左对齐
		
		//3个标签
		//3个文本框
		//2个按钮
		
		JLabel JL1=new JLabel("姓名");
		jframe.add(JL1);
		JT1=new JTextField(12);
		jframe.add(JT1);
		
		JLabel JL2=new JLabel("地址");
		jframe.add(JL2);
		JT2=new JTextField(12);
		jframe.add(JT2);
		
		
		
		
		JLabel JL3=new JLabel("邮箱");
		jframe.add(JL3);
		JT3=new JTextField(12);
		jframe.add(JT3);
		
		JButton JB=new JButton("保存");
		JButton JB1=new JButton("重置");
		jframe.add(JB1);
		jframe.add(JB);
		
		//重置功能
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JT1.setText("");
				JT2.setText("");
				JT3.setText("");
				
			}
			
			
		});
		
		
		//更改的功能
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String  data[]=new String [4];
				 data[0]=JT1.getText();
				 data[1]=JT2.getText();
				 data[2]=JT3.getText();
				 data[3]=Login.jtextfield.getText();
				

				 
				 
				 String sqlStr="update users set sname=?,saddress=?,semail=? where account=?";
				
				 if(data[2].equals("")) {
					 JOptionPane.showMessageDialog(null, "邮箱不能为空", "登录消息",JOptionPane.WARNING_MESSAGE);
					 
				 }else {
					 int a=Tool.changeData(sqlStr, data);
						if(a==0) {
							JOptionPane.showMessageDialog(null, "请检查输入账号", "登录消息",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "更改成功", "登录消息",JOptionPane.WARNING_MESSAGE);
						}
						if(a==-1) {
							JOptionPane.showMessageDialog(null, "系统报错", "登录消息",JOptionPane.WARNING_MESSAGE);
						}
					 
					 
					 
					 
					 
				 }
		
				
			}
			
			
		});
		
		
		
		
		
		
	}
}
