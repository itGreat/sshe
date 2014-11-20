package com.gc.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.Criteria;
import com.gc.sys.dao.IRoleDao;
import com.gc.sys.entity.Role;
import com.gc.sys.service.IRoleService;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月23日 上午11:33:41
 */
@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public List<Map<String, Object>> loadDataByCriteria(Criteria criteria) {
		return roleDao.loadDataByCriteria(criteria);
	}

	@Override
	public Role get(String id) {
		return roleDao.get(id);
	}

	@Override
	public void save(Role role) {
		final boolean isNew = StringUtils.isEmpty(role.getId());
		if(isNew){
			roleDao.save(role);
		}else{
			roleDao.update(role);
		}
		
	}

	@Override
	public void delete(String id) {
		Role role = roleDao.get(id);
		roleDao.delete(role);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			Role role = roleDao.get(id);
			roleDao.delete(role);
		}
	}
}
