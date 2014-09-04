package com.gc.sys.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.gc.sys.entity.base.UndeleteEntity;

/**
 * @author gongchang 
 * 描述： 用户实体
 * 时间：2014年7月12日 下午10:23:15
 */
@Entity
@Table(name = "t_sys_user")
public class User extends UndeleteEntity{

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 拥有角色
	 */
	private Set<Role> roles;

	@Column(length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length=40)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "t_sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	// Fetch策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合中对象id的缓存.
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
