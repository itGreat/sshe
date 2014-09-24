package com.gc.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gc.common.Criteria;
import com.gc.sys.common.UserCriteria;
import com.gc.sys.entity.Dept;
import com.gc.sys.entity.User;
import com.gc.sys.service.IDeptService;
import com.gc.sys.service.IUserService;
import com.gc.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gongchang
 * 描述：用户部门管理
 * 时间：2014年9月22日 下午3:31:14
 */
@SuppressWarnings("serial")
@Namespace(value="/sys/userdept")
@ParentPackage(value="basePackage")
public class UserDeptAction extends ActionSupport{

	protected Log log = LogFactory.getLog(getClass());
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	
	private User user;
	private String userId;
	private UserCriteria criteria;
	
	private Dept dept;
	private String deptId;
	private String parentId;
	
	/**
	 * @author gongchang
	 * 功能： 调整用户部门管理主页面
	 * 时间：2014年9月5日 下午3:54:32
	 */
	@Action(value="main",results={ @Result(name=SUCCESS,location="main.jsp") })
	public String index(){
		return SUCCESS;
	}
	
	@Action(value="tree")
	public void tree() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String str = deptService.loadTree();
		log.info(str);
		out.print(str);
		out.close();
	}
	
	
	
	@Action(value="dept_input",results={ @Result(name=SUCCESS,location="dept_input.jsp") })
	public String deptInput(){
		if(StringUtils.isNotEmpty(deptId)){
			dept = deptService.get(deptId);
		}else{
			Dept parent = deptService.get(parentId);
			dept = new Dept();
			dept.setParent(parent);
//			Struts2Utils.getRequest().setAttribute("parent", parent);
		}
		return SUCCESS;
	}
	
	@Action(value="dept_del")
	public void deptDel() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		deptService.delete(deptId);
		out.print(1);
		out.close();
	}
	
	@Action(value="dept_save")
	public void deptSave() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		deptService.save(dept);
		out.print(1);
		out.close();
	}
	
	/**
	 * 2014-4-18,gongchang
	 * 功能： 检查部门是否符合删除条件
	 * 逻辑描述： 
	 * 参数：无
	 * 返回结果：无
	 * @throws IOException 
	 */
	@Action(value="dept_check")
	public void deptCheck() throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		String msg = deptService.check(deptId);
		out.print(msg);
		out.close();
	}
	
	@Action(value="user_input",results={ @Result(name=SUCCESS,location="user_input.jsp") })
	public String userInput(){
		if(StringUtils.isNotEmpty(userId)){
			user = userService.get(userId);
		}else{
			Dept dept = deptService.get(deptId);
			user = new User();
			user.setDept(dept);
		}
		return SUCCESS;
	}
	
	@Action(value="user_save")
	public void userSave() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		userService.save(user);
		out.print(1);
		out.close();
	}
	
	@Action(value="user_del")
	public void userDel() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		userService.delete(userId);
		out.print(1);
		out.close();
	}

	@Action(value="user_json")
	public void userJson() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		ArrayList<HashMap<String,Object>> rows = new ArrayList<HashMap<String,Object>>();
		//get方式提交，手动解码
		String username = request.getParameter("criteria.username");
		if(null != username){
			criteria.setUsername(new String(username.getBytes("ISO-8859-1"), "utf-8"));
		}
		String name = request.getParameter("criteria.name");
		if(null != name){
			criteria.setName(new String(name.getBytes("ISO-8859-1"), "utf-8"));
		}
		List<User> list = userService.findByCriteria(criteria);
		resultMap.put("total", list.size());
		for (User user : list) {
			HashMap<String,Object> userMap = new HashMap<String,Object>();
			userMap.put("id", user.getId());
			userMap.put("name", user.getName());
			userMap.put("username", user.getUsername());
			String deptname = null == user.getDept() ? null : user.getDept().getName();
			userMap.put("deptname", deptname);
			rows.add(userMap);
		}
		resultMap.put("rows", rows);
		out.print(JSONObject.toJSON(resultMap));
		out.close();
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(UserCriteria criteria) {
		this.criteria = criteria;
	}
}
