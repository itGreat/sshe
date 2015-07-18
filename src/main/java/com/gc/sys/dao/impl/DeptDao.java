package com.gc.sys.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gc.core.dao.BaseDaoImpl;
import com.gc.sys.dao.IDeptDao;
import com.gc.sys.po.Dept;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:35:44
 */
@Repository
public class DeptDao extends BaseDaoImpl<Dept> implements IDeptDao{

	@Override
	public Dept get(Serializable id) {
		Session session = getCurrentSession();
		Dept dept = (Dept)session.get(Dept.class, id);
		return dept;
	}

}
