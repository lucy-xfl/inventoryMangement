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
	
	final int WIDTH=730;//���ö����ܵĿ��
	final int HEIGHT=50;//���ö����ܵĸ߶�
	
	
	//��������
	Object columns[] ={"ID","Supplier","Stoke name","InTime","Num","Price","Account"};//������Ϣ
	JTable tableL=null;//���
	JScrollPane jscrollpane;//������
	public static DefaultTableModel  model;//������Ŀ���Ȩ
	
	
	
	public ShowInOutStock(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
		
	}
	void init() {
		//һ�����ʵ�ֲ�ѯ����������  ���˺Ų�ѯ
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,25,30));
		
		JLabel JL=new JLabel("Orders");
		this.add(JL);
		JTextField JT1=new JTextField(12);
		this.add(JT1);
		JRadioButton jrb1=new JRadioButton("Find inbound information");
		JRadioButton jrb2=new JRadioButton("Find outbound information");
		
		ButtonGroup bg=new ButtonGroup();;	
		bg.add(jrb1);			//����Ҫ�ѵ�ѡ����밴ť���������в���ʵ�ֵ�ѡ��������
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
				//�ж� �ı��������Ƿ��ж�����������в��ң�û������У������ȫ��
				//�жϵ�ѡ����ѡ����Ǹ�
				
				if(JT1.getText().equals("")) {
					//����ȫ��
					if(jrb1.isSelected()) {//��һ��ѡ����
						//����ҵ�һ��
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//���ҵڶ���
						//����ҵ�һ��
						String sqlStr="select id,supname,stockname,outtime,num,pric,oper from outstock";
						ResultSet rs = Tool.showData(sqlStr, null);
						Tool.addDataTable(rs, model, 7);
						
					}
				}else {
					
					if(jrb1.isSelected()) {//��һ��ѡ����
						//����ҵ�һ��
						String  data[]=new String [1];
						data[0]=JT1.getText();
						String sqlStr="select id,supname,stockname,intime,num,pric,oper from instock where id=?";
						ResultSet rs = Tool.showData(sqlStr, data);
						Tool.addDataTable(rs, model, 7);
						
					}else {
						//���ҵڶ���
						//����ҵ�һ��
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
		
		tableL=getTable();//��ʼ�����
		jscrollpane=new JScrollPane(tableL);//���һ���������
		tableL.setPreferredSize(new Dimension(WIDTH-40,10000));//��������ô�С
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//�����������ʾ�ڴ�����
		jscrollpane.setPreferredSize(new Dimension(WIDTH-40,400));//��������ô�С
		this.add(jscrollpane);
		
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
	
}
