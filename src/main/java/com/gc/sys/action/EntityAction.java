package com.gc.sys.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gc.sys.service.IEntityService;
import com.gc.util.Struts2Utils;


/**
 * @author gongchang
 * 描述：
 * 时间：2014年11月15日 上午10:11:10
 */
@SuppressWarnings("serial")
@Namespace(value="/sys/entity")
@ParentPackage(value="basePackage")
public class EntityAction extends CrudActionSupport<Entity>{

	@Autowired
	private IEntityService entityService;
	
	private String id;
	private Entity entity;
	
	private Criteria criteria;
	
	@Override
	public Entity getModel() {
		return entity;
	}
	
	
	@Action(value="getEntitys")
	public void getEntitys()  throws Exception  {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String,Object>> list = entityService.getEntitys();
		PrintWriter out = response.getWriter();
		out.print(JSONObject.toJSON(list));
	}
	
	/**
	 * @author gongchang
	 * 功能：
	 * 时间：2014年11月15日 上午10:11:26
	 */
	@Action(value="data")
	public void data() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
//		List<Map<String,Object>> list = entityService.loadData();
		List<Map<String,Object>> list = entityService.loadDataByCriteria(criteria);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", list);
		String jsonString = JSONObject.toJSONString(dataMap);
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="entity_list.jsp") })
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	@Action(value="input",results={ @Result(name=SUCCESS,location="entity_input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(id)){
			entity = entityService.get(id);
		}
		return SUCCESS;
	}

	@Override
	@Action(value="save")
	public String save() throws Exception {
		entityService.save(entity);
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}

	@Override
	@Action(value="delete")
	public String delete() throws Exception {
		entityService.delete(id);
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Entity getEntity() {
		return entity;
	}


	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

}
