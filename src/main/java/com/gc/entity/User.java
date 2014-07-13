package com.gc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gc.common.IdEntity;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年7月12日 下午10:23:15
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_user")
public class User extends IdEntity{
	
	protected Long id;
	private String name;
	private String username;
	private String password;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public Long getId() { 
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
