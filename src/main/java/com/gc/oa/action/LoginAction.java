package com.gc.oa.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gc.core.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace(value="/oa")
@ParentPackage(value="basePackage")
public class LoginAction extends ActionSupport{
	
	private String username;
	private String password;
	
	
	/**
	 * @author gongchang
	 * 功能：跳转后台首页
	 * 时间：2014年9月5日 下午3:54:32
	 */
	@Action(value="index",results={ @Result(name=SUCCESS,location="index.jsp") })
	public String index(){
		username = (String) Struts2Utils.getSession().getAttribute("username");
		return SUCCESS;
	}
	
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
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			throw new RuntimeException("username and password is null");
		 }else{
			 if(username.equals(password)){
				 Struts2Utils.getSession().setAttribute("username", username);
				 return SUCCESS;
			 }else{
				 return LOGIN;
			 }
		 }
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

