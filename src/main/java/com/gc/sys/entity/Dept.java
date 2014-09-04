package com.gc.sys.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.gc.sys.entity.base.ID;

/**
 * @author gongchang
 * 描述：部门实体
 * 时间：2014年9月4日 下午2:19:50
 */
@Entity
@Table(name="t_sys_dept")
public class Dept extends ID{

	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 上级部门
	 */
	private Dept parent;
	
	/**
	 * 子部门
	 */
	private Set<Dept> children;
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="parent")
	@OrderBy("code")
	public Set<Dept> getChildren() {
		return children;
	}
	public void setChildren(Set<Dept> children) {
		this.children = children;
	}
}
