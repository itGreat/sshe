package com.gc.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.gc.common.CrudActionSupport;
import com.gc.sys.entity.Entity;

@SuppressWarnings("serial")
@Namespace(value="/sys/entity")
@ParentPackage(value="basePackage")
public class EntityAction extends CrudActionSupport<Entity>{

	@Override
	public Entity getModel() {
		
		return null;
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="list.jsp") })
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		
		return null;
	}

	@Override
	public String save() throws Exception {
		
		return null;
	}

	@Override
	public String delete() throws Exception {
		
		return null;
	}

}
