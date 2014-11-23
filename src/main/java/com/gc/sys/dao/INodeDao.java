package com.gc.sys.dao;

import java.util.List;
import java.util.Map;

import com.gc.sys.entity.Node;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:27:14
 */
public interface INodeDao extends IBaseDao<Node> {

	List<Map<String, Object>> getNodes();

}
