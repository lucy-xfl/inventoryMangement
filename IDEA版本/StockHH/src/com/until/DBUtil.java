package com.until;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
public class DBUtil {
	
	public static Connection conn=null;
	
	public DBUtil(String account ,String password,String database){
		//数据库账号，数据库密码，数据库名字
		
		//连接驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");
			
		}catch(Exception e) {
			System.out.println("加载驱动失败");
		}
		
		try {
			
			String url="jdbc:mysql://localhost:3306/"+database+"?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&AllowPublicKeyRetrieval=True";
			conn=DriverManager.getConnection(url, account, password);
			System.out.println("连接数据库成功");
			
		}catch(SQLException e1) {
			System.out.println("链接数据库失败");
			String temp=e1.getMessage();
			System.out.println(temp);
			String[] arr1=temp.split(" ");
			if(arr1[0].equals("Unknown")) {
			System.out.println("请建立名字为："+arr1[2]+"数据库");
			}
			if(arr1[0].equals("Access")) {
			System.out.println("请检查数据库密码是否正确：数据库密码错误");
			}
		}
	}
	//检查连接是否关闭的意思自动关闭 第一个参数 读取数据库的接口
	//预处的接口
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
