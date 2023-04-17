package com.staff.panl;

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
import com.dao.StaffStockDao;
import com.dao.SupManDao;
import com.tool.Tool;
import com.windows.Login;

public class OutStockPan extends JPanel {
	
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top level frame

	
	//Data of the table

	//Title Information
	Object columns[] ={"Order Number","Suppliers","Product Name","Outbound time","Number of products","Product Price","Merchandise Inventory","User"};

	//Create table and scroll-pane
	JTable tableL=null;
	JScrollPane jscrollpane;

	//Defining the control of the form
	public static DefaultTableModel  model;
	
	public static JTextField stockPricOut;
	public static JTextField stockNumOut;
	public static JTextField stockUser;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;

	//Constructor method to initialize the panel
	public OutStockPan(int x,int y,int width,int height) {
		//Set the location and size
		this.setBounds(x, y, width, height);
		init();
	}

	//Initialize the panel
	void init() {

		this.setLayout(null);

		//Set panel one
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

		//Set the size and location of the panel one
		jpan1.setBounds(0, 0, WIDTH-20, 50);
		
		this.add(jpan1);

		//Declare 4 buttons
		JButton JB1=new JButton("Start Outbound");
		jpan1.add(JB1);
		

		JButton JB2=new JButton("Delete Outbound");
		jpan1.add(JB2);
		

		JButton JB3=new JButton("ChangeOutbound");
		jpan1.add(JB3);
		

		JButton JB4=new JButton("Find Outbound");
		jpan1.add(JB4);
		

		//Set panel two(including 6 labels, 4 text fields, 2 dropdown-boxes)
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,12,15));
		//Set the size and location of the panel two
		jpan2.setBounds(0, 60, WIDTH-20, 100);
		this.setBorder(BorderFactory.createTitledBorder(""));

		//Label for merchandise suppliers
		JLabel JL1=new JLabel("Merchandise Suppliers");
		jpan2.add(JL1);

		//Dropdown-box for selecting a supplier
		cmbSupName=new JComboBox();
		cmbSupName.addItem("--Please select a supplier--");
		jpan2.add(cmbSupName);

		//Label for product name
		JLabel JL2=new JLabel("Product Name");
		jpan2.add(JL2);

		//Dropdown-box for selecting a product
		cmbStockName=new JComboBox();
		cmbStockName.addItem("--Please select the product--");
		jpan2.add(cmbStockName);


		//Label for number of products
		JLabel JL3=new JLabel("Number of products");
		jpan2.add(JL3);


		stockNumOut=new JTextField(6);
		jpan2.add(stockNumOut);

		//Label for product price
		JLabel JL4=new JLabel("Product Price");
		jpan2.add(JL4);

		//Text field for entering product price
		stockPricOut=new JTextField(6);
		jpan2.add(stockPricOut);
		
		//Buyers
		JLabel JL5=new JLabel("Clients");
		jpan2.add(JL5);
		
		stockUser=new JTextField(8);
		jpan2.add(stockUser);

		//Label for order number
		JLabel JL6=new JLabel("Order Number");
		jpan2.add(JL6);


		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);

		//Set borders for the panels and add them to the main panel
		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		
		this.add(jpan2);

		//Create the table and add it to a scroll-pane
		table();
		this.add(jscrollpane);

		//add Listener to drop-down boxes of selecting supplier
		cmbSupName.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//Call the readSun method of the SupManDao class to update the cmbStockName
				SupManDao.readSun(cmbStockName, (String)cmbSupName.getSelectedItem());
			}
		});

		//Add listener to jb1
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Get the data and write it to the database
				//InStockDao.writeStock(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select the product", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the quantity", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the price", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockUser.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please enter your username", "Message",JOptionPane.WARNING_MESSAGE);
					
				}
				else{
					//Getting the selected supplier, product, quantity, price, and person values
					String sup=(String)cmbSupName.getSelectedItem();
					String sun=(String)cmbStockName.getSelectedItem();
					String num=stockNumOut.getText();
					String pri=stockPricOut.getText();
					String usrname=stockUser.getText();
					String peo=Login.jtextfield.getText();
					
					//Call the writeStock method with the 6 values and storing the result in the variable a
					int a=OutStockDao.writeStock(sup, sun, num, pri, usrname,peo);

					if(a==0) {
						JOptionPane.showMessageDialog(null, "Failed to add", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please fill in the price or data with the numeric type", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Added successfully", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Insufficient inventory to leave the warehouse", "Message",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		//Add listener to jb4
		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//get the stock number from the stockNum text field
			String num=stockNum.getText();
			ResultSet rs ;

			//if stockNum is empty, call the InStockDao.findStockallData method and return the result set
			if(num.equals("")) {
				//Find All, and store the data to rs
				rs = OutStockDao.findStockallData();
				//Use the Tool.addDataTable method to pass the rs(store data), table and set the length of table
				int a=Tool.addDataTable(rs, model,8 );
				//if no data, show message
				if(a==0) {
					JOptionPane.showMessageDialog(null, "No relevant data available", "Message",JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				//Find specific
				rs=OutStockDao.findStockoneData(num);
				ResultSet rs1 = OutStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					//get the supplier name, stock name, quantity, price and user from the rs
					String sup = rs1.getString("supname");
					String sun = rs1.getString("stockname");
					String nu =	rs1.getString("num");
					String pr =	rs1.getString("pric");
					String user = rs1.getString("user");

					//Loop through the two dropdown boxes(select the supplier and select the product)
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


				//Use the Tool.addDataTable method to pass the rs(store data), table and set the length
				int a=Tool.addDataTable(rs, model,8 );
				//if no data, show message
				if(a==0) {
					JOptionPane.showMessageDialog(null, "No relevant data available", "Message",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		});

		//Add listener to jb2
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Directly enter the ID to delete
				//Get the stock number from the stockNum text field
				String num=stockNum.getText();
				if(num.equals("")) {
					//Display a warning message if the stock number is empty
					JOptionPane.showMessageDialog(null, "Please enter the deletion number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					//Call the showTime method to check if the number can be deleted or not
					int c=StaffStockDao.showTimeOut(num);

					//If c is equal to 1, call the method dellStockData() to delete the record
					if(c==1) {
						int a=OutStockDao.dellStockData(num);
						// Display messages based on the return value of the dellStockData method
						if(a==0) {
							JOptionPane.showMessageDialog(null, "Please check if the input number exists", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "Deleted successfully", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==3) {
							JOptionPane.showMessageDialog(null, "Please check if the input number is a number", "Message",JOptionPane.WARNING_MESSAGE);
						}
					
					}else {
						System.out.println(c);
						JOptionPane.showMessageDialog(null, "The number is incorrect or has been over three minutes", "Message",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		//Add listener to jb3
		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sup=null;//supplier
				String sun=null;//Sub-products
				String num=null;//quantity
				String pric=null;//price
				String ID=null;//ID number
				String user=null;
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					// Check if the supplier and sub-product fields are selected, and if the quantity and price fields are not empty
					if(cmbSupName.getSelectedIndex()==0) {

						JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(cmbStockName.getSelectedIndex()==0) {


						JOptionPane.showMessageDialog(null, "Please select a sub-product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumOut.getText().equals("")) {


						JOptionPane.showMessageDialog(null, "The number of products cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricOut.getText().equals("")) {


						JOptionPane.showMessageDialog(null, "Product price cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockUser.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "User name cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else{

						//Check if the ID number is valid and within the time limit for deletion
						int c=StaffStockDao.showTimeOut(stockNum.getText());
						if(c==1) {
							//Retrieve the selected values and update the inventory data
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

						}else {
							System.out.println(c);
							JOptionPane.showMessageDialog(null, "Please check the number input format or it has been more than 3 minutes", "Message",JOptionPane.WARNING_MESSAGE);
						}
					}
					
				}
			}
		});
		
	}

	void table() {
		
		tableL=getTable();//Initialize table
		jscrollpane=new JScrollPane(tableL);//Creates a JScrollPane to allow the JTable to scroll
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));//Sets the preferred size of the JTable to allow for proper display
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//Set vertical scrollbar to display in the window
		jscrollpane.setBounds(0, 170, WIDTH-30, 360);
		
	}


	//A method returns a JTable object to display data
	JTable getTable() {
		//Create a table if it is empty
		if(tableL==null) {
			tableL=new JTable();
			model=new DefaultTableModel() {
				//Overrides isCellEditable() to prevent the user from editing the data in the JTable
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};

		//Sets the column identifiers for the JTable
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//Set to the mode of the table

		//Disables the user from reordering and resizing columns in the JTable
		tableL.getTableHeader().setReorderingAllowed(false);
		tableL.getTableHeader().setResizingAllowed(false);
			
		}
		return tableL;
	}

}
