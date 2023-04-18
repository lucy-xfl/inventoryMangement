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
	
	final int WIDTH=730;
	final int HEIGHT=50;

	
	//��������
	Object columns[] ={"ID","Supplier","Stock Name","Out-time","Num","Price","Total-num","Account"};//������Ϣ
	JTable tableL=null;
	JScrollPane jscrollpane;
	public static DefaultTableModel  model;
	
	public static JTextField stockPricOut;
	public static JTextField stockNumOut;
	public static JTextField stockUser;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	public OutStockPan(int x,int y,int width,int height) {

		this.setBounds(x, y, width, height);
		init();
	}
	
	
	void init() {

		this.setLayout(null);

	
		

		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

		jpan1.setBounds(0, 0, WIDTH-20, 50);

		
		this.add(jpan1);
	

		JButton JB1=new JButton("Depot");
		jpan1.add(JB1);
		

		JButton JB2=new JButton("Delete");
		jpan1.add(JB2);
		

		JButton JB3=new JButton("Change");
		jpan1.add(JB3);
		

		JButton JB4=new JButton("Find");
		jpan1.add(JB4);
		
		
		

		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,12,15));

		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);
		this.setBorder(BorderFactory.createTitledBorder(""));
		
		
	
		cmbSupName=new JComboBox();
		cmbSupName.addItem("--Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Name");
		jpan2.add(JL2);
		
		
		cmbStockName=new JComboBox();
		//cmbStockName.addItem("--Stock Name--");
		jpan2.add(cmbStockName);
		
		
		
		JLabel JL3=new JLabel("Num");
		jpan2.add(JL3);
		
		stockNumOut=new JTextField(6);
		jpan2.add(stockNumOut);
		
		
		
		JLabel JL4=new JLabel("Price");
		jpan2.add(JL4);
		
		stockPricOut=new JTextField(6);
		jpan2.add(stockPricOut);
		

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
		

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
			}
			
		});

		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�����ݻ�ȡ  д�뵽���ݿ�����
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

		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {

				rs = OutStockDao.findStockallData();

			int a=Tool.addDataTable(rs, model,8 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relevant data available", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {

				
				rs=OutStockDao.findStockoneData(num);
				ResultSet rs1 = OutStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					String user=rs1.getString("user");
					

			
					
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

		
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		

		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sup=null;
				String sun=null;
				String num=null;
				String pric=null;
				String ID=null;
				String user=null;
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {

						
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
						

						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumOut.getText();
						pric=stockPricOut.getText();
						ID=stockNum.getText();
						user=stockUser.getText();

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
		
		tableL=getTable();
		jscrollpane=new JScrollPane(tableL);
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrollpane.setBounds(0, 170, WIDTH-20, 360);
		
	}
	
	JTable getTable() {

		if(tableL==null) {
			tableL=new JTable();
			model=new DefaultTableModel() {
				//���һЩ�Ա��Ŀ��� ���ñ�� ���ɶ�  ���ɱ༭
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//����Ϊ����ģʽ
		
		tableL.getTableHeader().setReorderingAllowed(false);
		tableL.getTableHeader().setResizingAllowed(false);
			

			
			
		}
		
		
		
		return tableL;
	}
	
	
	

}
