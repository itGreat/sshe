package com.gc.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gc.sys.po.Entity;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:27:14
 */
@Repository
public interface IEntityDao extends IBaseDao<Entity> {

	List<Entity> findByUid(String id);

}
