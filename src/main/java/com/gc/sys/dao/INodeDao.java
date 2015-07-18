package com.gc.sys.dao;

<<<<<<< HEAD
import com.gc.sys.po.Node;
=======
import java.util.List;
import java.util.Map;

import com.gc.sys.entity.Node;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:27:14
 */
public interface INodeDao extends IBaseDao<Node> {

	List<Map<String, Object>> getNodes();

}
