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
	
	
	Object columns[] ={"Staff Work Number","Permissions","Staff Name","Staff Address","Staff Email"};//Title Information
	JTable tableL=null;//Forms
	JScrollPane jscrollpane;//Scroll bar
	public static DefaultTableModel  model;//Defining the control of the form
	
	final int WIDTH=500;//Set the width of the top-level frame
	final int HEIGHT=400;//Set the height of the top-level frame
	JFrame jframe=new JFrame();
	
	public DellStaffWindows() {
		

		init();
		jframe.setVisible(true); //Set whether the current window can be displayed
		jframe.setResizable(false);//The size of the window cannot be changed
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Set the default closing method
		jframe.validate();//Making components work
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	void init() {
		
		
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);//To center the window
		jframe.setTitle("Delete staff accounts");
		
		//A label, a text box, a button, and a flow layout.
		jframe.setLayout(new FlowLayout(FlowLayout.LEFT));//Set to left alignment
		
		JLabel JL1=new JLabel("Staff Work Number");
		jframe.add(JL1);
		JTextField JT1=new JTextField(12);
		jframe.add(JT1);
		
		JButton JB1=new JButton("Delete Staff accounts");
		jframe.add(JB1);
		JButton JB2=new JButton("Check Staff Account Number");
		jframe.add(JB2);
		table();
		jframe.add(jscrollpane);
		
		JB1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Pass in the contents of the text box
				String sqlStr="delete from users where account=?";
				
				String  data[]=new String [1];
				data[0]=JT1.getText();
				int a=Tool.dellData(sqlStr, data);
				if(a==0) {
					JOptionPane.showMessageDialog(null, "Please check if the account exists", "Message",JOptionPane.WARNING_MESSAGE);
				}
				if(a==-1) {
					JOptionPane.showMessageDialog(null, "Please check the account input content", "Message",JOptionPane.WARNING_MESSAGE);
				}
				if(a>=1) {
					JOptionPane.showMessageDialog(null, "Deleted successfully", "Message",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sqlStr="select account,if(pow=1,'Staff Account','Manager'),sname,saddress,semail from users";
				
				
				ResultSet rs = Tool.showData(sqlStr, null);
				 Tool.addDataTable(rs, model, 5);
				
				
				
			}
		
		});
		
		
		
	}
	void table() {
		
		tableL=getTable();//Initialization Form
		jscrollpane=new JScrollPane(tableL);//Add a browse pane
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));//Setting the size of a table
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//Display sliding components in the window
		//jscrollpane.setBounds(0, 170, WIDTH-30, 360);
		jscrollpane.setPreferredSize(new Dimension(WIDTH-30,320));//Setting the size of a table
		
	}
	
	JTable getTable() {
		//Create a table if it is empty
		if(tableL==null) {
			tableL=new JTable();//Create
			model=new DefaultTableModel() {
				//Add some control over the table
				//Set the table to be immovable and non-editable
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//Set to the mode of the table
		
		tableL.getTableHeader().setReorderingAllowed(false);//Make the table non-draggable
		tableL.getTableHeader().setResizingAllowed(false);//Make the table unchangeable in size

			
			
		}
		
		
		
		return tableL;
	}
}
