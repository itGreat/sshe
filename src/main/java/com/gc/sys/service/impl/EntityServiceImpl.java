package com.gc.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.Criteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.entity.Entity;
import com.gc.sys.service.IEntityService;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月23日 上午11:33:41
 */
@Service
public class EntityServiceImpl implements IEntityService{
	
	@Autowired
	private IEntityDao entityDao;

	@Override
	public List<Map<String, Object>> loadData() {
		return entityDao.loadData();
	}

	@Override
	public void save(Entity entity) {
		final boolean isNew = StringUtils.isEmpty(entity.getId());
		if(isNew){
			entityDao.save(entity);
		}else{
			entityDao.update(entity);
		}
		
//		entityDao.saveOrUpdate(entity);
	}

	@Override
	public Entity get(String id) {
		return entityDao.get(id);
	}

	@Override
	public void delete(String id) {
		Entity entity = entityDao.get(id);
		entityDao.delete(entity);
	}

	@Override
	public void delete(String[] ids) {
		
	}

	@Override
	public List<Map<String, Object>> loadDataByCriteria(Criteria criteria) {
		return entityDao.loadDataByCriteria(criteria);
	}

	@Override
	public List<Map<String, Object>> getEntitys() {
		return entityDao.loadEntitys();
	}

 

}
