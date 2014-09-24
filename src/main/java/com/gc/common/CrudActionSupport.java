package com.gc.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = -3250152265646142566L;

	/**
	 * 进行增删改操作后,以redirect方式重新打开action默认页的result名.
	 */
	public static final String RELOAD = "reload";
	
	/**
	 *  进行增删改操作后,以redirect方式定向到显示信息窗口的result名.
	 */
	public static final String MESSAGE="message";
	
	 /**
	  * 异进行增删改抛出异常后，,以redirect方式定向到显示信息窗口的result名.
	  */
	public static final String EXCEPTION = "exception";
	
	protected Log log = LogFactory.getLog(getClass());

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}

	//-- CRUD Action函数 --//
	/**
	 * Action函数, 显示Entity列表.
	 * 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;

	
	/**
	 * Action函数,显示新增或修改Entity界面.
	 * 建议return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action函数,新增或修改Entity. 
	 * 建议return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity.
	 * 建议return RELOAD.
	 */
	public abstract String delete() throws Exception;
}