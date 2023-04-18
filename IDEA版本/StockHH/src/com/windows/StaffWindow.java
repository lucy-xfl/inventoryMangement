package com.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.dao.SupManDao;

import com.staff.panl.*;
import com.tool.Tool;

public class StaffWindow {

	String buton[] ={"    Goods Inbound    ","    Goods Outbound    "};//the name of the button
	String butonName[] ={"stockIn","stockOut"};//Name to distinguish different buttons
	
	final int WIDTH=900;//Set the width of the top-level frame
	final int HEIGHT=600;//Set the height of the top level frame
	public JFrame jframe=new JFrame();
	
	public StaffWindow() {
		

		init();
		jframe.setVisible(true); //Set whether the current window can be displayed
		jframe.setResizable(false);//The size of the window cannot be changed
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Set the default closing method
		jframe.validate();//Making components work
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	void init() {
		
		
		jframe.setLayout(null);//Set empty layout
		jframe.setTitle("Inventory Management System");
		//Window centering
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1=new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		
		//Set the position and size of the first JPanel
		jpanel1.setBounds(5, 5, 160, HEIGHT-10);

		jpanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		

		//Add a menu bar to put account management and add suppliers
		JMenuBar menubar = new JMenuBar();//Create a menu bar
		JMenu menu = new JMenu("Account Management");
		//JMenu menu1 = new JMenu("Inventory Management");
		JMenuItem item1_1 = new JMenuItem("Change Personal Information",new  ImageIcon("src/img/item3.png"));
		menu.add(item1_1);
		menubar.add(menu);//Put the menu in the menu bar
		jframe.setJMenuBar(menubar);
	
		
		JMenu menu2 = new JMenu("System");
		JMenuItem item2_2 = new JMenuItem("Deregister", new ImageIcon("src/img/it1.png"));
		JMenuItem item2_3 = new JMenuItem("Logout", new ImageIcon("src/img/it2.png"));
		 menu2.add(item2_2);
		 menu2.add(item2_3);
		 menubar.add(menu2);
		 //Deregister
		 item2_2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//Close the current screen
					//Open the login screen
					jframe.dispose();
					Login login=new Login("Inventory Management System");
					
				}
				
			});
		 //Logout
		 item2_3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jframe.dispose();
				}
				
			});
		
		
		//Open the account to add the staff account finished
		
		item1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangOwnMeg chan=new ChangOwnMeg();
				String data[]=new String[1];
				data[0]=Login.jtextfield.getText();
				String sqlStr="select  sname,saddress,semail from users  where account=?";
				ResultSet rs = Tool.showData(sqlStr,data );
				try {
					rs.next();
					chan.JT1.setText(rs.getString("sname"));
					chan.JT2.setText(rs.getString("saddress"));
					chan.JT3.setText(rs.getString("semail"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			
		});

		
		
		
		
		
		
		//Inbound pane
		InStockPan inpan=new InStockPan(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//Outbound pane
		OutStockPan outpan=new OutStockPan(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(outpan, (Integer) (JLayeredPane.PALETTE_LAYER));

		
		

		jpanel2.setBounds(215-50, 5, 680+50, HEIGHT-10);//Set size of JPanel2
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		jpanel1.setBackground(new Color(255,239,213));
		
		
		
		for(int i=0;i<buton.length;i++) {
			
			JButton bu=null;

			if(i==0) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu1.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==1) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu2.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}

			bu.addActionListener(new ActionListener() {
		
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					JButton jbl=(JButton)e.getSource();
					if(jbl.getName().equals(butonName[0])) {
						//Move the JPanel inpan to the top
						jpanel2.moveToFront(inpan);
						SupManDao.readSup(InStockPan.cmbSupName);
						
					
						
					}
					
					if(jbl.getName().equals(butonName[1])) {
						//Move the JPanel outpan to the top
						jpanel2.moveToFront(outpan);
						SupManDao.readSup(OutStockPan.cmbSupName);
						
					}
			
					
					
					
					
				}
				
			});
			
			
			
		}
		
		
		
		

		
		
		
		
		
	}


	
	
}
