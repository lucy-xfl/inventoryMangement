package com.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;







import com.style.Style;
import com.tool.Tool;

public class ForGetPasssWord {

	
	public static int code=-1;
	FlowLayout flowlayout;//Define a layout
	final int WIDTH=700;//Set the width of the top-level frame
	final int HEIGHT=450;//Set the height of the top level frame
	
	//Define JPanel
	javax.swing.JPanel jpanel_1;//Put pictures and other
	javax.swing.JPanel jpanel_2;//Just put the title
	javax.swing.JPanel jpanel_3;//Just put the title
	
	JFrame jframe=new JFrame();
	
	
	public ForGetPasssWord() {
		init();
		jframe.setVisible(true); //Set whether the current window can be displayed
		jframe.setResizable(false);//The size of the window cannot be changed
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Set the default closing method
		jframe.validate();//Making components work
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
		
		
	}
	
	void init() {
		//Define the layout
		flowlayout=new FlowLayout(FlowLayout.CENTER);//Center Alignment
		//Centering the window
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		jpanel_1=new javax.swing.JPanel();
		jpanel_1.setPreferredSize(new Dimension (WIDTH,HEIGHT));
		jpanel_1.setLayout(null);//Set layout
		jpanel_1.setOpaque(false);//Set the current JPanel to transparent
		


		ImageIcon img=new ImageIcon("src/img/2.jpg");//Read the image into the img variable
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0,0,700,450);//Set background image Set background position
		
		Style fronts=new Style();//The classes inside can change the style
		jframe.setTitle("Inventory Management System Password Retrieval");
		
		JLabel title = new JLabel("Inventory Management System Account Retrieval");
		title.setFont(fronts.title);
		title.setForeground(Color.gray);
		
	
		
		
		jpanel_2=new javax.swing.JPanel();
		jpanel_2.setBounds(0, 30, WIDTH-10, 70);
		jpanel_2.setOpaque(false);//Set the current JPanel to transparent
		jpanel_2.setLayout(flowlayout);
		jpanel_2.add(title);
		//Initialize the contents of JPanel 2
		//Initialize the contents of JPanel 3
		jpanel_3=new javax.swing.JPanel();
		jpanel_3.setBounds(175, 100,350,270);
		jpanel_3.setOpaque(false);//Set the current JPanel to transparent
		jpanel_3.setLayout(flowlayout);//Set Layout
		
		
		JLabel JL1=new JLabel("Account Number:");
		JL1.setFont(fronts.account);//Set font style
		JTextField JT1 = new JTextField(20);
		JL1.setForeground(new Color(102,102,102));
		JT1.setForeground(new Color(18,120,192));
		JT1.setPreferredSize(new Dimension(15,28));
		JT1.setFont(fronts.accounttext);
		
		//The first row of data is initialized

		jpanel_3.add(JL1);
		jpanel_3.add(JT1);
		
		
		
		JLabel JL2=new JLabel("New Password:");
		JL2.setFont(fronts.account);//Set font style
		JPasswordField JT2 = new JPasswordField(20);
		JL2.setForeground(new Color(102,102,102));
		JT2.setForeground(new Color(18,120,192));
		JT2.setPreferredSize(new Dimension(15,28));
		JT2.setFont(fronts.accounttext);
		
		//The first row of data is initialized

		jpanel_3.add(JL2);
		jpanel_3.add(JT2);
		
		
		JLabel JL3=new JLabel("Email Address:");
		JL3.setFont(fronts.account);//Set font style
		JTextField JT3 = new JTextField(20);
		JL3.setForeground(new Color(102,102,102));
		JT3.setForeground(new Color(18,120,192));
		JT3.setPreferredSize(new Dimension(15,28));
		JT3.setFont(fronts.accounttext);
		
		//The first row of data is initialized

		jpanel_3.add(JL3);
		jpanel_3.add(JT3);
		
		
		
		JLabel JL4=new JLabel("Verification Code:");
		JL4.setFont(fronts.account);//Set font style
		JTextField JT4 = new JTextField(20);
		JL4.setForeground(new Color(102,102,102));
		JT4.setForeground(new Color(18,120,192));
		JT4.setPreferredSize(new Dimension(15,28));
		JT4.setFont(fronts.accounttext);
		
		//The first row of data is initialized

		jpanel_3.add(JL4);
		jpanel_3.add(JT4);
		
		
		JButton JB1 = new JButton("Submit");
		JB1.setPreferredSize(new Dimension(150,30));//Set the size of the button
		JB1.setFont(fronts.ok);
		JB1.setForeground(new Color(244,170,128));
		
		
		JButton JB2 = new JButton("Verify");
		JB2.setPreferredSize(new Dimension(150,30));//Set the size of the button
		JB2.setFont(fronts.ok);
		JB2.setForeground(new Color(244,170,128));

		jpanel_3.add(JB1);
		jpanel_3.add(JB2);
		//Last line
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Read whether the account number is not empty and whether the password is not empty and whether the email format is correct
				String account=JT1.getText();//Get Account
				char []str=JT2.getPassword();
				String password=new String(str);//Get Password
				//String 
				String email=JT3.getText();// Get Email
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "Account cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Account cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(email.equals("")){
					JOptionPane.showMessageDialog(null, "Account cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					//To send a message to an email address, we need to determine whether the email address is intended for
					
					String mah = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//Regular expressions for email
					Pattern pattern = Pattern.compile(mah);
					Matcher matcher = pattern.matcher(email);
					boolean result = matcher.matches();
					if(result) {
						//Send the correct verification code to the email address  163
						Random tool = new Random();//4455
					
			    		code = tool.nextInt(9000) + 1000;
			    		System.out.println("The verification code is:" + code);
						
			    		 String to = email;//Get the currently entered mailbox
			    		 
			    		 String from = "czydabaoer@gmail.com";
					     String host = "smtp.gmail.com";  //Mail Server
						
					     // Get system properties
					      Properties properties = System.getProperties();

					        // Setting up the mail server
					       properties.setProperty("mail.smtp.host", host);

					       properties.put("mail.smtp.auth", "true");
					        // Get default session object
					       
					       
					       
					       
					       Session session = Session.getDefaultInstance(properties,new Authenticator(){
					            public PasswordAuthentication getPasswordAuthentication()
					            {
					                return new PasswordAuthentication("buyiyangdelaomo@163.com", "HJZUSSRJLSDFUOJH"); //Sender email username, password
					            }
					        });
					       
					       //Read out the mailboxes from the database
					       String sqlStr="select * from users where account=?";//Read Mailbox
					       String data[]=new String[1];
					       data[0]=account;
					       ResultSet rs = Tool.showData(sqlStr,  data);
					    
					       try {
					    	
							if(rs.next()) {
								String ema=rs.getString("semail");
								
								   //System.out.println(ema==email);
								if(ema.equals(email)) {
									 try{
								            // Create the default MimeMessage object
								            MimeMessage message = new MimeMessage(session);

								            // Set From: Header fields
								            message.setFrom(new InternetAddress(from));

								            // Set To: Header fields
								            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

								            // Set Subject: Header fields
								            message.setSubject("Inventory Management System Registration Verification");//Email Topic

								            // Set the message body
								            message.setText("Your verification code for this time is:" + code);//Email Content

								            // Send Message
								            Transport.send(message);
								            JOptionPane.showMessageDialog(null, "Verification code sent successfully, please pay attention to check", "Message",JOptionPane.WARNING_MESSAGE);
								        }catch (MessagingException mex) {
								            mex.printStackTrace();
								            JOptionPane.showMessageDialog(null, "Check whether to open pop3 service, send failure", "Message",JOptionPane.WARNING_MESSAGE);
								        }
								       	
									
									
								}else {
									
									JOptionPane.showMessageDialog(null, "Wrong email address!", "Message",JOptionPane.WARNING_MESSAGE);
									
								}
								
								
								
								
								
							}else {
								
								JOptionPane.showMessageDialog(null, "Please enter the correct account number", "Message",JOptionPane.WARNING_MESSAGE);
								
							}
					
							
							
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					       
					}else {
						JOptionPane.showMessageDialog(null, "Email format error", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
					
				}
			}
			
		});
		
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Read whether the account number is not empty
				// and whether the password is not empty
				// and whether the email format is correct
				String account=JT1.getText();//Get Account
				char []str=JT2.getPassword();
				String password=new String(str);//Get Password
				//String 
				String email=JT3.getText();// Get Email
				String code1=JT4.getText();
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "Account cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
					
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Password cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(email.equals("")){
					JOptionPane.showMessageDialog(null, "Email cannot be empty", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					//To send a message to an email address, you need to determine if the email address is correct.
					
					String mah = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//Regular expressions for mailboxes
					Pattern pattern = Pattern.compile(mah);
					Matcher matcher = pattern.matcher(email);
					boolean result = matcher.matches();
					if(result) {
						
					       	
						String s = String.valueOf(code);
						if(s.equals(code1)) {
							//Update data to the database
							String sql="update users set password=? where account=?";
							String data[]=new String [2];
							data[0]=password;
							data[1]=account;
							int num = Tool.dellData(sql, data);
							if(num==0) {
								JOptionPane.showMessageDialog(null, "Please enter the correct account number", "Message",JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "Change successful", "Message",JOptionPane.WARNING_MESSAGE);
							}
							
							
						}else {
							JOptionPane.showMessageDialog(null, "Verification code error", "Message",JOptionPane.WARNING_MESSAGE);
						}
				
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Email format error", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
					
				}
			}
			
		});
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		jpanel_1.add(jpanel_2);//盘子二房标题
		jpanel_1.add(jpanel_3);
		jpanel_1.add(bgimg);
		jframe.add(jpanel_1);//盘子一房北京
		
		

		
		
				
				
				
				
				
				
				
				
				
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
