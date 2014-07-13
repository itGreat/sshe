package com.gc.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
public abstract class IdEntity implements Serializable {

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
	/*@Id
	@Column(name="Id", length=25)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TimeIDGenerator")
	@GenericGenerator(name = "TimeIDGenerator", strategy = "com.gc.entity.TimeIDGenerator")*/
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public Long getId() { 
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}