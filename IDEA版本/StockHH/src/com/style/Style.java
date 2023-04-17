package com.style;

import java.awt.Font;

public class Style {

	
	
	public static Font title;//Define a title for the login screen
	public static Font account;//Style of the account
	public static Font accounttext;//Login text box style
	public static Font ok;//Font style of login button


	public Style(){
		//Initialization of the program

		title=new Font("Arial",Font.BOLD,20);//The first parameter is what font, the second parameter is bold, and the third parameter is font size
		account=new Font("Arial",Font.BOLD,18);//Tags
		accounttext=new Font("Arial",Font.PLAIN,18);//Account box
		ok=new Font("Arial",Font.BOLD,18);//Login button
		
	}
	
}
