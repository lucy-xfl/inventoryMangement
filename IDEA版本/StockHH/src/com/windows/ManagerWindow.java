package com.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.dao.SupManDao;
import com.manage.item.AddStaffAccout;
import com.manage.panl.InStockPan;
import com.manage.panl.OutStockPan;
import com.manage.panl.ShowInOutStock;
import com.manage.panl.SupplierPan;
import com.sta.panl.InComeStic;
import com.sta.panl.InOutPanel;
import com.sta.panl.inBarPanel;
import com.sta.panl.outBarPanel;
import com.tool.Tool;

public class ManagerWindow {

	String buton[] ={"    Goods Inbound    ","   Goods Outbound    ","    Add Supplier    ","   Search History   ","Inbound Statistics","Outbound Statistics","Profit and Loss Statistics","Goods Flow Curve"};//the name of the button
	String butonName[] ={"stockIn","stockOut","supplier","showdata","stiffdata","inoutdata","income","inoutstock"};//Name to distinguish different buttons

	final int WIDTH=900;//Set the width of the top-level frame
	final int HEIGHT=600;//Set the height of the top level frame
	public JFrame jframe=new JFrame();
	public static InOutPanel inout;
	public ManagerWindow() {
		

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
		JMenuItem item1_1 = new JMenuItem("Add Staff Account",new  ImageIcon("src/img/item1.png"));
		JMenuItem item1_2 = new JMenuItem("Delete Staff Account",new  ImageIcon("src/img/item2.png"));
		JMenuItem item1_3 = new JMenuItem("Personal Information Management",new  ImageIcon("src/img/item3.png"));
		menu.add(item1_1);
		menu.add(item1_2);
		menu.add(item1_3);
		
		
		JMenuItem item2_1 = new JMenuItem("Change Staff Account Information");
		//menu1.add(item2_1);
	

		
		menubar.add(menu);//Put the menu in the menu bar
	//	menubar.add(menu1);
		jframe.setJMenuBar(menubar);
	

		JMenu menu2 = new JMenu("System");
		JMenuItem item2_2 = new JMenuItem("Deregister",new  ImageIcon("src/img/it1.png"));
		JMenuItem item2_3 = new JMenuItem("Logout",new  ImageIcon("src/img/it2.png"));
		 menu2.add(item2_2);
		 menu2.add(item2_3);
		 menubar.add(menu2);
		 //Deregister function
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
		 //Logout function
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
				AddStaffAccout a=new AddStaffAccout();
			}
			
		});
		//Click to delete staff account
		item1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DellStaffWindows a=new DellStaffWindows();
				
				
			}
			
		});
		//Click to change personal information
		//Click to delete staff account
		item1_3.addActionListener(new ActionListener() {

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
		//Add supplier window
		SupplierPan suppan=new SupplierPan(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(suppan, (Integer) (JLayeredPane.PALETTE_LAYER));
		//Search History
		//Show data window
		ShowInOutStock showdata=new ShowInOutStock(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(showdata, (Integer) (JLayeredPane.PALETTE_LAYER));
		

		//jpanel for statistics
		inBarPanel inbar=new inBarPanel(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(inbar, (Integer) (JLayeredPane.PALETTE_LAYER));
		//Statistics Outbound
		outBarPanel outbar=new outBarPanel(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(outbar, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//Sector statistics
		InComeStic income=new InComeStic(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(income, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//Curve statistics chart
		inout=new InOutPanel(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(inout.JP(), (Integer) (JLayeredPane.PALETTE_LAYER));
		
		
		
		
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
			if(i==2) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu3.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==3) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu4.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==4) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu5.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==5) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu6.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==6) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu7.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
				bu.setPreferredSize(new Dimension(150, 30));
			}
			if(i==7) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu8.png"));
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
						//Move the JPanel of Goods Inbound to the top
						jpanel2.moveToFront(inpan);
						SupManDao.readSup(InStockPan.cmbSupName);
						
						
						
					}
					
					if(jbl.getName().equals(butonName[1])) {
						//Move the JPanel of Goods Outbound to the top
						jpanel2.moveToFront(outpan);
						SupManDao.readSup(OutStockPan.cmbSupName);
						
					}
					
					if(jbl.getName().equals(butonName[2])) {
						//Move the JPanel of Add Supplier to the top
						jpanel2.moveToFront(suppan);
						
						SupManDao.readSup(suppan.cmb1);
						
						
					}
					if(jbl.getName().equals(butonName[3])) {
						//Move the JPanel of Show Data to the top
						jpanel2.moveToFront(showdata);
						
						
						
						
					}
					if(jbl.getName().equals(butonName[4])) {
						//Move the JPanel of inbar to the top
						jpanel2.moveToFront(inbar);
						//Moving to the top requires a bit of data initialization.
					
						inbar.rep();//Remove
						inbar.rep1();//Add on
						SwingUtilities.updateComponentTreeUI(inbar);//Update window after adding or removing components

						
						//
						
					}
					if(jbl.getName().equals(butonName[5])) {
						//Move the JPanel of outbar to the top
						jpanel2.moveToFront(outbar);
						//Moving to the top requires a bit of data initialization.
					
						outbar.rep();//Remove
						outbar.rep1();//Add on
						SwingUtilities.updateComponentTreeUI(outbar);//Update window after adding or removing components

						

						
					}
					if(jbl.getName().equals(butonName[6])) {
						//Move the JPanel of income to the top
						jpanel2.moveToFront(income);
						//Moving to the top requires a bit of data initialization.
					
						income.rep();//Remove
						income.rep1();//Add on
						SwingUtilities.updateComponentTreeUI(income);//Update window after adding or removing components


						
					}
					
					if(jbl.getName().equals(butonName[7])) {
						//Move the JPanel of inout to the top
						jpanel2.moveToFront(inout.JP());
						//Moving to the top requires a bit of data initialization.
					
					
						SwingUtilities.updateComponentTreeUI(inout.JP());//Update window after adding or removing components
						SupManDao.readSup(InOutPanel.cmbSupName);

						
					}
					
					
				}
				
			});
			
			
			
		}
		
		
		
		

		
		
		
		
		
	}


	
	
}
