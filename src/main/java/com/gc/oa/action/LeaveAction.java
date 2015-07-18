package com.gc.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gc.core.util.FileUtil;
import com.gc.core.util.Struts2Utils;
import com.gc.oa.common.ProcessConstant;
import com.gc.oa.common.TaskDTO;
import com.gc.oa.po.Leave;
import com.gc.oa.service.ILeaveService;
import com.gc.oa.util.ActivitiUtils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace(value="/oa/leave")
@ParentPackage(value="basePackage")
public class LeaveAction extends ActionSupport{
	
	@Autowired
	private ILeaveService leaveService;
	
	private String  username;
	private Leave leave;
	private String id;
	private String activitiTaskId;
	private String procInstId;
	
	private File file;
	private String fileFileName;
	private String procName = "请假流程";
	private String taskKey;

	/**
	 * @author gongchang
	 * 功能： 
	 * 时间：2014年9月5日 下午3:55:07
	 */
	@Action(value="list",results={ @Result(name=SUCCESS,location="list.jsp") })
	public String list(){
		username = (String) Struts2Utils.getSession().getAttribute("username");
		HttpServletRequest request = Struts2Utils.getRequest();
		List<Leave> list =  leaveService.findByUname(username);
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	@Action(value="currenttasklist",results={ @Result(name=SUCCESS,location="currenttasklist.jsp") })
	public String currenttasklist(){
		HttpServletRequest request = Struts2Utils.getRequest();
		username = (String) Struts2Utils.getSession().getAttribute("username");
		List<Task> list = ActivitiUtils.getTaskByAssignee(username);
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	@Action(value="currenttasklist2",results={ @Result(name=SUCCESS,location="currenttasklist2.jsp") })
	public String currenttasklist2(){
		HttpServletRequest request = Struts2Utils.getRequest();
		username = (String) Struts2Utils.getSession().getAttribute("username");
		List<TaskDTO> list = leaveService.currenttasklist();
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	@Action(value="add",results={ @Result(name=SUCCESS,location="add.jsp") })
	public String add(){
		username = (String) Struts2Utils.getSession().getAttribute("username");
		return SUCCESS;
	}
	
	@Action(value="image",results={ @Result(name=SUCCESS,location="image.jsp") })
	public String image() throws IOException{
		HttpServletRequest request = Struts2Utils.getRequest();
		RepositoryService repositoryService =  ActivitiUtils.getRepositoryService();
		// 通过部署ID获取到某一次部署下的所有资源文件名称集合
				String deploymentId = "401";
				List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
				// 添加规则，得到图片名称
				String imageName = null;
				for (String name : names) {
					if(name.indexOf(".png")>=0){
						imageName = name;
					}
				}
				// 通过部署ID和文件名称得到对应文件的输入流
				if(imageName!=null){
					String dir =  "/upload/workflow"; 
					String realPath = ServletActionContext.getServletContext().getRealPath(dir);
					InputStream in  = repositoryService.getResourceAsStream(deploymentId, imageName);
					String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
					String filename = time+".png";
					// 使用工具完成文件的拷贝
					File file  = new File(realPath+"/"+filename);
					FileUtils.copyInputStreamToFile(in, file);
					request.setAttribute("filename", filename);
					in.close();
				}
		return SUCCESS;
	}
	
	/**
	 * @author gongchang
	 * 时间：2014-9-19 下午4:15:35
	 * 功能：显示当前流程节点图片
	 * 逻辑描述：
	 * 参数：
	 * 返回结果：
	 */
	@Action(value="viewNode",results={ @Result(name=SUCCESS,location="image.jsp") })
	public String viewNode( ) throws IOException{
		HttpServletRequest request = Struts2Utils.getRequest();
//		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			RuntimeService runtimeService = ActivitiUtils.getRuntimeService();
			/**流程实例**/
			ProcessInstance processInstance = 
					(ProcessInstance)runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
			BpmnModel bpmnModel = ActivitiUtils.getRepositoryService().getBpmnModel(processInstance.getProcessDefinitionId());
			ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ActivitiUtils.getProcessEngine();
			Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());
			/**得到图片输出流**/
			List<String> activeActivityIds =  runtimeService.getActiveActivityIds(procInstId);
			InputStream imageStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
			/**得到图片输出流**/
			String realPath = ServletActionContext.getServletContext().getRealPath(ProcessConstant.DIR_WORKFLOW);
			String filename = FileUtil.getCurrentTimeString()+".png";
			// 使用工具完成文件的拷贝
			File file  = new File(realPath+"/"+filename);
			FileUtils.copyInputStreamToFile(imageStream, file);
			request.setAttribute("filename", filename);
			imageStream.close();
			/*OutputStream out = response.getOutputStream();
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
			    out.write(b, 0, len);
			}
			imageStream.close();
			out.flush();
			out.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="save")
	public void save() throws IOException{
		leaveService.save(leave);
		Struts2Utils.getResponse().getWriter().print(1);
	}
	
	@Action(value="startLeave")
	public void startLeave() throws IOException{
		leaveService.startLeave(id);
		Struts2Utils.getResponse().getWriter().print(1);
	}
	
	@Action(value="completeLeave")
	public void completeLeave() throws IOException{
		leaveService.completeLeave(activitiTaskId,taskKey,procInstId);
		Struts2Utils.getResponse().getWriter().print(1);
	}
	
	
	@Action(value="flow",results={ @Result(name=SUCCESS,location="flow.jsp") })
	public String flow(){
		// 列出所有的流程定义
		List<ProcessDefinition>	list = ActivitiUtils.getRepositoryService()
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionCategory()
				.orderByProcessDefinitionName()
				.orderByProcessDefinitionVersion()
				.desc()
				.list();
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	@Action(value="deploy")
	public void deploy() throws IOException{
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 创建流程发布配置对象
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		InputStream in =new FileInputStream(file);
		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("LeaveFlow.zip");
		ZipInputStream zipInputStream = new ZipInputStream(in);
		// 在配置对象中添加发布的规则信息
		deploymentBuilder.name(procName).addZipInputStream(zipInputStream );
		// 调用deploy方法发布流程
		deploymentBuilder.deploy();
		Struts2Utils.getResponse().getWriter().print(1);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Leave getLeave() {
		return leave;
	}


	public void setLeave(Leave leave) {
		this.leave = leave;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getActivitiTaskId() {
		return activitiTaskId;
	}

	public void setActivitiTaskId(String activitiTaskId) {
		this.activitiTaskId = activitiTaskId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}
}