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
	FlowLayout flowlayout;//定义一个布局
	final int WIDTH=410;//设置顶层框架的宽度
	final int HEIGHT=320;//设置顶层框架的高度
	
	//定义箱子
	javax.swing.JPanel jpanel_1;//放图片和其他的盘子
	javax.swing.JPanel jpanel_2;//只是放标题
	javax.swing.JPanel jpanel_3;//只是放标题
	
	JFrame jframe=new JFrame();
	
	
	public ForGetPasssWord() {
		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
		
		
	}
	
	void init() {
		//定义布局
		flowlayout=new FlowLayout(FlowLayout.CENTER);//居中对齐
		//让窗口居中显示
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		jpanel_1=new javax.swing.JPanel();
		jpanel_1.setPreferredSize(new Dimension (WIDTH,HEIGHT));
		jpanel_1.setLayout(null);//设置布局
		jpanel_1.setOpaque(false);//将当前的盘子设置成透明
		
		
		//设置盘子大小
		
		//设置背景图片
		ImageIcon img=new ImageIcon("src/img/2.jpg");//将图片读取放到img变量里面
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0,0,410,400);//设置背景图片 设置背景位置
		
		Style fronts=new Style();//里面的类可以改变样式
		jframe.setTitle("仓库管理系统密码找回");
		
		JLabel title = new JLabel("仓储管理系统账号找回");
		title.setFont(fronts.title);
		title.setForeground(Color.gray);
		
	
		
		
		jpanel_2=new javax.swing.JPanel();
		jpanel_2.setBounds(0, 30, WIDTH-10, 70);
		jpanel_2.setOpaque(false);//将当前的盘子设置成透明
		jpanel_2.setLayout(flowlayout);
		jpanel_2.add(title);
		//对箱子二的内容进行初始化
		//初始化第三箱子 
		jpanel_3=new javax.swing.JPanel();
		jpanel_3.setBounds(25, 100,350,270);
		jpanel_3.setOpaque(false);//将当前的盘子设置成透明
		jpanel_3.setLayout(flowlayout);//设置布局
		
		
		JLabel JL1=new JLabel("账        号:");
		JL1.setFont(fronts.account);//设置字体样式
		JTextField JT1 = new JTextField(20);
		JL1.setForeground(new Color(102,102,102));
		JT1.setForeground(new Color(18,120,192));
		JT1.setPreferredSize(new Dimension(15,28));
		JT1.setFont(fronts.accounttext);
		
		//第一行数据进行初始化

		jpanel_3.add(JL1);
		jpanel_3.add(JT1);
		
		
		
		JLabel JL2=new JLabel("新  密  码:");
		JL2.setFont(fronts.account);//设置字体样式
		JPasswordField JT2 = new JPasswordField(20);
		JL2.setForeground(new Color(102,102,102));
		JT2.setForeground(new Color(18,120,192));
		JT2.setPreferredSize(new Dimension(15,28));
		JT2.setFont(fronts.accounttext);
		
		//第一行数据进行初始化

		jpanel_3.add(JL2);
		jpanel_3.add(JT2);
		
		
		JLabel JL3=new JLabel("邮        箱:");
		JL3.setFont(fronts.account);//设置字体样式
		JTextField JT3 = new JTextField(20);
		JL3.setForeground(new Color(102,102,102));
		JT3.setForeground(new Color(18,120,192));
		JT3.setPreferredSize(new Dimension(15,28));
		JT3.setFont(fronts.accounttext);
		
		//第一行数据进行初始化

		jpanel_3.add(JL3);
		jpanel_3.add(JT3);
		
		
		
		JLabel JL4=new JLabel("验  证  码:");
		JL4.setFont(fronts.account);//设置字体样式
		JTextField JT4 = new JTextField(20);
		JL4.setForeground(new Color(102,102,102));
		JT4.setForeground(new Color(18,120,192));
		JT4.setPreferredSize(new Dimension(15,28));
		JT4.setFont(fronts.accounttext);
		
		//第一行数据进行初始化

		jpanel_3.add(JL4);
		jpanel_3.add(JT4);
		
		
		JButton JB1 = new JButton("提   交");
		JB1.setPreferredSize(new Dimension(150,30));//设置按钮的大小
		JB1.setFont(fronts.ok);
		JB1.setForeground(new Color(244,170,128));
		
		
		JButton JB2 = new JButton("获取验证码");
		JB2.setPreferredSize(new Dimension(150,30));//设置按钮的大小
		JB2.setFont(fronts.ok);
		JB2.setForeground(new Color(244,170,128));

		jpanel_3.add(JB1);
		jpanel_3.add(JB2);
		//最后一行
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 判读账号 是否 不为空  密码是否不为空  邮箱格式 是否正确
				String account=JT1.getText();//获取账号
				char []str=JT2.getPassword();
				String password=new String(str);//获取密码
				//String 
				String email=JT3.getText();// 获取邮箱
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "账号不能为空", "消息",JOptionPane.WARNING_MESSAGE);
					
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(email.equals("")){
					JOptionPane.showMessageDialog(null, "邮箱不能为空", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					//给邮箱发信息  还需判断一下  邮箱地址是否争取
					
					String mah = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//邮箱的正则表达式
					Pattern pattern = Pattern.compile(mah);
					Matcher matcher = pattern.matcher(email);
					boolean result = matcher.matches();
					if(result) {
						//向邮箱发送正确的验证码  163  
						Random tool = new Random();//4455
					
			    		code = tool.nextInt(9000) + 1000;
			    		System.out.println("验证码为：" + code);
						
			    		 String to = email;//获取当前输入的邮箱
			    		 
			    		 String from = "buyiyangdelaomo@163.com";
					     String host = "smtp.163.com";  //163 邮件服务器
						
					     // 获取系统属性
					      Properties properties = System.getProperties();

					        // 设置邮件服务器
					       properties.setProperty("mail.smtp.host", host);

					       properties.put("mail.smtp.auth", "true");
					        // 获取默认session对象
					       
					       
					       
					       
					       Session session = Session.getDefaultInstance(properties,new Authenticator(){
					            public PasswordAuthentication getPasswordAuthentication()
					            {
					                return new PasswordAuthentication("buyiyangdelaomo@163.com", "HJZUSSRJLSDFUOJH"); //发件人邮件用户名、密码
					            }
					        });
					       
					       //将数据库里面的邮箱读取出来
					       String sqlStr="select * from users where account=?";//读取邮箱
					       String data[]=new String[1];
					       data[0]=account;
					       ResultSet rs = Tool.showData(sqlStr,  data);
					    
					       try {
					    	
							if(rs.next()) {
								String ema=rs.getString("semail");
								
								   //System.out.println(ema==email);
								if(ema.equals(email)) {
									 try{
								            // 创建默认的 MimeMessage 对象
								            MimeMessage message = new MimeMessage(session);

								            // Set From: 头部头字段
								            message.setFrom(new InternetAddress(from));

								            // Set To: 头部头字段
								            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

								            // Set Subject: 头部头字段
								            message.setSubject("仓储管理系统注册验证");//Email主题

								            // 设置消息体
								            message.setText("您的此次验证码为：" + code);//Email内容

								            // 发送消息
								            Transport.send(message);
								            JOptionPane.showMessageDialog(null, "验证码发送成功,请注意查收", "消息",JOptionPane.WARNING_MESSAGE);
								        }catch (MessagingException mex) {
								            mex.printStackTrace();
								            JOptionPane.showMessageDialog(null, "检查是否开启pop3服务，发失败", "消息",JOptionPane.WARNING_MESSAGE);
								        }
								       	
									
									
								}else {
									
									JOptionPane.showMessageDialog(null, "邮箱地址错误！", "消息",JOptionPane.WARNING_MESSAGE);
									
								}
								
								
								
								
								
							}else {
								
								JOptionPane.showMessageDialog(null, "请输入正确的账号", "消息",JOptionPane.WARNING_MESSAGE);
								
							}
					
							
							
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					       
					}else {
						JOptionPane.showMessageDialog(null, "邮箱格式错误", "消息",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
					
				}
			}
			
		});
		
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 判读账号 是否 不为空  密码是否不为空  邮箱格式 是否正确
				String account=JT1.getText();//获取账号
				char []str=JT2.getPassword();
				String password=new String(str);//获取密码
				//String 
				String email=JT3.getText();// 获取邮箱
				String code1=JT4.getText();
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "账号不能为空", "消息",JOptionPane.WARNING_MESSAGE);
					
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空", "消息",JOptionPane.WARNING_MESSAGE);
				}else if(email.equals("")){
					JOptionPane.showMessageDialog(null, "邮箱不能为空", "消息",JOptionPane.WARNING_MESSAGE);
				}else {
					//给邮箱发信息  还需判断一下  邮箱地址是否争取
					
					String mah = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//邮箱的正则表达式
					Pattern pattern = Pattern.compile(mah);
					Matcher matcher = pattern.matcher(email);
					boolean result = matcher.matches();
					if(result) {
						
					       	
						String s = String.valueOf(code);
						if(s.equals(code1)) {
							//讲数据更新到数据库
							String sql="update users set password=? where account=?";
							String data[]=new String [2];
							data[0]=password;
							data[1]=account;
							int num = Tool.dellData(sql, data);
							if(num==0) {
								JOptionPane.showMessageDialog(null, "请输入正确账号", "消息",JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "更改成功", "消息",JOptionPane.WARNING_MESSAGE);
							}
							
							
						}else {
							JOptionPane.showMessageDialog(null, "验证码错误", "消息",JOptionPane.WARNING_MESSAGE);
						}
				
						
						
					}else {
						JOptionPane.showMessageDialog(null, "邮箱格式错误", "消息",JOptionPane.WARNING_MESSAGE);
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
