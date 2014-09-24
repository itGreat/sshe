package com.gc.sys.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.gc.sys.dao.IUserDao;
import com.gc.sys.entity.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年7月14日 上午9:47:42
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDao extends BaseDaoImpl<User> implements IUserDao{
	
	/* (non-Javadoc)
	 * @see com.gc.sys.dao.IUserDao#getByUsername(java.lang.String)
	 */
	
	@Override
	public User getByUsername(String username) {
		StringBuffer  hql = new StringBuffer();
		hql.append(" select t from User t where t.username = "+username+" ");
		Session session = getCurrentSession();
		Query query = session.createQuery(hql.toString());
		List<User> list = query.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
	@Override
	public User get(Serializable id) {
		Session session = getCurrentSession();
		User user = (User)session.get(User.class, id);
		return user;
	}

	@Override
	public List<User> findByDeptId(String deptId) {
		StringBuffer  hql = new StringBuffer();
		hql.append(" select t from User t where t.dept.id = '"+deptId+"' ");
		Session session = getCurrentSession();
		Query query = session.createQuery(hql.toString());
		List<User> list = query.list();
		return list;
	}

}
