package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.manage.panl.InStockPan;
import com.until.DBUtil;

public class InStockDao {
	
	static Connection con=DBUtil.conn;
	
	
	
	public static int  writeStock(String sup,String sunname,String num1,String pri,String peo) {
		PreparedStatement preSql;//预处语句
		
		//int num1 = Integer.parseInt(num);//将字符转换成整数

		String sqlStr="insert into instock(supname,stockname,intime,num,pric,oper) values(?,?,now(),?,?,?)";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, sunname);
			preSql.setString(3, num1);
			preSql.setString(4, pri);
			preSql.setString(5, peo);
			
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
	}
	//查找单个和查找全部  查找全部 是需要输
	public static ResultSet  findStockoneData(String num) {
		PreparedStatement preSql;//预处语句
		
		//int num1 = Integer.parseInt(num);//将字符转换成整数
		String  data[]=new String [6];
		String sqlStr="select instock.id,instock.supname,instock.stockname,instock.intime,instock.num,instock.pric,product.stock from instock,product where product.supname=instock.supname and product.`name`=instock.stockname and id=?";
		ResultSet rs=null ;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, num);
	
		
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return  rs;
		}
	}
	//查找所有的代码
	public static ResultSet  findStockallData() {
		PreparedStatement preSql;//预处语句
		//int num1 = Integer.parseInt(num);//将字符转换成整数
		String  data[]=new String [6];
		String sqlStr="select instock.id,instock.supname,instock.stockname,instock.intime,instock.num,instock.pric,product.stock from instock,product where product.supname=instock.supname and product.`name`=instock.stockname";
		ResultSet rs=null ;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return  rs;
		}
	}
	public static int dellStockData(String id) {
		PreparedStatement preSql;//预处语句
		
		//int num1 = Integer.parseInt(num);//将字符转换成整数

		String sqlStr="delete from instock where id=?";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,id);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			
			
			if(e.getMessage().equals("Cannot be deleted due to lack of inventory in the warehouse")) {
				return 4;
			}else {
				return 3;
			}
			
		}
	}
	//更改商品记录
	public static int changeStockData(String sup,String sun,String num,String pric,String id) {
	PreparedStatement preSql;//预处语句
		
		

		String sqlStr="UPDATE instock set supname=? ,stockname=? ,num=?,pric=? where id=?";
		int num1=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);
			preSql.setString(2,sun);
			preSql.setString(3,num);
			preSql.setString(4,pric);
			preSql.setString(5,id);
			num1=preSql.executeUpdate();
			return num1;
			
		}catch(SQLException e) {
			if(e.getMessage().equals("Cannot be updated due to lack of inventory in the warehouse")) {
				return 4;
			}else {
				return 3;
			}
			
			
		}
	}
	
	

}
