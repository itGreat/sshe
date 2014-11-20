package com.gc.sys.service;

import java.util.List;
import java.util.Map;

import com.gc.common.Criteria;
import com.gc.sys.entity.Role;

public interface IRoleService {

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);

	Role get(String id);

	void save(Role role);

	void delete(String id);
	
	void delete(String[] ids);
}
