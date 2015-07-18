package com.gc.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.gc.sys.po.Entity;
=======
import com.gc.common.Criteria;
import com.gc.sys.entity.Entity;
>>>>>>> 4b722529099eeeb99ff1d4a9132d0629d853a2b1

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月22日 下午2:27:14
 */
@Repository
public interface IEntityDao extends IBaseDao<Entity> {

	List<Entity> findByUid(String id);

	List<Map<String, Object>> loadData();

	List<Map<String, Object>> loadDataByCriteria(Criteria criteria);

	List<Map<String, Object>> loadEntitys();

	List<Entity> findByIds(String[] entityIds);

}
