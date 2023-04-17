package com.tool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.until.DBUtil;

public class Tool {

	static Connection con=DBUtil.conn;
	//Set window centering
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame jframe) {
		
		Toolkit kit =Toolkit.getDefaultToolkit();//Get object size	Set window position
		Dimension screenSize=kit.getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;//Get screen height and width
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		jframe.setBounds(x, y, WIDTH, HEIGHT);
		
	}
	//Modular tool for adding tables
	public static  int addDataTable(ResultSet rs ,DefaultTableModel  model,int index) {
		
		int count=0;
		model.setNumRows(0);
		
		String  data[]=new String [index];
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
					
				}
				model.addRow(data);
				
				
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return count;
		}
		
		
		

		
	}
	
	
	
	
	
	
	//Delete generic method
	//Pass in a delete statement
	//Array to store data
	public static int dellData(String sqlStr,String data[]) {
		PreparedStatement preSql;//Preliminary Statements
		
		//int num1 = Integer.parseInt(num);//Convert characters to integers

		
		int num=0;
		
		try {
		
			preSql=con.prepareStatement(sqlStr);
			for(int i=0;i<data.length;i++) {
				preSql.setString(i+1,data[i]);
			}
			
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
			//-1 save
			//0 not found to meet the conditions
			//>=1 delete successfully
				
			
		}
		
		
		
	}
	//General method of query
	//Delete generic method
	//Pass in a delete statement
	//Array to store data
	public static ResultSet showData(String sqlStr,String data[]) {
			PreparedStatement preSql;//Preliminary Statements
		
		//int num1 = Integer.parseInt(num);//Convert characters to integers

		
		ResultSet rs=null;
		
		try {
		
			preSql=con.prepareStatement(sqlStr);
			if(data!=null) {
				for(int i=0;i<data.length;i++) {
					preSql.setString(i+1,data[i]);
				}
			}
			
			
			 rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			
			return rs;
			//-1 save
			//0 not found to meet the conditions
			//>=1 delete successfully
				
			
		}
		
		
		
	}
	//通用更改的功能
	public static int changeData(String sqlStr,String data[]) {
		PreparedStatement preSql;//Preliminary Statements
				
				//int num1 = Integer.parseInt(num);//Convert characters to integers

				
				int num=0;
				
				try {
				
					preSql=con.prepareStatement(sqlStr);
					for(int i=0;i<data.length;i++) {
						preSql.setString(i+1,data[i]);
					}
					
					num=preSql.executeUpdate();
					return num;
					
				}catch(SQLException e) {
					e.printStackTrace();
					return -1;
					//-1 save
					//0 not found to meet the conditions
					//>=1 delete successfully
						
					
				}
				
				
				
			}
	
	
	
	
	
	
	
	
	
	
	
	
}
