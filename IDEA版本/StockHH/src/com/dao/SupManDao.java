package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.until.DBUtil;

public class SupManDao {
	
	//实现 添加供应删 ，删除供应商  
	//添加旗下子产品
	public static Connection con=DBUtil.conn;
	
	
	
	
	//定义一个整数的返回值确定 状体 0表示失败  1表示成功
	public static int wiretSup(String name) {
		
		PreparedStatement preSql;//预处语句
		int  num;//存放结果的
		String sqlStr="insert into supplier(`name`) values (?)";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		
		//采用预处理的方式
	}
	//删除供应商 为0的时候表示删除失败 为1的时候表明删除成功
public static int dellSup(String name) {
		
		PreparedStatement preSql;//预处语句
		int  num;//存放结果的
		String sqlStr="delete from supplier where `name`=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		
		//采用预处理的方式
	}
	//将运营商和子产品 都写入  数据
//同时写入父亲和孩子
public static int wiretSupSun(String subname ,String sunname) {
	
	PreparedStatement preSql;//预处语句
	int  num;//存放结果的
	String sqlStr="insert into product (`name`,`supname`) VALUES(?,?)";
	
	try {
		preSql=con.prepareStatement(sqlStr);
		preSql.setString(1, sunname);
		preSql.setString(2, subname);
	
		num=preSql.executeUpdate();
		return num;//添加容易
		
	}catch(SQLException e) {
		return 3;
	}
	
	//采用预处理的方式
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//读取全部
	public static void readSup(JComboBox cmb1) {
		
		//移除传递过来的所有项目
		cmb1.removeAllItems();
		cmb1.addItem("--Supplier--");
		
		int star=0;//为0表示没有数据  1有数据
		PreparedStatement preSql;//预处语句
		String sqlStr="select * from supplier";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
			//执行到这个表名有数据 
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
	//读取子产品 传递过来的是一个字符串 和他供应商
   public static void readSun(JComboBox cmb1,String sup) {
		
		//移除传递过来的所有项目
		cmb1.removeAllItems();
		cmb1.addItem("--Product--");
		
		int star=0;//为0表示没有数据  1有数据
		PreparedStatement preSql;//预处语句
		String sqlStr="select * from product where supname=?";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();
			while(rs.next()) {
			//执行到这个表名有数据 
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
	   
		PreparedStatement preSql;//预处语句
		int  num;//存放结果的
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
