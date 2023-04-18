package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.until.DBUtil;

public class StaffStockDao {
	public static Connection con=DBUtil.conn;
	//Time to get the order
	//Get the time of the system
	//Compare If it takes more than 3 minutes, the operation is not allowed Deletion and change are required within 3 minutes
	
	public static int showTime(String num) {
		
		PreparedStatement preSql;
		ResultSet rs;//Storage of the results
		
		String sqlStr="select *   from instock where id=?";
	
		int a=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,num);
			String t = null;
			rs=preSql.executeQuery();//Put the results in rs
			while(rs.next()) {
				a++;
				t = rs.getString("intime");
				
				Date day=new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ac = df.format(day);
				Long between_dayInteger = between_days(t, df.format(day));
				if(between_dayInteger>3) { //Whether more than three minutes
					return 0;
				}else {
					return 1;
				}
			}
			return 0;
			
		}catch(SQLException e) {
			return 3;
		}
		
	}
	
	public static int showTimeOut(String num) {
		
		PreparedStatement preSql;//Preprocessing Statement
		ResultSet rs;//Storage of the results
		
		String sqlStr="select *   from outstock where id=?";
	
		int a=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,num);
			String t = null;
			rs=preSql.executeQuery();//Put the results in rs
			
			while(rs.next()) {
				a++;
				t = rs.getString("outtime");
				
				Date day=new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ac = df.format(day);
				Long between_dayInteger = between_days(t, df.format(day));
				System.out.println(between_dayInteger);
				if(between_dayInteger>3) {
					return 0;
				}else {
					return 1;
				}
			}
			return 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 3;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Long between_days(String a, String b) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �Զ���ʱ���ʽ

        Calendar calendar_a = Calendar.getInstance();// ��ȡ��������
        Calendar calendar_b = Calendar.getInstance();

        Date date_a = null;
        Date date_b = null;

        try {
            date_a = simpleDateFormat.parse(a);//�ַ���תDate
            date_b = simpleDateFormat.parse(b);
            calendar_a.setTime(date_a);// ��������
            calendar_b.setTime(date_b);
        } catch (ParseException e) {
            e.printStackTrace();//��ʽ���쳣
        }

        long time_a = calendar_a.getTimeInMillis();
        long time_b = calendar_b.getTimeInMillis();
       
        long between_days = (time_b - time_a) / (1000 *60);//�����������

        return between_days;
    }
	
	
	
}
