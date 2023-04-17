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
	
	final int WIDTH=730;//���ö����ܵĿ��
	final int HEIGHT=50;//���ö����ܵĸ߶�

	
	//��������
	Object columns[] ={"ID","Supplier","Name","Time","Num","Price","Total Num"};//������Ϣ
	JTable tableL=null;//���
	JScrollPane jscrollpane;//������
	public static DefaultTableModel  model;//������Ŀ���Ȩ
	
	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	
	public InStockPan(int x,int y,int width,int height) {
		//��һ�� w  h �Ǳ�ʾ����λ�� �ڶ�����ʾ //�������Ĵ�С
		this.setBounds(x, y, width, height);
		init();
		
		
		
	}
	
	
	void init() {
		//���ÿղ���
		this.setLayout(null);
		//��Ʒ�����Ϣ����Ʒ���ƣ���Ʒ���ʱ�䣬��Ʒ���۸���Ʒ�����������Ʒ��棬��Ʒ��Ӧ��
		
		
		//��Ҫ��������
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));//��������˼
		//�������ӵĴ�С��λ��
		jpan1.setBounds(0, 0, WIDTH-20, 70);
		jpan1.setOpaque(false);
		
		this.add(jpan1);
	
		// ����5����ť 
		JButton JB1=new JButton("Save");
		jpan1.add(JB1);
		
		// ����5����ť 
		JButton JB2=new JButton("Delete");
		jpan1.add(JB2);
		
		// ����5����ť 
		JButton JB3=new JButton("Change");
		jpan1.add(JB3);
		
		// ����5����ť 
		JButton JB4=new JButton("Find");
		jpan1.add(JB4);
		

		
		//���  4����ǩ  3���ı���  1��������  ����һ�����Ӷ�
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));//��������˼
		//�������ӵĴ�С��λ��
		jpan2.setBounds(0, 60, WIDTH-20, 100);
	
		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);

		
		
		cmbSupName=new JComboBox();    //����JComboBox
		cmbSupName.addItem("--Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Name");
		jpan2.add(JL2);
		//_____________________________________________
	
		jpan2.setBorder(BorderFactory.createTitledBorder(""));
		jpan1.setBorder(BorderFactory.createTitledBorder(""));
		this.setBorder(BorderFactory.createTitledBorder(""));
	
		
		cmbStockName=new JComboBox();    //����JComboBox
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
		//��һ�����
		//________________________________________________________________
		//��Ӽ���
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�����ݻ�ȡ  д�뵽���ݿ�����
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
		//������ļ���
		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				SupManDao.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
				
			}
			
		});
		
		//��ѯ��ѯ���а�
		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//���ֲ鷨������ȫ�� �Ͳ��ҵ��� 
			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {
				//�����ȫ��
				rs = InStockDao.findStockallData();
				//����һ���洢���ݵ�rs  ��һ�����   ����Ҫһ������ ����
			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No relative data", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {
				//����ҵ���
				
				rs=InStockDao.findStockoneData(num);
				ResultSet rs1 = InStockDao.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					
					//��������������
					
			
					
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
		
		//ɾ����ť 
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ֱ������  ID����ɾ��
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
		//��������
		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sup=null;//��Ӧ��
				String sun=null;//�Ӳ�Ʒ
				String num=null;//����
				String pric=null;//�۸�
				String ID=null;//�۸�
				
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the ID number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {
						//��˵��û��ѡ��
						
						JOptionPane.showMessageDialog(null, "Please select a supplier", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Please select a product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "The number of products cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Product price cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					}else {
						
						//д�� 
						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumIn.getText();
						pric=stockPricIn.getText();
						ID=stockNum.getText();
						//���ĸ�ֵ�������ݿ�
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
		
		tableL=getTable();//��ʼ�����
		jscrollpane=new JScrollPane(tableL);//���һ���������
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));//��������ô�С
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//�����������ʾ�ڴ�����
		jscrollpane.setBounds(0, 170, WIDTH-20, 360);
		
	}
	
	JTable getTable() {
		//������Ϊ���򴴽����
		if(tableL==null) {
			tableL=new JTable();//���� 
			model=new DefaultTableModel() {
				//���һЩ�Ա��Ŀ��� ���ñ�� ���ɶ�  ���ɱ༭
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);//����Ϊ����ģʽ
		
		tableL.getTableHeader().setReorderingAllowed(false);//�ñ�񲻿��϶�
		tableL.getTableHeader().setResizingAllowed(false);//�ñ�񲻿��϶�
			
		//�п� ������  �����ñ�񲻿ɱ༭
			
			
		}
		
		
		
		return tableL;
	}
	
	private void myUpdateUI() {
	SwingUtilities.updateComponentTreeUI(this);//��ӻ�ɾ�������,���´���


	}
	

}
