package bpmn;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestHelloworld {
	
	//初始化数据库
	/*@Test
	public void initDb(){
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngine ep = configuration.buildProcessEngine();
	}

	//1.部署流程
	@Test
	public void deployment(){
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngine ep = processEngineConfiguration.buildProcessEngine();
		RepositoryService repositoryService = ep.getRepositoryService();
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addClasspathResource("diagrams/MyProcess.bpmn").addClasspathResource("diagrams/MyProcess.png");
		builder.deploy();
	}
	//2.启动流程
	@Test
	public void startProcess(){
		ProcessEngine ep = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = ep.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayProcess");
		String activityId = processInstance.getActivityId();
		String id = processInstance.getId();
	}
	//3.查看任务
	@Test
	public void queryTask(){
		ProcessEngine ep = ProcessEngines.getDefaultProcessEngine();
		TaskService raskService = ep.getTaskService();
		List<Task> list = raskService.createTaskQuery().taskAssignee("jshuz").list();//gongchang jshuz
		for (Task task : list) {
			System.out.println("taskid: "+task.getId());
			System.out.println("name: "+task.getName());
			System.out.println("assignee: "+task.getAssignee());
			System.out.println("createTime: "+task.getCreateTime());
		}
	}
	
	
	//4.办理任务
	@Test
	public void completeTask(){
		ProcessEngine ep = ProcessEngines.getDefaultProcessEngine();
		TaskService raskService = ep.getTaskService();
		String taskId="104";
		raskService.complete(taskId);
	}
	*/
	
}
