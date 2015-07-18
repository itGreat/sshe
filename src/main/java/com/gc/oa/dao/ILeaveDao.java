package com.gc.oa.dao;

import java.util.List;
import java.util.Map;

import com.gc.oa.po.Leave;

/**
 * @author gongchang
 * 描述：
 * 时间：2014-9-17 上午10:51:17
 */
public interface ILeaveDao extends IBaseDao<Leave>{

	List<Map<String, String>> currenttask(String username);

}
