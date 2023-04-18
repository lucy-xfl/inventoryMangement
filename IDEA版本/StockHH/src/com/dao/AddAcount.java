package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.until.DBUtil;

public class AddAcount {
	static Connection con=DBUtil.conn;
	
	public static int  writeAccount(String account,String password,String name,String address,String emain) {
		PreparedStatement preSql;//Preprocessing Statements
		
		//int num1 = Integer.parseInt(num);//Convert characters to integers

		String sqlStr="insert into users(account,`password`,sname,saddress,semail) values(?,?,?,?,?)";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			preSql.setString(3, name);
			preSql.setString(4, address);
			preSql.setString(5, emain);
			
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			
			return 3;
		}
	}
}
