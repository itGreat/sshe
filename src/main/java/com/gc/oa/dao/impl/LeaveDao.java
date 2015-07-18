package com.gc.oa.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.gc.core.dao.BaseDaoImpl;
import com.gc.oa.dao.ILeaveDao;
import com.gc.oa.po.Leave;

@Repository
public class LeaveDao extends BaseDaoImpl<Leave> implements ILeaveDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> currenttask(String username) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ta.id_ 'activitiTaskId',ta.task_def_key_ 'taskKey',t.procInstId 'procInstId', t.id 'leaveId',  t.reason 'reason',  t.uname 'uname' ");
		sql.append(" FROM t_oa_leave t,act_ru_task ta,act_ru_identitylink i ");
		sql.append(" WHERE t.procInstId=ta.proc_inst_id_ AND ta.id_=i.task_id_ AND i.user_id_ ='"+username+"' ");
		Session session = getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery(sql.toString());
		List<Map<String, String>> list = sqlquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	@Override
	public Leave get(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Leave get(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
