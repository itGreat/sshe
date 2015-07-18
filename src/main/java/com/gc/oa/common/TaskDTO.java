package com.gc.oa.common;

public class TaskDTO {
	private String activitiTaskId;
	private String assignee;
//	private String taskType;
	private String procInstId;
	private String taskKey;
	private String leaveId;
	private String reason;
	private String uname;
	
	public String getActivitiTaskId() {
		return activitiTaskId;
	}
	public void setActivitiTaskId(String activitiTaskId) {
		this.activitiTaskId = activitiTaskId;
	}
//	public String getTaskType() {
//		return taskType;
//	}
//	public void setTaskType(String taskType) {
//		this.taskType = taskType;
//	}
	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getTaskKey() {
		return taskKey;
	}
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
}
