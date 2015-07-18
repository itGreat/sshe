package com.gc.sys.service.impl;

import java.util.List;
<<<<<<< HEAD

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.core.exception.ValidateException;
import com.gc.sys.common.EntityCriteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.po.Entity;
=======
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.Criteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.entity.Entity;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
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
<<<<<<< HEAD
	public List<Entity> findByCriteria(EntityCriteria criteria) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Entity t where 1=1 ");
		if(null != criteria){
			//模糊查询名称
			String name = criteria.getName();
			if(StringUtils.isNotEmpty(name)){
				hql.append(" and t.name like '%"+name.trim()+"%' ");
			}
			//模糊查询类型
			String type = criteria.getType();
			if(StringUtils.isNotEmpty(type)){
				hql.append(" and t.type = '"+type+"' ");
			}
			String value = criteria.getValue();
			if(StringUtils.isNotEmpty(value)){
				hql.append(" and t.value like '%"+value.trim()+"%' ");
			}
		}
		hql.append(" order by t.id desc");
		return entityDao.find(hql.toString());
=======
	public List<Map<String, Object>> loadData() {
		return entityDao.loadData();
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1
	}

	@Override
	public void save(Entity entity) {
<<<<<<< HEAD
		if(null == entity){
			throw new ValidateException("save entity is null");
		}
		Boolean isNew = StringUtils.isEmpty(entity.getId());
		if(isNew){
			entityDao.save(entity);
		}else{
			Entity entity_ = entityDao.get(entity.getId());
			BeanUtils.copyProperties(entity, entity_);
			entityDao.saveOrUpdate(entity_);
		}
	}

	@Override
	public Entity get(String entityId) {
		return entityDao.get(entityId);
	}
=======
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

 
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

	@Override
	public void delete(String entityId) {
		Entity entity = entityDao.get(entityId);
		entityDao.delete(entity);
	}
}
