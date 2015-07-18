package com.gc.oa.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gc.core.po.ID;

/**
 * @author gongchang
 * 描述：请假
 * 时间：2014-9-17 上午9:29:48
 */
@Entity
@Table(name = "t_oa_leave")
public class Leave extends ID{
	
	private String uname;
	
	private String reason;
	
	//流程实例id
	private String procInstId;

	@Column
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	
}