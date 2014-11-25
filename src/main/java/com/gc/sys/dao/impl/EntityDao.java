package com.gc.sys.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.gc.common.Criteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:35:44
 */
@SuppressWarnings("unchecked")
@Repository
public class EntityDao extends BaseDaoImpl<Entity> implements IEntityDao{

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

	public List<Map<String, Object>> loadData() {
		StringBuffer hql = new StringBuffer();
		// type name value remark 
		hql.append(" select new map(t.id as id,t.type as type,t.name as name,t.value as value,t.remark as remark) from Entity t ");
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		List<Map<String, Object>> list2 = session.createQuery(hql.toString()).list();
		return list2;
	}
	
	@Override
	public Entity get(Serializable id) {
		Session session = getCurrentSession();
		return (Entity) session.get(Entity.class, id);
	}

	
	@Override
	public List<Map<String, Object>> loadDataByCriteria(Criteria criteria) {
		StringBuffer hql = new StringBuffer();
		// type name value remark 
		hql.append(" select new map(t.id as id,t.type as type,t.name as name,t.value as value,t.remark as remark) from Entity t where 1=1 ");
		if(null != criteria){
			String type = criteria.getType();
			if(StringUtils.isNotEmpty(type)){
				hql.append(" and t.type = '"+type+"' ");
			}
			String name = criteria.getName();
			if(StringUtils.isNotBlank(name)){
				hql.append(" and t.name like '%"+name+"%' ");
			}
			String value = criteria.getValue();
			if(StringUtils.isNotBlank(value)){
				hql.append(" and t.value like '%"+value+"%' ");
			}
		}
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		List<Map<String, Object>> list = session.createQuery(hql.toString()).list();
		return list;
	}

	@Override
	public List<Map<String, Object>> loadEntitys() {
		StringBuffer hql = new StringBuffer();
		hql.append(" select new map(t.id as id,t.name as text) from Entity t ");
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		List<Map<String, Object>> list = session.createQuery(hql.toString()).list();
		return list;
	}

	@Override
	public List<Entity> findByIds(String[] entityIds) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select t from Entity t where t.id in (:entityIds) ");
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		Query query = session.createQuery(hql.toString());
		query.setParameterList("entityIds", entityIds);
		List<Entity> list = query.list();
		return list;
	}

}
