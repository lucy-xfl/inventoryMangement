package com.manage.panl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.dao.SupManDao;

public class  SupplierPan  extends JPanel{
	
	static int num=1;
	final int WIDTH=730;
	final int HEIGHT=50;
	public JComboBox cmb1;
	public static  JTextField  jt1;
	
	public SupplierPan(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
	}
	
	void init() {
	
		this.setLayout(null);
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,30,10));
		jpan1.setBounds(0, 0, WIDTH-20, 100);

		this.add(jpan1);
		
		JLabel jl1=new JLabel("Supplier");
		jt1=new JTextField(30);
		JButton jb1=new JButton("Add");
		JButton jb5=new JButton("Delete");
		
	
		
		
		
		jpan1.add(jl1);
		jpan1.add(jt1);
		jpan1.add(jb1);
		jpan1.add(jb5);
		
		
		//һ����ǩ
		JLabel JL1=new JLabel("Supplier");
		jpan1.add(JL1);

		
	
		
		
		JComboBox cmbSupName = new JComboBox();
		cmbSupName.addItem("--Supplier--");
		jpan1.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Stock Name");
		jpan1.add(JL2);
		
		
		JComboBox cmbStockName = new JComboBox();
		cmbStockName.addItem("--Stock Name--");
		jpan1.add(cmbStockName);
		
		JButton jb=new JButton("DeleteAll");
		jpan1.add(jb);
		
		
		
		//_____________________________________________
	


		
		
		
		
		
		

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
				
			}
			
		});
		
		SupManDao.readSup(cmbSupName);
		
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
				}else if (cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					String sup=(String )cmbSupName.getSelectedItem();
					String sun=(String )cmbStockName.getSelectedItem();
					
					int a=SupManDao.delSunStock(sup, sun);
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Delete successfully", "Message",JOptionPane.WARNING_MESSAGE);
						SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
					}
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Delete failed", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Error", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
				
				
				
				
			}
			
		});
		
		
		
		
		
		
		
		
		

		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));
	
		jpan2.setBounds(0, 110, WIDTH-20,410);
		this.add(jpan2);
		
		JLabel jl2=new JLabel("Supplier");
		cmb1=new JComboBox();
		cmb1.addItem("--Supplier--");
		JButton jb2 =new JButton("Adding its sub-products");
	
		JButton jb3 =new JButton("Save");
		JButton jb4=new JButton("Reset");
		

		
		jpan2.add(jl2);
		jpan2.add( cmb1);
		jpan2.add(jb2);
		jpan2.add( jb3);
		
		jpan2.add( jb4);
		
	
		
		
	
		

		JPanel jp3=new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		jp3.setPreferredSize(new Dimension(200, 350));
		JTextField A=new JTextField(12);
		JLabel B=new JLabel("Stock Name");
		A.setName("sun");
		jp3.add(B);
		jp3.add(A);
		jpan2.add(jp3);
		
		
		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		jp3.setBorder(BorderFactory.createTitledBorder("Add Products"));
		
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				if(num<5) {
					JTextField A=new JTextField(12);
					JLabel B=new JLabel("Stock Name");
					A.setName("sun");
					
					jp3.add(B);
					jp3.add(A);
					myUpdateUI();
					num++;
				}else {
					JOptionPane.showMessageDialog(null, "Add a maximum of 5", "Message",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		
		

		
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(jt1.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Supplier cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					int star=SupManDao.wiretSup(jt1.getText());
					if(star==0) {
						JOptionPane.showMessageDialog(null, "Supplier addition failure", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(star==1) {
						JOptionPane.showMessageDialog(null, "Supplier added successfully", "Message",JOptionPane.WARNING_MESSAGE);
						SupManDao.readSup(cmb1);
						//ˢ��������
						SupManDao.readSup(cmbSupName);
						
					}
					if(star==3) {
						JOptionPane.showMessageDialog(null, "Supplier name duplicate, please rewrite input", "Message",JOptionPane.WARNING_MESSAGE);
					}
				}
				
				
				
				
			}
			
		});	

		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				if(jt1.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Supplier cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					int star=SupManDao.dellSup(jt1.getText());
					if(star==0) {
						JOptionPane.showMessageDialog(null, "Delete supplier add failed please check name", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(star==1) {
						JOptionPane.showMessageDialog(null, "Supplier deletion success", "Message",JOptionPane.WARNING_MESSAGE);
						SupManDao.readSup(cmb1);
						SupManDao.readSup(cmbSupName);

						
					}
					if(star==3) {
						JOptionPane.showMessageDialog(null, "Error: Please check the input content", "Message",JOptionPane.WARNING_MESSAGE);
					}
				}
				
				
				
				
			}
			
		});	
		
		

		
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a=0;
				Component[] tem = jp3.getComponents();
				for(int i=0;i<tem.length;i++) {
					
					

					if(tem[i].getName()!=null&&tem[i].getName().equals("sun")) {

						JTextField TEMP1 = (JTextField)tem[i];
						String text=TEMP1.getText();
			
						if(cmb1.getSelectedIndex()==0) {
							JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
						}else {

							String sup=(String) cmb1.getSelectedItem();

						a=SupManDao. wiretSupSun(sup, text);
						
						}
						
						
					}
				}

				if(a==3) {
					JOptionPane.showMessageDialog(null, "Please check for duplicate sub-product names", "Message",JOptionPane.WARNING_MESSAGE);
				}
				else if(a==0) {
					JOptionPane.showMessageDialog(null, "Add Failed", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Add successfully", "Message",JOptionPane.WARNING_MESSAGE);
					SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
				}
			}
			
		});
		

		jb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jp3.removeAll();
				JTextField A=new JTextField(12);
				JLabel B=new JLabel("Stock Name");
				A.setName("sun");
				jp3.add(B);
				jp3.add(A);
				num=1;
				
				
				
				
				myUpdateUI();
				
				
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	



	private void myUpdateUI() {
	SwingUtilities.updateComponentTreeUI(this);


	}
	
	
	
	
	
}

	
