package com.gc.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gongchang
 * 描述：登录
 * 时间：2014年9月4日 下午4:59:39
 */
@SuppressWarnings("serial")
@Namespace(value="/sys")
public class LoginAction extends ActionSupport{
	
	private String username;
	private String password;
	
	
	@Action(value="index",results={
		@Result(name=SUCCESS,location="index.jsp"),
		@Result(name="login",location="login.action"),
	})
	public String index(){
		
		return SUCCESS;
	}
	
	@Action(value="login",results={
			@Result(name=SUCCESS,location="index.jsp"),
			@Result(name="login",location="tologin.action"),
		})
	public String login(){
		
		return null;
	}
	
	@Action(value="login",results={
			@Result(name=SUCCESS,location="index.jsp"),
			@Result(name="login",location="login.jsp"),
		})
	public String tologin(){
		
		return null;
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
