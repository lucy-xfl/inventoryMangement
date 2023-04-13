package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.until.DBUtil;

public class LoginDao {
	 //�����¼�¼�����
	static Connection con=DBUtil.conn;//�����ӵ�con������
	//��¼״̬  �ɹ�true ʧ����fails
	public static boolean  loginStar(String account ,String password ) {
		//��һ���˺ţ��ڶ���������  Ԥ���� 
	
		PreparedStatement preSql;//Ԥ�����
		ResultSet rs;//��Ž����
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//������ŵ�rs����
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	
//______________________________________
	//�����˺�Ȩ��
	public static  int   loginPow(String account ,String password ) {
		//��һ���˺ţ��ڶ���������  Ԥ���� 
	
		PreparedStatement preSql;//Ԥ�����
		ResultSet rs;//��Ž����
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//������ŵ�rs����
			if(rs.next()) {
				if(rs.getString("pow").equals("2")) {
					//����Ա�û�
					return 2;
					
				}else {
					//������ͨ�û�
					return 1;
				}
	
			}else {
				return 3;
			}
		}catch(SQLException e) {
			return 3;
		}
	}

}
