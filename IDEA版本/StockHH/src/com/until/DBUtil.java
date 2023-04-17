package com.until;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
public class DBUtil {
	
	public static Connection conn=null;
	
	public DBUtil(String account ,String password,String database){
		//Database account, database password, database name
		
		//Connection drive
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded driver successfully");
			
		}catch(Exception e) {
			System.out.println("Failed to load driver");
		}
		
		try {
			
			String url="jdbc:mysql://localhost:3306/"+database+"?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&AllowPublicKeyRetrieval=True";
			conn=DriverManager.getConnection(url, account, password);
			System.out.println("Connection to database successful");
			
		}catch(SQLException e1) {
			System.out.println("Failed to link database");
			String temp=e1.getMessage();
			System.out.println(temp);
			String[] arr1=temp.split(" ");
			if(arr1[0].equals("Unknown")) {
			System.out.println("Please create the name£º"+arr1[2]+"database");
			}
			if(arr1[0].equals("Access")) {
			System.out.println("Please check if the database password is correct: Database password error");
			}
		}
	}
	//Check if the connection is closed meaning automatically closed
	//First parameter
	//Interface to read the database
	//Pre-existing interfaces
	public static void  CloseDB(ResultSet rs, PreparedStatement stm) {
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stm!=null)
		{
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
