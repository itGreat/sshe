package com.gc.sys.dao;

import java.util.List;
import java.util.Map;

import com.gc.common.Criteria;
import com.gc.sys.entity.Role;

public interface IRoleDao extends IBaseDao<Role> {

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);

}
