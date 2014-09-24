package com.gc.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月24日 下午2:51:48
 */
@SuppressWarnings("serial")
@Namespace(value="/sys/comm")
@ParentPackage(value="basePackage")
public class CommAction extends ActionSupport{

	/**
	 * @author gongchang
	 * 功能：跳转后台首页
	 * 时间：2014年9月5日 下午3:54:32
	 */
	@Action(value="radio_dept",results={ @Result(name=SUCCESS,location="radio_dept.jsp") })
	public String radioDept(){
		return SUCCESS;
	}
	
}
