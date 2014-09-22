package com.gc.sys.dao.impl;

import java.util.List;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.gc.sys.dao.IUserDao;
import com.gc.sys.entity.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年7月14日 上午9:47:42
 */
@Repository
public class UserDao extends BaseDaoImpl<User> implements IUserDao{
	
	 
	
	/* (non-Javadoc)
	 * @see com.gc.sys.dao.IUserDao#getByUsername(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getByUsername(String username) {
		StringBuffer  hql = new StringBuffer();
		hql.append(" select t from User t where t.username = "+username+" ");
		Session session = getCurrentSession();
		Query query = session.createQuery(hql.toString());
		List<User> list = query.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
