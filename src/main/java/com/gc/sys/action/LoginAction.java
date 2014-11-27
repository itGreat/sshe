package com.gc.sys.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gc.sys.entity.Entity;
import com.gc.sys.entity.Role;
import com.gc.sys.entity.User;
import com.gc.sys.service.IUserService;
import com.gc.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gongchang
 * 描述：登录
 * 时间：2014年9月4日 下午4:59:39
 */
@SuppressWarnings("serial")
@Namespace(value="/sys")
@ParentPackage(value="basePackage")
public class LoginAction extends ActionSupport{
	
	private String username;
	private String password;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * @author gongchang
	 * 功能：验证登录信息
	 * 时间：2014年9月5日 下午3:54:48
	 */
	@Action(value="checklogin",results={
			@Result(name=SUCCESS,type="redirect",location="index.action"),
			@Result(name=LOGIN,type="redirect",location="login.action"),
		})
	public String cklogin(){
		 if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
			 User user = userService.getByUsername(username);
			 if(null != user && password.equals(user.getPassword())){
				 Set<Role> roles = user.getRoles();
				 Map<String,Boolean> map = new HashMap<String, Boolean>();
				 for (Role role : roles) {
					for (Entity entity : role.getEntities()) {
						map.put(entity.getValue(), true);
					}
				 }
				 Struts2Utils.getSession().setAttribute("entityMap", map);
				 return SUCCESS;
			 }else{
				 return LOGIN;
			 }
		 }
		return LOGIN;
	}
	
	/**
	 * @author gongchang
	 * 功能：跳转登录页面
	 * 时间：2014年9月5日 下午3:55:07
	 */
	@Action(value="login",results={ @Result(name=SUCCESS,location="login.jsp") })
	public String login(){
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
