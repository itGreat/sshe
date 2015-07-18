package com.gc.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.core.exception.ValidateException;
import com.gc.sys.common.EntityCriteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.po.Entity;
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
	}

	@Override
	public void save(Entity entity) {
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

	@Override
	public void delete(String entityId) {
		Entity entity = entityDao.get(entityId);
		entityDao.delete(entity);
	}
}
