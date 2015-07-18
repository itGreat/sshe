package com.gc.sys.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gc.core.util.Struts2Utils;
import com.gc.sys.service.INodeService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace(value="/sys")
@ParentPackage(value="basePackage")
public class IndexAction extends ActionSupport{
	
	protected Log log = LogFactory.getLog(getClass());
	
	/*@Autowired
	private IEntityService entityService;*/
	@Autowired
	private INodeService nodeService;
	
	/**
	 * @author gongchang
	 * 功能：跳转后台首页
	 * 时间：2014年9月5日 下午3:54:32
	 */
	@Action(value="index",results={ @Result(name=SUCCESS,location="index.jsp") })
	public String index(){
		return SUCCESS;
	}
	
	
	@Action(value="tree")
	public void tree() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		String str = nodeService.loadTree();
		log.info(str);
		out.print(str);
		out.close();
	}
	
}
