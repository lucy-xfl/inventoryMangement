package com.sta.panl;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import com.sta.InOutTimeSeriesChart;
import com.sta.InOutTimeSeriesChart2;

public class InOutPnal {

	final int WIDTH=730;//Set the width of the top-level frame
	final int HEIGHT=50;//Set the height of the top level frame
	JPanel  JPM=new JPanel();

	//Define a drop-down box
	public static JComboBox cmbSupName;

	public  InOutPnal(int x,int y,int width,int height) {
		//Set the location and size
		JPM.setBounds(x, y, width, height-80);
		init();
	}
	
	
	void init() {
		//Create the needed panel, labels and drop-down box
		JPanel JP1=new JPanel();
		JP1.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		JLabel JL1=new JLabel("Suppliers");
		JP1.add(JL1);
		cmbSupName = new JComboBox();
		cmbSupName.addItem("--Please select a supplier--");
		JP1.add(cmbSupName);

		JLabel JL2=new JLabel("Warehouse Status");
		JP1.add(JL2);





		//Define two radio buttons
		JRadioButton jrb1=new JRadioButton("Inbound");
		JRadioButton jrb2=new JRadioButton("Outbound");

		//Set default selection inbound
		jrb1.setSelected(true);

		//Create ButtonGroup
		ButtonGroup bg=new ButtonGroup();

		//Add JRadioButtons to ButtonGroup, and add them all to the panel
		bg.add(jrb1);
		bg.add(jrb2);
		JP1.add(jrb1);
		JP1.add(jrb2);
		
		

		//Add two buttons
		JButton JB1=new JButton("View the last three months");
		JP1.add(JB1);
		
		JButton JB2=new JButton("View historical data");
		JP1.add(JB2);

		//Set the outermost component as grid layout
		JPM.setLayout(new GridLayout(2,2,10,10));

		JPM.add(JP1);

		//The first variable is suppliers, the second is inbound
		JPM.add(new InOutTimeSeriesChart("","instock").getChartPanel());

		//this.add(new outBarChart2().getChartPanel());
		JPM.setBorder(BorderFactory.createTitledBorder(""));
		JP1.setBorder(BorderFactory.createTitledBorder("Statistical data"));

		//add listener to jb2
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//Read all the data of a carrier
				if(cmbSupName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "Please select a carrier", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					//get the name of selected supplier
					String sup=(String) cmbSupName.getSelectedItem();

					//used to target the inbound or outbound
					String sqlstr;

					//named the database according to the choice of inbound(jrb1) or outbound(jrb2)
					if(jrb1.isSelected()) {
						sqlstr="instock";
					}else {
						sqlstr="outstock";
						
					}
					
					JPM.remove(1);

					//Update window after adding or removing components
					SwingUtilities.updateComponentTreeUI(JPM);
					JPM.add(new InOutTimeSeriesChart(sup, sqlstr).getChartPanel());

				}
			}
			
		});
		
		//add listener to jb1
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				//Read all the data of a carrier
				if(cmbSupName.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "Please select a carrier", "message",JOptionPane.WARNING_MESSAGE);
				}else {

					//get the name of selected supplier
					String sup=(String) cmbSupName.getSelectedItem();
					//used to target the inbound or outbound
					String sqlstr;

					//named the database according to the choice of inbound(jrb1) or outbound(jrb2)
					if(jrb1.isSelected()) {
						sqlstr="instock";
					}else {
						sqlstr="outstock";
					}
					JPM.remove(1);

					//Update window after adding or removing components
					SwingUtilities.updateComponentTreeUI(JPM);


					JPM.add(new InOutTimeSeriesChart2(sup, sqlstr).getChartPanel());


				}
			}

		});
		
		

		
	}

public JPanel  JP() {
	return JPM;
}
}
