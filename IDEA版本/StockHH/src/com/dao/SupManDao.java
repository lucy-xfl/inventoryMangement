package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.until.DBUtil;

public class SupManDao {
	
	//Implement Add Supply Delete , Delete Supplier
	//Adding its sub-products
	public static Connection con=DBUtil.conn;
	
	
	
	
	//Define the return value of an integer to determine the form 0 means failure 1 means success
	public static int wiretSup(String name) {
		
		PreparedStatement preSql;//Preprocessing Statements
		int  num;//Storage of the results
		String sqlStr="insert into supplier(`name`) values (?)";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}

	}
	//Deleting a vendor A value of 0 indicates a failed deletion A value of 1 indicates a successful deletion
public static int dellSup(String name) {
		
		PreparedStatement preSql;
		int  num;
		String sqlStr="delete from supplier where `name`=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}

	}
	//Write both suppliers and subproducts to the data
public static int wiretSupSun(String subname ,String sunname) {
	
	PreparedStatement preSql;
	int  num;
	String sqlStr="insert into product (`name`,`supname`) VALUES(?,?)";
	
	try {
		preSql=con.prepareStatement(sqlStr);
		preSql.setString(1, sunname);
		preSql.setString(2, subname);
	
		num=preSql.executeUpdate();
		return num;
		
	}catch(SQLException e) {
		return 3;
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Read All
	public static void readSup(JComboBox cmb1) {
		
		//Remove all items passed in
		cmb1.removeAllItems();
		cmb1.addItem("--Supplier--");
		
		int star=0;//0 means there is no data 1 means there is data
		PreparedStatement preSql;
		String sqlStr="select * from supplier";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {
			//Execution to this indicates that data is available
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
	//Read the subproducts passed as a string and his supplier
   public static void readSun(JComboBox cmb1,String sup) {
		
		//Remove all items passed in
		cmb1.removeAllItems();
		cmb1.addItem("--Product--");
		
		int star=0;//0 means there is no data 1 means there is data
		PreparedStatement preSql;
		String sqlStr="select * from product where supname=?";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();
			while(rs.next()) {
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
	   
		PreparedStatement preSql;
		int  num;
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
