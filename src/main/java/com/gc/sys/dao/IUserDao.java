package com.gc.sys.dao;

import com.gc.sys.entity.User;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月6日 下午10:16:21
 */
public interface IUserDao {

	User getByUsername(String username);

}
