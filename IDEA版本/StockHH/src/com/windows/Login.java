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

	final int WIDTH=500;//设置顶层框架的宽度
	final int HEIGHT=350;//设置顶层框架的高度
	
	String title;
	JFrame jframe=new JFrame();
	

	FlowLayout flowlayout;
	
	
	
	Login(String title){
		this.title=title;
		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}

	void init() {

	//	jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
		//设置标题
		
		jframe.setTitle(title);
		//设置窗口的位置
		// 设置当前窗口的大小
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
	
		//大小设置完毕了。
		flowlayout=new FlowLayout(flowlayout.CENTER);//居中对齐
		Style style=new Style();
		jframe.setLayout(null);
		
		//先把图片放入进来
		ImageIcon img=new ImageIcon("src/img/Login.jpg");//将图片读取放到img变量里面
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0,0,500,350);//设置背景图片 设置背景位置
		
		
		//定义两个框框 分别装账号，密码标题
		JPanel jpnel1=new JPanel();
		jpnel1.setLayout(flowlayout);
		jpnel1.setBounds(0, 0,500, 45);
		
		//添加标题
		JLabel jlabel1=new JLabel("仓库管理系统登录");
		jlabel1.setFont(style.title);
		jpnel1.add(jlabel1);
		jpnel1.setOpaque(false);
		//创建盘子二
		JPanel jpnel2=new JPanel();
		jpnel2.setLayout(flowlayout);
		jpnel2.setBounds(125, 45,210, 230);
		jpnel2.setOpaque(false);
		
		JLabel jlabel2=new JLabel("账号");
		jlabel2.setFont(style.account);
		jpnel2.add(jlabel2);
		//添加账号框
		jtextfield=new JTextField(10);
		jtextfield.setFont(style.accounttext);
		jpnel2.add(jtextfield);
		
		JLabel jlabel3=new JLabel("密码");
		jlabel3.setFont(style.account);
		jpnel2.add(jlabel3);
		
		
		//添加账号框
		jtextfield1=new JPasswordField(10);
		jtextfield1.setFont(style.accounttext);
		jpnel2.add(jtextfield1);
		
		
		//JLabel AAA=new JLabel("            ");
		//jpnel2.add(AAA);
		//添加两个单选框
		//JCheckBox JC1 = new JCheckBox("保存密码");//
		//JC1.setOpaque(false);
		//jpnel2.add(JC1);
		//JCheckBox JC2 = new JCheckBox("自动登录");//
		//jpnel2.add(JC2);
		//JC2.setOpaque(false);
		
		
		
		
		// 登录按钮
		JButton jbutton=new JButton("安全登录");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(195,35) );
		jbutton.setBackground(Color.gray);
		jbutton.setForeground(new Color(	255,215,0));
		
		
		jpnel2.add(jbutton);


		jframe.add(jpnel2);
		jframe.add(jpnel1);
		JLabel jl=new JLabel("忘记密码");
		jframe.add(jl);
		jl.setBounds(430, 160, 300, 40);
		//register.setFont(fronts.register);
		jl.setForeground(Color.DARK_GRAY);
		
		jframe.add(bgimg);
		
		
		//标签只能添加鼠标箭头
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
		
		
		
	
		
		
		//以下是监听事件
		
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将账号  和密码获取到 并做响应的提示 
				//将账号和密码，跟数据库进行匹对，同时在匹对权限，实现跳转不同的界面
				
				String account=jtextfield.getText();//获取账号
				
				char []str=jtextfield1.getPassword();
				String password=new String(str);
				
				
				boolean star = LoginDao.loginStar(account, password);
				if(star==true) {
					System.out.println("登录成功");
					//之后，还需要判断 权限 在那个等级 根据等级进行跳转不同的界面
					int pow=LoginDao.loginPow(account, password);
					if(pow==2) {
						//则是管理员
						jframe.dispose();
						MangePeopleWindows a=new MangePeopleWindows();
						SupManDao.readSup(InStockPan.cmbSupName);
						
					}else if(pow==1) {
						//则是普通用户
						jframe.dispose();
						StaiffWindows a=new StaiffWindows();
					}else {
						//报错
						JOptionPane.showMessageDialog(null, "系统错误", "登录消息",JOptionPane.WARNING_MESSAGE);
					}
					
					
				}else {
					
					JOptionPane.showMessageDialog(null, "账号或者密码错误！", "登录消息",JOptionPane.WARNING_MESSAGE);
				}
				
		
			
				
				
			}
			
		});
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
		
		
		
		
		
		
		
		
	}
	
	
	
	

	
	///
	//
	//
}
