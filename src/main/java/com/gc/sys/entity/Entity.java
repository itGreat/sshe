package com.gc.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gc.sys.entity.base.ID;

/**
 * @author gongchang
 * 描述：功能实体
 * 时间：2014年9月4日 下午2:38:54
 */
@javax.persistence.Entity
@Table(name="t_sys_entity")
public class Entity extends ID{
	
	 /**
	 * 功能类型为菜单
	 */
	public final static String TYPE_MENU="menu";

    /**
     * 功能类型为其它
     */
    public final static String TYPE_OTHER="other";

	//类型
    private String type;

    //名称
    private String name;

	//内容
    private String value;
    //备注
    private String remark;
    
    @Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	@Column
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
