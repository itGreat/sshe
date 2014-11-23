package com.gc.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gc.common.Criteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.dao.INodeDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.Node;
import com.gc.sys.service.INodeService;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月23日 上午11:33:41
 */
@Service
public class NodeServiceImpl implements INodeService{
	
	@Autowired
	private INodeDao nodeDao;
	@Autowired
	private IEntityDao entityDao;

	@Override
	public String loadTree() {
		String str = null;
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Node t where t.parent.id is null ");
		List<Node> list = nodeDao.find(hql.toString());
		if(!CollectionUtils.isEmpty(list)){
			StringBuffer buf=new StringBuffer("[");
			boolean notFirst=false;
			for (Node node : list) {
				if(notFirst){
					buf.append(",");
				}
				buf.append("{");
				buf.append(getJsonStr(node));
				buf.append(getChildDepart(node));
				buf.append("}");
				notFirst=true;
			}
			buf.append("]");
			str = buf.toString();
		}
		return str;
	}
	
	private String getJsonStr(Node node){
		StringBuffer buf=new StringBuffer();
		buf.append("\"id\" : \""+node.getId()+"\" ");
		buf.append(",\"text\" : \""+node.getName()+"\" ");
		/*String stats = CollectionUtils.isEmpty(node.getChildren()) ? "closed" : "open";
		buf.append(", \"state\": \""+stats+"\" ");*/
		buf.append(",\"attributes\":{");
		String url = null != node.getEntity() ? node.getEntity().getValue() : "";
		buf.append("\"url\" :\""+url+"\" ");
		buf.append("}");
		return buf.toString();
	}

	private String getChildDepart(Node node){
		StringBuffer buf=new StringBuffer();
		if(!CollectionUtils.isEmpty(node.getChildren())){
			buf.append(",\"children\":[");
			//查询下一个记录
			int i=0;
			for (Node node_ : node.getChildren()) {
				if(i==1){
					buf.append(",");
				}
				buf.append("{");
				buf.append(getJsonStr(node_));
				buf.append(getChildDepart(node_));
				i=1;
				buf.append("}");
			}
			buf.append("]");
		}
		return buf.toString();
	}

	@Override
	public List<Map<String, Object>> loadDataByCriteria(Criteria criteria) {
		return null;
	}

	@Override
	public Node get(String id) {
		return nodeDao.get(id);
	}

	@Override
	public void save(Node node) {
		final boolean isNew = StringUtils.isEmpty(node.getId());
		//关联功能
		String entityId = null != node.getEntity() ? node.getEntity().getId() : null; 
		if(StringUtils.isNotEmpty(entityId)){
			Entity entity = entityDao.get(entityId);
			node.setEntity(entity);
		}
		//关联上级
		String parentId = null != node.getParent() ? node.getParent().getId() : null;
		if(StringUtils.isNotEmpty(parentId)){
			Node parent = nodeDao.get(parentId);
			node.setParent(parent);
		}
		
		if(isNew){
			nodeDao.save(node);
		}else{
			Node model = nodeDao.get(node.getId());
			model.setName(node.getName());
			model.setLayer(node.getLayer());
			model.setSeq(node.getSeq());
			model.setEntity(node.getEntity());
			model.setParent(node.getParent());
			nodeDao.update(model);
		}
	}

	@Override
	public void delete(String id) {
		Node node = nodeDao.get(id);
		nodeDao.delete(node);
	}

	@Override
	public List<Map<String, Object>> getNodes() {
		return nodeDao.getNodes();
	}
}
