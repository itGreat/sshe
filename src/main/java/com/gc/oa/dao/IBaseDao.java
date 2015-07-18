package com.gc.oa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月6日 下午10:17:00
 */
public interface IBaseDao<T> {
	
	/**
	 * @author gongchang
	 * 功能：保存
	 * 时间：2014年9月6日 下午10:18:56
	 */
	/*public T save(T t);
	
	public T get(String id);
	
	public List<T> list();
	*/
	
	/**/
	
	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);
	
}
