package com.gc.sys.service;

import java.util.List;
import java.util.Map;

import com.gc.common.Criteria;
import com.gc.sys.entity.Node;

public interface INodeService {

	String loadTree();

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);

	Node get(String id);

	void save(Node node);

	void delete(String id);

	List<Map<String, Object>> getNodes();

	String loadTree(String id);

}
