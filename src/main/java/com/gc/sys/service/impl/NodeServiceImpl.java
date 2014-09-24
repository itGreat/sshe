package com.gc.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gc.sys.dao.INodeDao;
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
	private INodeDao NodeDao;

	@Override
	public String loadTree() {
		String str = null;
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Node t where t.parent is null ");
		List<Node> list = NodeDao.find(hql.toString());
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
		buf.append("\"id\" : "+node.getId());
		buf.append(",\"text\" : \""+node.getName()+"\" ");
		/*String stats = CollectionUtils.isEmpty(node.getChildren()) ? "closed" : "open";
		buf.append(", \"state\": \""+stats+"\" ");*/
		buf.append(",\"attributes\":{");
		buf.append("\"url\" :\""+node.getEntity().getValue()+"\" ");
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
}
