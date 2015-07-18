package com.gc.sys.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gc.common.Criteria;
import com.gc.sys.dao.IRoleDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.entity.Role;

@SuppressWarnings("unchecked")
@Repository
public class RoleDao extends BaseDaoImpl<Role> implements IRoleDao {

	@Override
	public List<Map<String, Object>> loadDataByCriteria(Criteria criteria) {
		StringBuffer hql = new StringBuffer();
		// type name value remark 
		hql.append(" select new map(t.id as id ,t.name as name ,t.remark as remark) from Role t where 1=1 ");
		if(null != criteria){
			String name = criteria.getName();
			if(StringUtils.isNotBlank(name)){
				hql.append(" and t.name like '%"+name+"%' ");
			}
		}
		hql.append(" order by t.id desc ");
		Session session = getCurrentSession();
		List<Map<String, Object>> list = session.createQuery(hql.toString()).list();
		return list;
	}

	@Override
	public Role get(Serializable id) {
		Session session = getCurrentSession();
		return (Role) session.get(Role.class, id);
	}
	 
}
