package com.manage.item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.dao.AddAcount;
import com.style.Style;
import com.tool.Tool;

public class AddStaffAccout {

	
	final int WIDTH=230;//���ö����ܵĿ��
	final int HEIGHT=300;//���ö����ܵĸ߶�
	
	JFrame jframe=new JFrame();
	
	
	public AddStaffAccout(){
		
		init();
		jframe.setVisible(true); //���õ�ǰ�����Ƿ����ʾ 
		jframe.setResizable(false);//���ڵĴ�С���ɱ�
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//����Ĭ�Ϲرշ�ʽ
		jframe.validate();//�������Ч
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	
	void init() {
		
		jframe.setTitle("c");
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		//5����ǩ  4���ı���
		JLabel JL=new JLabel("Add Staff account");//�����
		jframe.add(JL);
		Style style=new Style();
		JL.setFont(style.title);
		
		JLabel JL1=new JLabel("Account: ");//�����
		jframe.add(JL1);
		JTextField JT1=new JTextField(10);
		jframe.add(JT1);
		
		JLabel JL5=new JLabel("Password:");//�����
		jframe.add(JL5);
		JPasswordField JT5=new JPasswordField(10);
		jframe.add(JT5);
		
		
		JLabel JL6=new JLabel("Confirm: ");//�����
		jframe.add(JL6);
		JPasswordField JT6=new JPasswordField(10);
		jframe.add(JT6);
		
		JLabel JL4=new JLabel("Email:  ");//�����
		jframe.add(JL4);
		JTextField JT4=new JTextField(10);
		jframe.add(JT4);
		
		
		
		JLabel JL2=new JLabel("Name:   ");//�����
		jframe.add(JL2);
		JTextField JT2=new JTextField(10);
		jframe.add(JT2);
		
		JLabel JL3=new JLabel("Address: ");//�����
		jframe.add(JL3);
		JTextField JT3=new JTextField(10);
		jframe.add(JT3);
		
		
	
		//һ����ť
		JButton JB=new JButton("Add Staff");
		jframe.add(JB);
		
		JButton JB1=new JButton("Reset");
		jframe.add(JB1);
		//������������
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
		
		
		//������ݿ�
		//������������
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
					JOptionPane.showMessageDialog(null, "Account cannot be empty", "Login Message",JOptionPane.WARNING_MESSAGE);
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Password cannot be empty", "Login Message",JOptionPane.WARNING_MESSAGE);
				}else if(okpassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Confirm password cannot be empty", "Login Message",JOptionPane.WARNING_MESSAGE);
				}else if(!okpassword.equals(password)) {
					JOptionPane.showMessageDialog(null, "Not same", "Login Message",JOptionPane.WARNING_MESSAGE);
				}else if(emain.equals("")){
					
					JOptionPane.showMessageDialog(null, "Address cannot be empty", "Login Message",JOptionPane.WARNING_MESSAGE);
					
					
				}else {
					
					int a=AddAcount.writeAccount(account, okpassword, name, address, emain);
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Add Failed", "Login Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Add successful", "Login Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please check if the work number is duplicated", "Login Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
				}

			}
	
			
		});
		
		
		
		
		
		
	}
}
