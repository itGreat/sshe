package com.gc.sys.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.sys.common.Constants;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.dao.IUserDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.User;
import com.gc.util.Struts2Utils;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月6日 下午10:14:45
 */
@Service
public class LoginService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IEntityDao entityDao;
	
	public void login(String username,String password){
		
		User user = userDao.getByUsername(username);
		if(null != user && password.equals(user.getPassword())){
			HttpSession session = Struts2Utils.getSession();
			session.setAttribute(Constants.LOGIN_INFO, user);
			
			//加载菜单
			//List<Entity> list = entityDao.findByUid(user.getId());
		}
	}
}
