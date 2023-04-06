package com.manage.item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.dao.AddAcount;
import com.style.Style;
import com.tool.Tool;

public class AddStaffAccout {

	
	final int WIDTH=220;//设置顶层框架的宽度
	final int HEIGHT=280;//设置顶层框架的高度
	
	JFrame jframe=new JFrame();
	
	
	public AddStaffAccout(){
		
		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	
	void init() {
		
		jframe.setTitle("添加员工账号号");
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		//5个标签  4个文本框
		JLabel JL=new JLabel("添加员工账号");//大标题 
		jframe.add(JL);
		Style style=new Style();
		JL.setFont(style.title);
		
		JLabel JL1=new JLabel("员工工号:");//大标题 
		jframe.add(JL1);
		JTextField JT1=new JTextField(10);
		jframe.add(JT1);
		
		JLabel JL5=new JLabel("账号密码:");//大标题 
		jframe.add(JL5);
		JPasswordField JT5=new JPasswordField(10);
		jframe.add(JT5);
		
		
		JLabel JL6=new JLabel("确认密码:");//大标题 
		jframe.add(JL6);
		JPasswordField JT6=new JPasswordField(10);
		jframe.add(JT6);
		
		JLabel JL4=new JLabel("员工邮箱:");//大标题 
		jframe.add(JL4);
		JTextField JT4=new JTextField(10);
		jframe.add(JT4);
		
		
		
		JLabel JL2=new JLabel("员工姓名:");//大标题 
		jframe.add(JL2);
		JTextField JT2=new JTextField(10);
		jframe.add(JT2);
		
		JLabel JL3=new JLabel("员工地址:");//大标题 
		jframe.add(JL3);
		JTextField JT3=new JTextField(10);
		jframe.add(JT3);
		
		
	
		//一个按钮
		JButton JB=new JButton("添加员工");
		jframe.add(JB);
		
		JButton JB1=new JButton("重置信息");
		jframe.add(JB1);
		//重置所有内容
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JT1.setText("");
				JT2.setText("");
				JT3.setText("");
				JT4.setText("");
				JT5.setText("");
				JT6.setText("");
			}
	
			
		});
		
		
		//添加数据库
		//重置所有内容
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String account=JT1.getText();
				String password=new String(JT5.getPassword());
				String okpassword=new String(JT6.getPassword());
				String name=JT2.getText();
				String address=JT3.getText();
				String emain=JT4.getText();
				
				
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "员工工号不能为空", "登录消息",JOptionPane.WARNING_MESSAGE);
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "员工密码不能为空", "登录消息",JOptionPane.WARNING_MESSAGE);
				}else if(okpassword.equals("")) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空", "登录消息",JOptionPane.WARNING_MESSAGE);
				}else if(!okpassword.equals(password)) {
					JOptionPane.showMessageDialog(null, "两次密码不一致", "登录消息",JOptionPane.WARNING_MESSAGE);
				}else if(emain.equals("")){
					
					JOptionPane.showMessageDialog(null, "邮箱地址不能为空", "登录消息",JOptionPane.WARNING_MESSAGE);
					
					
				}else {
					
					int a=AddAcount.writeAccount(account, okpassword, name, address, emain);
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "添加失败", "登录消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "添加成功", "登录消息",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "请检查工号是否重复", "登录消息",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
				}

			}
	
			
		});
		
		
		
		
		
		
	}
}
