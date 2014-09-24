package com.gc.sys.service;

import com.gc.sys.entity.Dept;

public interface IDeptService {

	String loadTree();

	Dept get(String deptId);

	void delete(String deptId);

	void save(Dept dept);

	public String check(String deptId);
}
