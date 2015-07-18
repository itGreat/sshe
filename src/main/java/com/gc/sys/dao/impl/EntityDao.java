package com.gc.sys.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.gc.core.dao.BaseDaoImpl;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.po.Entity;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:35:44
 */
@Repository
public class EntityDao extends BaseDaoImpl<Entity> implements IEntityDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> findByUid(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT t.* FROM t_sys_entity t,t_sys_role_entity re,t_sys_user_role ur ");
		sql.append(" WHERE ur.role_id = re.role_id AND ur.user_id = 1 ");
		Session session = getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		List<Map<String, String>> list = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return null;
	}

	@Override
	public Entity get(Serializable id) {
		return (Entity)getCurrentSession().get(Entity.class, id);
	}
}
