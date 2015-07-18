package com.gc.oa.service;

import java.util.List;

import com.gc.oa.common.TaskDTO;
import com.gc.oa.po.Leave;

/**
 * @author gongchang
 * 描述：
 * 时间：2014-9-17 上午10:48:43
 */
public interface ILeaveService {

	void save(Leave leave);
	
	/**
	 * @author gongchang
	 * 时间：2014-9-17 上午11:12:45
	 * 功能：开始请假流程
	 * 逻辑描述：
	 * 参数：
	 * 返回结果：
	 */
	void startLeave(String leaveId);

	List<Leave> find();

	List<Leave> findByUname(String username);

	List<TaskDTO> currenttasklist();

	void completeLeave(String activitiTaskId);

	void completeLeave(String activitiTaskId, String taskKey,String procInstId);

}
