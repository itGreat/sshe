package com.gc.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.sys.dao.IEntityDao;
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

 

}
