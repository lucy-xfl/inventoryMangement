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
	
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top-level frame

	
	//Data of the table
	Object columns[] ={"ID","Supplier","Name","Time","Num","Price","Total Num"};//Title Information
	JTable tableL=null;//Forms
	JScrollPane jscrollpane;//Scroll bar
	public static DefaultTableModel  model;//Defining the control of the form
	
	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	
	public InStockPan(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
		
		
		
	}
	
	
	void init() {
		//Set empty layout
		this.setLayout(null);
		//Product inbound information: product name, product inbound time, product inbound price, product inbound quantity, product inventory, product supplier
		
		
		//3JPanels
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));//左对齐的意思
		//Set size of JPanels
		jpan1.setBounds(0, 0, WIDTH-20, 70);
		jpan1.setOpaque(false);
		
		this.add(jpan1);
	
		// Define 4 buttons
		JButton JB1=new JButton("Save");
		jpan1.add(JB1);
		
		// Define 4 buttons
		JButton JB2=new JButton("Delete");
		jpan1.add(JB2);
		
		// Define 4 buttons
		JButton JB3=new JButton("Change");
		jpan1.add(JB3);
		
		// Define 4 buttons
		JButton JB4=new JButton("Find");
		jpan1.add(JB4);
		

		
		//Add 5 labels 3 text boxes 1 drop down box Define a JPanel2
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));//left alignment
		//Set location and size of jpan2
		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);

		
		
		cmbSupName=new JComboBox();    //Create JComboBox
		cmbSupName.addItem("--Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Name");
		jpan2.add(JL2);
		//_____________________________________________
	
		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		this.setBorder(BorderFactory.createTitledBorder(""));
	
		
		cmbStockName=new JComboBox();    //Create JComboBox
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
		//Put a table
		//________________________________________________________________
		//Adding a listener
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Get the data and write it to the database
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
		//Listening to drop-down boxes
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
				
			}
			
		});

		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Two types of search methods, find all and find single
			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {
				//Then find all
				rs = InStockDao.findStockallData();
				//Pass a rs to store data and a table Also need a table length
			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relative data", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {
				//then look for a single
				
				rs=InStockDao.findStockoneData(num);
				ResultSet rs1 = InStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					
					//Iterate through the two drop-down boxes
					
			
					
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
		
		//Delete Button
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Enter ID directly to delete
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
				String sup=null;//supplier
				String sun=null;//sub-project
				String num=null;//amount
				String pric=null;//price
				String ID=null;//ID
				
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {
						//means no selection
						
						JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "The number of products cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Product price cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else {
						
						//write in
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumIn.getText();
						pric=stockPricIn.getText();
						ID=stockNum.getText();
						//Passing 5 values into the database
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
