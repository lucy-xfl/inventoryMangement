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
	//设置窗口居中
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame jframe) {
		
		Toolkit kit =Toolkit.getDefaultToolkit();//获取对象大小	//设置窗口位置
		Dimension screenSize=kit.getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;//获取屏幕高度和宽度
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		jframe.setBounds(x, y, WIDTH, HEIGHT);
		
	}
	//添加表格的模块化工具
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
	
	
	
	
	
	
	//删除通用的方法   传入一个删除语句  数组存储数据
	public static int dellData(String sqlStr,String data[]) {
PreparedStatement preSql;//预处语句
		
		//int num1 = Integer.parseInt(num);//将字符转换成整数

		
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
			//-1保存  0没找到满足条件   >=1删除成功
				
			
		}
		
		
		
	}
	//查询通用方法
	//删除通用的方法   传入一个删除语句  数组存储数据
	public static ResultSet showData(String sqlStr,String data[]) {
			PreparedStatement preSql;//预处语句
		
		//int num1 = Integer.parseInt(num);//将字符转换成整数

		
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
			//-1保存  0没找到满足条件   >=1删除成功
				
			
		}
		
		
		
	}
	//通用更改的功能
	public static int changeData(String sqlStr,String data[]) {
		PreparedStatement preSql;//预处语句
				
				//int num1 = Integer.parseInt(num);//将字符转换成整数

				
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
					//-1保存  0没找到满足条件   >=1删除成功
						
					
				}
				
				
				
			}
	
	
	
	
	
	
	
	
	
	
	
	
}
