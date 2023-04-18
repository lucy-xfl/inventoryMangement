package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.until.DBUtil;

public class LoginDao {
	 //Classes that handle login events
	static Connection con=DBUtil.conn;//Pass the connected con over
	//Login Status Success true Failure faults
	public static boolean  loginStar(String account ,String password ) {
		//The first account number, the second is the password Preprocessing
	
		PreparedStatement preSql;//Preprocessing statement
		ResultSet rs;//Storage of the results
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//Put the results in rs
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
	//Check account permissions
	public static  int   loginPow(String account ,String password ) {
		//The first account number, the second is the password Preprocessing
	
		PreparedStatement preSql;//Preprocessing Statement
		ResultSet rs;//Storage of the results
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//Put the results in rs
			if(rs.next()) {
				if(rs.getString("pow").equals("2")) {
					//Manager
					return 2;
					
				}else {
					//Staff
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
