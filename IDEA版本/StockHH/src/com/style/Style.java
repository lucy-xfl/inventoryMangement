package com.style;

import java.awt.Font;

public class Style {

	
	
	public static Font title;//����һ������  �͵�¼����
	public static Font account;//�˺ŵ���ʽ
	public static Font accounttext;//��¼�ı������ʽ
	public static Font ok;//��¼��ť��������ʽ
	

	public Style(){
		//�Գ���ĳ�ʼ��
		
		title=new Font("����",Font.BOLD,20);//��һ������ ��ʲô���壬�ڶ������� �ǼӴ� ������������ �����С
		account=new Font("����bai�п�",Font.BOLD,18);//��ǩ
		accounttext=new Font("����",Font.PLAIN,18);//�˺ſ�
		ok=new Font("����",Font.BOLD,18);//��¼��ť
		
		
		
	}
	
}
