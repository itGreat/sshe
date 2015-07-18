package com.gc.sys.action;

import java.io.PrintWriter;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
<<<<<<< HEAD
import com.gc.core.action.CrudActionSupport;
import com.gc.core.util.Struts2Utils;
import com.gc.sys.common.EntityCriteria;
import com.gc.sys.po.Entity;
import com.gc.sys.service.IEntityService;
=======
import com.gc.common.Criteria;
import com.gc.common.CrudActionSupport;
import com.gc.sys.entity.Entity;
import com.gc.sys.service.IEntityService;
import com.gc.util.Struts2Utils;

>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

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
	
<<<<<<< HEAD
	private EntityCriteria criteria;
	
	private Entity entity;
	private String entityId;
=======
	private String id;
	private Entity entity;
	
	private Criteria criteria;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	
	@Override
	public Entity getModel() {
		return entity;
<<<<<<< HEAD
=======
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
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="entity_list.jsp") })
	public String list() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="list_json")
	public void listJson() throws Exception {
//		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		JSONObject json=  new JSONObject();
		//JSONObject rows=  new JSONObject();
		List<JSONObject> rows = new ArrayList<JSONObject>();
		List<Entity> list = entityService.findByCriteria(criteria);
		for (Entity entity : list) {
			JSONObject entityJson=  new JSONObject();
			entityJson.put("id", entity.getId());
			entityJson.put("name", entity.getName());
//			String type = entity.geten
			entityJson.put("type", entity.getType());
			entityJson.put("value", entity.getValue());
			entityJson.put("remark", entity.getRemark());
			rows.add(entityJson);
		}
		json.put("total", list.size());
		json.put("rows", rows);
		out.print(JSONObject.toJSON(json));
		out.close();
	}

	@Override
<<<<<<< HEAD
	@Action(value="input",results={ @Result(name=SUCCESS,location="input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(entityId)){
			entity = entityService.get(entityId);
=======
	@Action(value="input",results={ @Result(name=SUCCESS,location="entity_input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(id)){
			entity = entityService.get(id);
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
		}
		return SUCCESS;
	}

	@Override
	@Action(value="save")
	public String save() throws Exception {
<<<<<<< HEAD
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		entityService.save(entity);
		out.print(1);
		out.close();
=======
		entityService.save(entity);
		Struts2Utils.getResponse().getWriter().print(1);
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
		return null;
	}

	@Override
<<<<<<< HEAD
	@Action(value="del")
	public String delete() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		entityService.delete(entityId);
		out.print(1);
		out.close();
		return null;
	}

=======
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


>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	public Entity getEntity() {
		return entity;
	}

<<<<<<< HEAD
=======

>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

<<<<<<< HEAD
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
=======
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	}

}
