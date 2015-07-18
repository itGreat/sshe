package com.gc.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.core.exception.ValidateException;
import com.gc.sys.common.UserCriteria;
import com.gc.sys.dao.IDeptDao;
import com.gc.sys.dao.IUserDao;
import com.gc.sys.po.Dept;
import com.gc.sys.po.User;
import com.gc.sys.service.IUserService;


/**
 * @author gongchang
 * 描述：
 * 时间：2014年7月14日 上午9:47:33
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public void save(User user) {
		if(null == user){
			throw new ValidateException("save user is null");
		}
		//关联部门
		final String did = null != user.getDept() ? user.getDept().getId() : null;
		if(StringUtils.isNotEmpty(did)){
			Dept dept = deptDao.get(did);
			user.setDept(dept);
		}else{
			user.setDept(null);
		}
		
		Boolean isNew = StringUtils.isEmpty(user.getId());
		if(isNew){
			
			userDao.save(user);
		}else{
			User entity = userDao.get(user.getId());
			entity.setName(user.getName());
			entity.setPassword(user.getPassword());
			
			userDao.saveOrUpdate(entity);
		}
	}

	@Override
	public List<User> findByCriteria(UserCriteria criteria) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from User t where t.deleted = 0 ");
		if(null != criteria){
			//模糊查询登录账号
			String deptId = criteria.getDeptId();
			if(StringUtils.isNotEmpty(deptId)){
				hql.append(" and t.dept.id = '"+deptId+"' ");
			}
			//模糊查询登录账号
			String username = criteria.getUsername();
			if(StringUtils.isNotEmpty(username)){
				hql.append(" and t.username like '%"+username.trim()+"%' ");
			}
			//模糊查询用户姓名
			String name = criteria.getName();
			if(StringUtils.isNotEmpty(name)){
				hql.append(" and t.name like '%"+name.trim()+"%' ");
			}
		}
		hql.append(" order by t.id desc");
		List<User> list = userDao.find(hql.toString());
		return list;
	}

	@Override
	public User get(String userId) {
		return userDao.get(userId);
	}

	@Override
	public void delete(String userId) {
		User user = userDao.get(userId);
		userDao.delete(user);
	}

	@Override
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

}
