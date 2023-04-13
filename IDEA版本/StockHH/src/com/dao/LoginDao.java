package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.until.DBUtil;

public class LoginDao {
	 //处理登录事件的类
	static Connection con=DBUtil.conn;//将连接的con传过来
	//登录状态  成功true 失败则fails
	public static boolean  loginStar(String account ,String password ) {
		//第一个账号，第二个是密码  预处理 
	
		PreparedStatement preSql;//预处语句
		ResultSet rs;//存放结果的
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//将结果放到rs里面
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
	//检验账号权限
	public static  int   loginPow(String account ,String password ) {
		//第一个账号，第二个是密码  预处理 
	
		PreparedStatement preSql;//预处语句
		ResultSet rs;//存放结果的
		String sqlStr="select * from users where account=? and password =?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			rs=preSql.executeQuery();//将结果放到rs里面
			if(rs.next()) {
				if(rs.getString("pow").equals("2")) {
					//管理员用户
					return 2;
					
				}else {
					//就是普通用户
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
