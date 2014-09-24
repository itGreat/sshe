package com.gc.sys.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月4日 下午2:34:07
 */
@MappedSuperclass
public class UndeleteEntity extends ID{
	
	/**
	 * 已删除
	 */
	public final static Integer DELETED = 1;
	/**
	 * 未删除
	 */
	public final static Integer UN_DELETE = 0;

	/**
	 * 是否删除
	 */
	private Integer deleted = UN_DELETE;

	@Column(length=2)
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
