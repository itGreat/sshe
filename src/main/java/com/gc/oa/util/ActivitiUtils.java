package com.gc.oa.util;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * @author gongchang
 * 描述：Activiti 工具类
 * 时间：2014-9-19 下午3:37:06
 */
public class ActivitiUtils {

	/**
	 * 获得流程引擎
	 */
	private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * @author gongchang
	 * 时间：2014-9-17 下午3:23:16
	 * 功能：
	 * 逻辑描述：
	 * 参数：
	 * 返回结果：
	 */
	public static ProcessEngine getProcessEngine(){
		return processEngine;
	}
	
	/**
	 * @author gongchang
	 * 时间：2014-9-17 上午11:18:23
	 * 功能：
	 * 逻辑描述：
	 * 参数：
	 * 返回结果：
	 */
	public static List<Task> getTaskByAssignee(String assignee){
		ProcessEngine ep = getProcessEngine();
		TaskService raskService = ep.getTaskService();
		List<Task> list = raskService.createTaskQuery().taskAssignee(assignee).list();//gongchang jshuz
		return list;
	}

	/**
	 * @author gongchang
	 * 时间：2014-9-17 下午4:42:51
	 * 功能：
	 * 逻辑描述：
	 * 参数：
	 * 返回结果：
	 */
	public static void complete(String activitiTaskId) {
		getTaskService().complete(activitiTaskId);
	}

	/**
	 * @author gongchang
	 * 时间：2014-9-18 上午9:38:14
	 * 功能：启动流程实例
	 * 逻辑描述：
	 * 参数：procKey 流程id
	 * 返回结果：
	 */
	public static String startProcessInstance(String procKey) {
		RuntimeService runtimeService = getProcessEngine().getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(procKey);
		return processInstance.getId();
	}

	public static TaskService getTaskService() {
		return getProcessEngine().getTaskService();
	}

	public static RepositoryService getRepositoryService() {
		return getProcessEngine().getRepositoryService();
	}

	public static RuntimeService getRuntimeService() {
		return getProcessEngine().getRuntimeService();
	}
	
}
