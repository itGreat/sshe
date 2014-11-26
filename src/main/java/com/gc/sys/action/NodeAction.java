package com.gc.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gc.common.Criteria;
import com.gc.common.CrudActionSupport;
import com.gc.sys.entity.Node;
import com.gc.sys.service.INodeService;
import com.gc.util.Struts2Utils;


/**
 * @author gongchang
 * 描述：
 * 时间：2014年11月15日 上午10:11:10
 */
@SuppressWarnings("serial")
@Namespace(value="/sys/node")
@ParentPackage(value="basePackage")
public class NodeAction extends CrudActionSupport<Node>{

	@Autowired
	private INodeService nodeService;
	
	private String id;
	private Node node;
	private String parentId;
	
	private Criteria criteria;
	
	@Override
	public Node getModel() {
		return node;
	}
	
	@Action(value="getNodes")
	public void getEntitys()  throws Exception  {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		List<Map<String,Object>> list = nodeService.getNodes();
		PrintWriter out = response.getWriter();
		out.print(JSONObject.toJSON(list));
	}
	
	
	/**
	 * @author gongchang
	 * 功能：
	 * 时间：2014年11月15日 上午10:11:26
	 */
	@Action(value="data")
	public void data() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
//		List<Map<String,Object>> list = entityService.loadData();
		List<Map<String,Object>> list = nodeService.loadDataByCriteria(criteria);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", list);
		String jsonString = JSONObject.toJSONString(dataMap);
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}

	@Override
	@Action(value="list",results={ @Result(name=SUCCESS,location="node_list.jsp") })
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	@Action(value="input",results={ @Result(name=SUCCESS,location="node_input.jsp") })
	public String input() throws Exception {
		if(StringUtils.isNotEmpty(id)){
			node = nodeService.get(id);
		}else{
			node = new Node();
			if(StringUtils.isNotEmpty(parentId)){
				Node parent = nodeService.get(parentId);
				node.setParent(parent);
			}
		}
		return SUCCESS;
	}

	@Action(value="tree")
	public void tree() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/plain; charset=utf-8"); //指定文本类型
		PrintWriter out = response.getWriter();
		String str = nodeService.loadTree();
		log.info(str);
		out.print(str);
		out.close();
	}
	
	@Override
	@Action(value="save")
	public String save() throws Exception {
		nodeService.save(node);
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}

	@Override
	@Action(value="delete")
	public String delete() throws Exception {
		nodeService.delete(id);
		Struts2Utils.getResponse().getWriter().print(1);
		return null;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

 

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
