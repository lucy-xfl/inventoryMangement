package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.until.DBUtil;

public class SupManDao {
	
	//ʵ�� ��ӹ�Ӧɾ ��ɾ����Ӧ��  
	//��������Ӳ�Ʒ
	public static Connection con=DBUtil.conn;
	
	
	
	
	//����һ�������ķ���ֵȷ�� ״�� 0��ʾʧ��  1��ʾ�ɹ�
	public static int wiretSup(String name) {
		
		PreparedStatement preSql;//Ԥ�����
		int  num;//��Ž����
		String sqlStr="insert into supplier(`name`) values (?)";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		
		//����Ԥ����ķ�ʽ
	}
	//ɾ����Ӧ�� Ϊ0��ʱ���ʾɾ��ʧ�� Ϊ1��ʱ�����ɾ���ɹ�
public static int dellSup(String name) {
		
		PreparedStatement preSql;//Ԥ�����
		int  num;//��Ž����
		String sqlStr="delete from supplier where `name`=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		
		//����Ԥ����ķ�ʽ
	}
	//����Ӫ�̺��Ӳ�Ʒ ��д��  ����
//ͬʱд�븸�׺ͺ���
public static int wiretSupSun(String subname ,String sunname) {
	
	PreparedStatement preSql;//Ԥ�����
	int  num;//��Ž����
	String sqlStr="insert into product (`name`,`supname`) VALUES(?,?)";
	
	try {
		preSql=con.prepareStatement(sqlStr);
		preSql.setString(1, sunname);
		preSql.setString(2, subname);
	
		num=preSql.executeUpdate();
		return num;//�������
		
	}catch(SQLException e) {
		return 3;
	}
	
	//����Ԥ����ķ�ʽ
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//��ȡȫ��
	public static void readSup(JComboBox cmb1) {
		
		//�Ƴ����ݹ�����������Ŀ
		cmb1.removeAllItems();
		cmb1.addItem("--Supplier--");
		
		int star=0;//Ϊ0��ʾû������  1������
		PreparedStatement preSql;//Ԥ�����
		String sqlStr="select * from supplier";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
			//ִ�е�������������� 
				if(star==0) {
					star++;
				}
				
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
		
			cmb1.repaint();
		}catch(SQLException e) {
		
		}
		
	}
	//��ȡ�Ӳ�Ʒ ���ݹ�������һ���ַ��� ������Ӧ��
   public static void readSun(JComboBox cmb1,String sup) {
		
		//�Ƴ����ݹ�����������Ŀ
		cmb1.removeAllItems();
		cmb1.addItem("--Product--");
		
		int star=0;//Ϊ0��ʾû������  1������
		PreparedStatement preSql;//Ԥ�����
		String sqlStr="select * from product where supname=?";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();
			while(rs.next()) {
			//ִ�е�������������� 
				if(star==0) {
					star++;
				}
				
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
		
			cmb1.repaint();
		}catch(SQLException e) {
		
		}
		
	}
   
   public static int delSunStock(String sup,String sun) {
	   
		PreparedStatement preSql;//Ԥ�����
		int  num;//��Ž����
		String sqlStr="DELETE from product where `name`=? and supname=?;";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sun);
			preSql.setString(2, sup);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
	   
   }
	
	
	

}
