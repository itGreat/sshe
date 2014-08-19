package com.gc.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author gongchang
 * 描述：ID 主键
 * 时间：2014-8-19 下午2:47:16
 */
@MappedSuperclass
public abstract class ID {
	
	protected String id;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", insertable = true, updatable = true, nullable = false,length=40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
