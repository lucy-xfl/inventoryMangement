package com.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.tool.Tool;

public class ChangOwnMeg {
	final int WIDTH=200;//Set the width of the top-level frame
	final int HEIGHT=222;//Set the height of the top level frame
	JFrame jframe=new JFrame();
	public JTextField JT1;
	public JTextField JT2;
	public JTextField JT3;
	public ChangOwnMeg() {
		

		init();
		jframe.setVisible(true); //Set whether the current window can be displayed
		jframe.setResizable(false);//The size of the window cannot be changed
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Set the default closing method
		jframe.validate();//Making components work
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	//Initialization
	void init() {
		
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);//To center the window
		jframe.setTitle("Change personal information");
		
		//A label, a text box, a button, and a flow layout.
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));//…Ë÷√Œ™◊Û∂‘∆Î
		
		//3 JLabel
		//3 JTextField
		//2 JButton
		
		JLabel JL1=new JLabel("Name");
		jframe.add(JL1);
		JT1=new JTextField(12);
		jframe.add(JT1);
		
		JLabel JL2=new JLabel("Address");
		jframe.add(JL2);
		JT2=new JTextField(12);
		jframe.add(JT2);
		
		
		
		
		JLabel JL3=new JLabel("Email Address");
		jframe.add(JL3);
		JT3=new JTextField(12);
		jframe.add(JT3);
		
		JButton JB=new JButton("Save");
		JButton JB1=new JButton("Reset");
		jframe.add(JB1);
		jframe.add(JB);
		
		//Reset Function
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JT1.setText("");
				JT2.setText("");
				JT3.setText("");
				
			}
			
			
		});
		
		
		//Change Function
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
					 JOptionPane.showMessageDialog(null, "Mailbox cannot be empty", "Login Message",JOptionPane.WARNING_MESSAGE);
					 
				 }else {
					 int a=Tool.changeData(sqlStr, data);
						if(a==0) {
							JOptionPane.showMessageDialog(null, "Please check the input account number", "Login Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "Change successful", "Login Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==-1) {
							JOptionPane.showMessageDialog(null, "System error report", "Login Message",JOptionPane.WARNING_MESSAGE);
						}
					 
					 
					 
					 
					 
				 }
		
				
			}
			
			
		});
		

		
		
	}
}
