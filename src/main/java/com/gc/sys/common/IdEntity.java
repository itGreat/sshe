package com.gc.sys.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public abstract class IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6743002931682238932L;
	
	protected Long id;

	/**
	数据库表ID生成方式
	@TableGenerator(name="A_ID_GENERATOR",
					table="A_ID_GENERATOR",
					pkColumnName = "aid",
					pkColumnValue = "Acl_ID",
					valueColumnName = "Id_VALUE",
					initialValue = 1,
					allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE,generator="A_ID_GENERATOR")
	 */
	@Id
	@Column(name="Id", length=25)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TimeIDGenerator")
	@GenericGenerator(name = "TimeIDGenerator", strategy = "com.gc.common.TimeIDGeneratorLong")
	public Long getId() { 
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}