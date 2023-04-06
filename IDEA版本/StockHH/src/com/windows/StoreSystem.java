package com.windows;

import com.dao.StaffStockDao;
import com.manage.item.AddStaffAccout;
import com.until.DBUtil;

public class StoreSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//打开登录界面，第二部链接数据库
		
		
		
		DBUtil dbutil=new DBUtil("root","lele11170308","storemanage");//数据库账号，数据库密码，数据库名字
		Login login=new Login("仓库管理系统");
		
		//StaiffWindows s=new StaiffWindows();
		
		
		
		//DBUtil dbutil=new DBUtil("root","root","storemanage");
		
		
	}

}
