package com.gc.sys.service;


public interface IBaseService<T> {

	void save(T t);

	T get(String id);

	void delete(String id);
	
	void delete(String[] ids);
	
}
