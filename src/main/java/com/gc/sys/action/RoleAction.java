package com.gc.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gc.common.Criteria;
import com.gc.common.CrudActionSupport;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.Role;
import com.gc.sys.service.INodeService;
import com.gc.sys.service.IRoleService;
import com.gc.util.Struts2Utils;


/**
 * @author gongchang
 * 描述：
 * 时间：2014年11月15日 上午10:11:10
 */
@SuppressWarnings("serial")
@Namespace(value="/sys/role")
@ParentPackage(value="basePackage")
public class RoleAction extends CrudActionSupport<Role>{

	@Autowired
	private IRoleService roleService;
	@Autowired
	private INodeService nodeService;
	
	private String id;
	private String ids;
	private Role role;
	
	private Criteria criteria;
	
	@Override
	public Role getModel() {
		return role;
	}
	
	/**
	 * @author gongchang
	 * 功能：
	 * 时间：2014年11月15日 上午10:11:26
	 */
	@Action(value="data")
	public void data() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/json;charset=UTF-8");
//		List<Map<String,Object>> list = entityService.loadData();
		List<Map<String,Object>> list = roleService.loadDataByCriteria(criteria);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", list);
		String jsonString = JSONObject.toJSONString(dataMap);
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="role_list.jsp") })
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	@Action(value="input",results={ @Result(name=SUCCESS,location="role_input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(id)){
			role = roleService.get(id);
		}
		return SUCCESS;
	}

	@Override
	@Action(value="save")
	public String save() throws Exception {
		roleService.save(role);
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}

	@Override
	@Action(value="delete")
	public String delete() throws Exception {
		if(StringUtils.isNotEmpty(ids)){
			String[] arrIds = StringUtils.split(ids, ",");
			roleService.delete(arrIds);
		}
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}

	@Action(value="tree")
	public void tree() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		String str = nodeService.loadTree(id);
//		Role role = roleService.get(id);
//		Set<Entity> entities = role.getEntities();
//		for (Entity entity : entities) {
//			str = str.replace("\"id\":\""+entity.getId()+"\"", "\"id\":\""+entity.getId()+"\",\"checked\":true");
//		}
		log.info(str);
		out.print(str);
		out.close();
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

 
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
