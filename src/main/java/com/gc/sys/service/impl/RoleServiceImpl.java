package com.gc.sys.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.common.Criteria;
import com.gc.sys.dao.IEntityDao;
import com.gc.sys.dao.IRoleDao;
import com.gc.sys.entity.Entity;
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
	@Autowired
	private IEntityDao entityDao;

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
		
		if(StringUtils.isNotEmpty(role.getEntityIds())){
			String[] entityIds = StringUtils.split(role.getEntityIds(),",");
			List<Entity> list = entityDao.findByIds(entityIds);
			Set<Entity> entitys = new HashSet<Entity>(list);
			role.setEntities(entitys);
		}
		
		if(isNew){
			roleDao.save(role);
		}else{
			Role model = roleDao.get(role.getId());
			model.setName(role.getName());
			model.setRemark(role.getRemark());
			//将原功能实体分装成map
			Set<Entity> entities = model.getEntities();
			Map<String,Entity> map = new HashMap<String, Entity>(entities.size());
			for (Entity entity : entities) {
				map.put(entity.getId(), entity);
			}
			
			//添加新功能实体，移除存在的
			for (Entity roleEntity : role.getEntities()) {
				Entity modelEntity = map.get(roleEntity.getId());
				if(null == modelEntity){
					entities.add(roleEntity);
				}else{
					map.remove(roleEntity.getId());
				}
			}
			
			//删除功能实体，map最后剩下的就是需要删除的
			entities.removeAll(map.values());
			roleDao.update(model);
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
