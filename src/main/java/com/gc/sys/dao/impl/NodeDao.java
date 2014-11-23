package com.gc.sys.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gc.sys.dao.INodeDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.Node;
import com.gc.sys.entity.Role;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:35:44
 */
@SuppressWarnings("unchecked")
@Repository
public class NodeDao extends BaseDaoImpl<Node> implements INodeDao{

	@Override
	public List<Map<String, Object>> getNodes() {
		StringBuffer hql = new StringBuffer();
		hql.append(" select new map(t.id as id,t.name as text) from Node t where t.entity.type=:type");
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		Query query = session.createQuery(hql.toString());
		query.setParameter("type", Entity.TYPE_MENU);
		List<Map<String, Object>> list = query.list();
		return list;
	}

	 
	@Override
	public Node get(Serializable id) {
		Session session = getCurrentSession();
		return (Node) session.get(Node.class, id);
	}

}
