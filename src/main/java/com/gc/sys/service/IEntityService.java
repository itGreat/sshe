package com.gc.sys.service;

import java.util.List;
import java.util.Map;

import com.gc.common.Criteria;
import com.gc.sys.entity.Entity;

public interface IEntityService extends IBaseService<Entity>{

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);


}
