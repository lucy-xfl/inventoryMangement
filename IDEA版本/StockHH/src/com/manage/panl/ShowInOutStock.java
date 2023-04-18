package com.manage.panl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.tool.Tool;

public class ShowInOutStock extends JPanel{
	
	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top-level frame
	
	
	//Data of the table
	Object columns[] ={"ID","Supplier","Stoke name","InTime","Num","Price","Account"};//标题信息
	JTable tableL=null;//table
	JScrollPane jscrollpane;//Scroll bar
	public static DefaultTableModel  model;//Defining the control of the form
	
	
	
	public ShowInOutStock(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
		
	}
	void init() {
		//One form for querying two things Query by account
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,25,30));
		
		JLabel JL=new JLabel("Orders");
		this.add(JL);
		JTextField JT1=new JTextField(12);
		this.add(JT1);
		JRadioButton jrb1=new JRadioButton("Find inbound information");
		JRadioButton jrb2=new JRadioButton("Find outbound information");
		
		ButtonGroup bg=new ButtonGroup();;	
		bg.add(jrb1);			//You must put the radio box into the button group scope to achieve radio selection ！！！！
		bg.add(jrb2);
		this.add(jrb1);
		this.add(jrb2);
		jrb1.setSelected(true);
		JButton JB=new JButton("History");
		this.add(JB);
		table();
		this.setBorder(BorderFactory.createTitledBorder("Search"));
		
		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Determine if there is something inside the text box, if there is, then do the search, if not, then do the search all
				//Determine which radio box is the selected one
				
				if(JT1.getText().equals("")) {
					//Find All
					if(jrb1.isSelected()) {//The first one selected
						//then look for the first
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//then look for the second
						String sqlStr="select id,supname,stockname,outtime,num,pric,oper from outstock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}
				}else {
					
					if(jrb1.isSelected()) {//The first one selected
						//then look for the first
						String  data[]=new String [1];
						data[0]=JT1.getText();
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock where id=?";
						ResultSet rs = Tool.showData(sqlStr, data);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//Find the second
						//then look for the first
						String  data[]=new String [1];
						data[0]=JT1.getText();
						String sqlStr="select id,supname,stockname,outtime,num,pric,oper from outstock where id=?";
						ResultSet rs = Tool.showData(sqlStr, data);
						Tool.addDataTable(rs, model, 7);
						
					}
					
				}
				
				
				
			}
			
		});
		
		
	}
	void table() {
		
		tableL=getTable();//Initialization Form
		jscrollpane=new JScrollPane(tableL);//Add a browse pane
		tableL.setPreferredSize(new Dimension(WIDTH-40,10000));//Setting the size of a table
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//Display sliding components in the window
		jscrollpane.setPreferredSize(new Dimension(WIDTH-40,400));//Setting the size of a table
		this.add(jscrollpane);
		
	}
	
	JTable getTable() {
		//Create a table if it is empty
		if(tableL==null) {
			tableL=new JTable();//create
			model=new DefaultTableModel() {
				//Add some control over the table Set the table to be immovable and non-editable
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//Set to the mode of the table
		
		tableL.getTableHeader().setReorderingAllowed(false);//Make the table non-draggable
		tableL.getTableHeader().setResizingAllowed(false);

			
			
		}
		
		
		
		return tableL;
	}
	
}
