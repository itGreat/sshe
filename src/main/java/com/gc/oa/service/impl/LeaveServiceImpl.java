package com.gc.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.core.util.Struts2Utils;
import com.gc.oa.common.ProcessConstant;
import com.gc.oa.common.TaskDTO;
import com.gc.oa.dao.ILeaveDao;
import com.gc.oa.po.Leave;
import com.gc.oa.service.ILeaveService;
import com.gc.oa.util.ActivitiUtils;

@Service
public class LeaveServiceImpl implements ILeaveService{

	@Autowired
	private ILeaveDao leaveDao;
	
	@Override
	public void save(Leave leave) {
		leaveDao.save(leave);
	}

	
	/* (non-Javadoc)
	 * @see com.gc.oa.service.ILeaveService#startLeave(java.lang.String)
	 */
	@Override
	public void startLeave(String leaveId) {
		String procinstId=ActivitiUtils.startProcessInstance(ProcessConstant.LEAVE_PROCESS);
		
		TaskService taskService = ActivitiUtils.getTaskService();
		Task task= taskService.createTaskQuery().processInstanceId(procinstId).singleResult();
		String activitiTaskId=task.getId();
		
		//将当前的人领取任务
		String username = (String)Struts2Utils.getSession().getAttribute("username");
		taskService.claim(activitiTaskId, username);
		
		//如果流程还未结束
		if(taskService.createTaskQuery().processInstanceId(procinstId).list().size()>0){
			Task task_=taskService.createTaskQuery().processInstanceId(procinstId).list().get(0);
			taskService.addCandidateUser(task_.getId(), username);
		}
		
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Leave t where t.id = '"+leaveId+"' ");
		Leave leave = leaveDao.get(hql.toString());
		leave.setProcInstId(procinstId);
		leaveDao.saveOrUpdate(leave);
	}


	@Override
	public List<Leave> find() {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Leave t ");
		return leaveDao.find(hql.toString());
	}


	@Override
	public List<Leave> findByUname(String username) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Leave t ");
		hql.append(" where t.uname = '"+username+"' ");
		return leaveDao.find(hql.toString());
	}


	@Override
	public List<TaskDTO> currenttasklist() {
		String username = (String)Struts2Utils.getSession().getAttribute("username");
		List<TaskDTO> list = new ArrayList<TaskDTO>();
		List<Map<String, String>> currenttask = leaveDao.currenttask(username);
		for (Map<String, String> map : currenttask) {
			TaskDTO dto = new TaskDTO();
			dto.setActivitiTaskId(map.get("activitiTaskId"));
			dto.setAssignee(map.get("assignee"));
			//dto.setTaskType(map.get("taskType"));
			dto.setProcInstId(map.get("procInstId"));
			dto.setTaskKey(map.get("taskKey"));
			dto.setLeaveId(map.get("leaveId"));
			dto.setReason(map.get("reason"));
			dto.setUname(map.get("uname"));
			list.add(dto);
		}
		return list;
	}


	/* (non-Javadoc)
	 * @see com.gc.oa.service.ILeaveService#completeLeave(java.lang.String)
	 */
	@Override
	public void completeLeave(String activitiTaskId) {
		ActivitiUtils.complete(activitiTaskId);
	}


	@Override
	public void completeLeave(String activitiTaskId, String taskKey,String procInstId) {
		List<String> userlist = new ArrayList<String>();
		String username = (String)Struts2Utils.getSession().getAttribute("username");
		 if("startleave".equals(taskKey)){
			 userlist.add("jshuz");
		 }else if("managercheck".equals(taskKey)){
			 userlist.add("tw");
		 } 
		
		 TaskService taskService = ActivitiUtils.getTaskService();
		 
		 taskService.claim(activitiTaskId,username);
		 taskService.complete(activitiTaskId);
		 //如果流程还未结束
		 if(taskService.createTaskQuery().processInstanceId(procInstId).list().size()>0){
			Task task=taskService.createTaskQuery().processInstanceId(procInstId).list().get(0);
			for(String o:userlist){
				taskService.addCandidateUser(task.getId(), o);
			}
		 }
	}

}