package com.gc.sys.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.gc.core.po.ID;

/**
 * @author gongchang 描述：角色实体 时间：2014年9月4日 下午2:25:28
 */
@javax.persistence.Entity
@Table(name="t_sys_role")
public class Role extends ID {

	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 角色对应实体
	 */
	private Set<Entity> entities = new HashSet<Entity>(0);
	
	//辅助属性
	private String entityIds;

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_sys_role_entity", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "entity_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	public Set<Entity> getEntities() {
		return entities;
	}

	public void setEntities(Set<Entity> entities) {
		this.entities = entities;
	}

	
	@Transient
	public String getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(String entityIds) {
		this.entityIds = entityIds;
	}
}
