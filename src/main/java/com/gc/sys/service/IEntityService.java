package com.gc.sys.service;

import java.util.List;
<<<<<<< HEAD

import com.gc.sys.common.EntityCriteria;
import com.gc.sys.po.Entity;

public interface IEntityService {
=======
import java.util.Map;

import com.gc.common.Criteria;
import com.gc.sys.entity.Entity;

public interface IEntityService extends IBaseService<Entity>{

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);

	List<Map<String, Object>> getEntitys();
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

	List<Entity> findByCriteria(EntityCriteria criteria);

	void save(Entity entity);

	Entity get(String entityId);

	void delete(String entityId);


}
