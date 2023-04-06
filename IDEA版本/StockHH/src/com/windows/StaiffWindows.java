package com.windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.dao.SupManDao;
import com.manage.item.AddStaffAccout;

import com.staff.panl.*;
import com.tool.Tool;

public class StaiffWindows {

	String buton[] ={"    商品入库    ","    商品出库    "};//是按钮的名称
	String butonName[] ={"stockIn","stockOut"};//名字区分不同按钮
	
	final int WIDTH=900;//设置顶层框架的宽度
	final int HEIGHT=600;//设置顶层框架的高度
	public JFrame jframe=new JFrame();
	
	public StaiffWindows() {
		

		init();
		jframe.setVisible(true); //设置当前窗口是否可显示 
		jframe.setResizable(false);//窗口的大小不可边
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jframe.validate();//让组件生效
		jframe.setIconImage(new ImageIcon("src/img/icons8-warehouse-100.png").getImage());
	}
	
	void init() {
		
		
		jframe.setLayout(null);//设置空布局
		jframe.setTitle("仓库管理系统");
		//窗口居中
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1=new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		//暂时没有想好是什么布局暂不设置
		
		//设置第一个盘子的位置以及大小
		jpanel1.setBounds(5, 5, 150, HEIGHT-10);

		jpanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		

		//增加一个菜单栏  放账号管理   和增加供应商
		JMenuBar menubar = new JMenuBar();//创建一个菜单条
		JMenu menu = new JMenu("账号管理");
		//JMenu menu1 = new JMenu("仓库管理");
		JMenuItem item1_1 = new JMenuItem("个人信息更改",new  ImageIcon("src/img/item3.png"));
		menu.add(item1_1);
		menubar.add(menu);//将菜单放到  菜单调
		jframe.setJMenuBar(menubar);
	
		
		JMenu menu2 = new JMenu("系统");
		JMenuItem item2_2 = new JMenuItem("注销", new ImageIcon("src/img/it1.png"));
		JMenuItem item2_3 = new JMenuItem("退出", new ImageIcon("src/img/it2.png"));
		 menu2.add(item2_2);
		 menu2.add(item2_3);
		 menubar.add(menu2);
		 //注销
		 item2_2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//关闭当前界面 打开登录界面
					jframe.dispose();
					Login login=new Login("仓库管理系统");
					
				}
				
			});
		 //退出
		 item2_3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jframe.dispose();
				}
				
			});
		
		
		//打开账号添加员工账号完毕
		
		item1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangOwnMeg chan=new ChangOwnMeg();
				String data[]=new String[1];
				data[0]=Login.jtextfield.getText();
				String sqlStr="select  sname,saddress,semail from users  where account=?";
				ResultSet rs = Tool.showData(sqlStr,data );
				try {
					rs.next();
					chan.JT1.setText(rs.getString("sname"));
					chan.JT2.setText(rs.getString("saddress"));
					chan.JT3.setText(rs.getString("semail"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			
		});
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		//入库窗格
		InStockPan inpan=new InStockPan(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//出库窗格 
		OutStockPan outpan=new OutStockPan(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(outpan, (Integer) (JLayeredPane.PALETTE_LAYER));

		
		

		jpanel2.setBounds(215-50, 5, 680+50, HEIGHT-10);//设置盘子二大小
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		jpanel1.setBackground(new Color(135,206,235));
		
		
		
		for(int i=0;i<buton.length;i++) {
			
			JButton bu=null;

			if(i==0) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu1.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
			}
			if(i==1) {
				bu=new JButton(buton[i],new  ImageIcon("src/img/Bu2.png"));
				jpanel1.add(bu);
				bu.setName(butonName[i]);
			}

			bu.addActionListener(new ActionListener() {
		
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					JButton jbl=(JButton)e.getSource();
					if(jbl.getName().equals(butonName[0])) {
						//将商品入库那个盘子移动到最上面
						jpanel2.moveToFront(inpan);
						SupManDao.readSup(InStockPan.cmbSupName);
						
					
						
					}
					
					if(jbl.getName().equals(butonName[1])) {
						//将商品入库那个盘子移动到最上面
						jpanel2.moveToFront(outpan);
						SupManDao.readSup(OutStockPan.cmbSupName);
						
					}
			
					
					
					
					
				}
				
			});
			
			
			
		}
		
		
		
		

		
		
		
		
		
	}


	
	
}
