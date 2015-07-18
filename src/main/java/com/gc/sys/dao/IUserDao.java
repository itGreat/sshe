package com.gc.sys.dao;

import java.util.List;

import com.gc.sys.po.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月6日 下午10:16:21
 */
public interface IUserDao extends IBaseDao<User> {

	User getByUsername(String username);

	List<User> findByDeptId(String deptId);

}
