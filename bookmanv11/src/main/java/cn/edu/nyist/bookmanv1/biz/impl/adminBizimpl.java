package cn.edu.nyist.bookmanv1.biz.impl;

import cn.edu.nyist.bookmanv1.biz.adminBIz;
import cn.edu.nyist.bookmanv1.dao.AdminDao;
import cn.edu.nyist.bookmanv1.dao.impl.AdminDaoJDBCimpl;

public class adminBizimpl implements adminBIz {

	@Override
	public boolean findAdminbyNameandPwd(String name, String pwd) {
		AdminDao adminDao= new AdminDaoJDBCimpl();
		return adminDao.get(name,pwd);
	}



}
