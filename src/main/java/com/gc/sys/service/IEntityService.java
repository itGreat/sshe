package com.gc.sys.service;

import java.util.List;

import com.gc.sys.common.EntityCriteria;
import com.gc.sys.po.Entity;

public interface IEntityService {

	List<Entity> findByCriteria(EntityCriteria criteria);

	void save(Entity entity);

	Entity get(String entityId);

	void delete(String entityId);


}
