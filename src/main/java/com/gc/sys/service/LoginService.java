package com.gc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.sys.dao.IUserDao;
import com.gc.sys.entity.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月6日 下午10:14:45
 */
@Service
public class LoginService {
	
	@Autowired
	private IUserDao userDao;
	
	public void login(String username,String password){
		
		User user = userDao.getByUsername(username);
		if(null != user){
			if(password.equals(user.getPassword())){
				
			}
		}
	}
}
