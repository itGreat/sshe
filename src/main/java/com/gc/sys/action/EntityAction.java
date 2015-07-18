package com.gc.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gc.core.action.CrudActionSupport;
import com.gc.core.util.Struts2Utils;
import com.gc.sys.common.EntityCriteria;
import com.gc.sys.po.Entity;
import com.gc.sys.service.IEntityService;

@SuppressWarnings("serial")
@Namespace(value="/sys/entity")
@ParentPackage(value="basePackage")
public class EntityAction extends CrudActionSupport<Entity>{

	@Autowired
	private IEntityService entityService;
	
	private EntityCriteria criteria;
	
	private Entity entity;
	private String entityId;
	
	@Override
	public Entity getModel() {
		return entity;
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="list.jsp") })
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
	@Action(value="input",results={ @Result(name=SUCCESS,location="input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(entityId)){
			entity = entityService.get(entityId);
		}
		return SUCCESS;
	}

	@Override
	@Action(value="save")
	public String save() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		entityService.save(entity);
		out.print(1);
		out.close();
		return null;
	}

	@Override
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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
