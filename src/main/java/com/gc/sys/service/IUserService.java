package com.gc.sys.service;

import java.util.List;

import com.gc.sys.common.UserCriteria;
import com.gc.sys.po.User;

public interface IUserService {

	void save(User user);

	List<User> findByCriteria(UserCriteria criteria);

	User get(String userId);

	void delete(String userId);

}
