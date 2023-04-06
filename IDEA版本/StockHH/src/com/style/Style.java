package com.style;

import java.awt.Font;

public class Style {

	
	
	public static Font title;//定义一个标题  就登录界面
	public static Font account;//账号的样式
	public static Font accounttext;//登录文本框的样式
	public static Font ok;//登录按钮的字体样式
	

	public Style(){
		//对程序的初始话
		
		title=new Font("宋体",Font.BOLD,28);//第一个参数 是什么字体，第二个参数 是加粗 ，第三个参数 字体大小
		account=new Font("华文bai行楷",Font.BOLD,18);//标签
		accounttext=new Font("宋体",Font.PLAIN,18);//账号框
		ok=new Font("宋体",Font.BOLD,18);//登录按钮
		
		
		
	}
	
}
