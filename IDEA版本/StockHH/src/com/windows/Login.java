package com.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.dao.LoginDao;
import com.dao.SupManDao;
import com.manage.panl.InStockPan;
import com.style.Style;
import com.tool.Tool;



public class Login {
	
	public static JTextField jtextfield;
	public static JPasswordField jtextfield1;

	final int WIDTH=500;//Set the width of the top-level frame
	final int HEIGHT=350;//Set the height of the top-level frame
	
	String title;
	JFrame jframe=new JFrame();
	

	FlowLayout flowlayout;
	
	
	
	Login(String title){
		this.title=title;
		init();
		jframe.setVisible(true); //Set whether the current window can be displayed
		jframe.setResizable(false);//The size of the window cannot be changed
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Set the default closing method
		jframe.validate();//Making components work
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}

	void init() {

		//Set title
		
		jframe.setTitle(title);
		//Set the position of the window
		// Set the size of the current window
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
	
		//The size is now set
		flowlayout=new FlowLayout(flowlayout.CENTER);//Center Alignment
		Style style=new Style();
		jframe.setLayout(null);
		
		//Put the picture in first
		ImageIcon img=new ImageIcon("src/img/Login.jpg");//Read the image into the img variable
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0,0,500,350);//Set background image Set background position
		
		
		//Define two JPanel with account number and password title respectively
		JPanel jpnel1=new JPanel();
		jpnel1.setLayout(flowlayout);
		jpnel1.setBounds(0, 0,500, 45);
		
		//Add Title
		JLabel jlabel1=new JLabel("Inventory Management System Login");
		jlabel1.setFont(style.title);
		jpnel1.add(jlabel1);
		jpnel1.setOpaque(false);
		//Create JPanel2
		JPanel jpnel2=new JPanel();
		jpnel2.setLayout(flowlayout);
		jpnel2.setBounds(125, 45,210, 230);
		jpnel2.setOpaque(false);
		
		JLabel jlabel2=new JLabel("Account Number");
		jlabel2.setFont(style.account);
		jpnel2.add(jlabel2);
		//Add account box
		jtextfield=new JTextField(10);
		jtextfield.setFont(style.accounttext);
		jpnel2.add(jtextfield);
		
		JLabel jlabel3=new JLabel("Password");
		jlabel3.setFont(style.account);
		jpnel2.add(jlabel3);
		
		
		//Add password box
		jtextfield1=new JPasswordField(10);
		jtextfield1.setFont(style.accounttext);
		jpnel2.add(jtextfield1);

		

		// Login button
		JButton jbutton=new JButton("Secure Login");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(195,35) );
		jbutton.setBackground(Color.gray);
		jbutton.setForeground(new Color(	255,215,0));
		
		
		jpnel2.add(jbutton);


		jframe.add(jpnel2);
		jframe.add(jpnel1);
		JLabel jl=new JLabel("Forgot password");
		jframe.add(jl);
		jl.setBounds(350, 160, 300, 40);
		//register.setFont(fronts.register);
		jl.setForeground(Color.DARK_GRAY);
		
		jframe.add(bgimg);
		
		
		//Tabs can only add mouse arrows
		jl.addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ForGetPasssWord c=new ForGetPasssWord();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
	
		
		
		//The following events are listened to
		
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Get the account number and password and respond to the prompt
				//Match the account number and password with the database,
				//and at the same time, in the matching permission, realize jumping to different interfaces.
				
				String account=jtextfield.getText();//Get Account
				
				char []str=jtextfield1.getPassword();
				String password=new String(str);
				
				
				boolean star = LoginDao.loginStar(account, password);
				if(star==true) {
					System.out.println("Login successful");
					//After that, you need to determine the level of authority and jump to different screens according to the level.
					int pow=LoginDao.loginPow(account, password);
					if(pow==2) {
						//And the Manager
						jframe.dispose();
						MangePeopleWindows a=new MangePeopleWindows();
						SupManDao.readSup(InStockPan.cmbSupName);
						
					}else if(pow==1) {
						//And the Staff
						jframe.dispose();
						StaiffWindows a=new StaiffWindows();
					}else {
						//Report an error
						JOptionPane.showMessageDialog(null, "System Error", "Login Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Wrong account or password!", "Login Message",JOptionPane.WARNING_MESSAGE);
				}
				
		
			
				
				
			}
			
		});
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
		
		
		
		
		
		
		
		
	}
	
	
	
	

	
	///
	//
	//
}
