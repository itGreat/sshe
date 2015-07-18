package com.gc.core.criteria;
/**
 * 2014-4-25,gongchang
 * 通用的查询条件字段
 * 实现功能：
 */
public class Criteria {

	/**
	 * id 唯一标识
	 */
	private String id;
	
	/**
	 * userid 用户id
	 */
	private String userId;
	
	/**
	 * 登录账号
	 */
	private String username; 
	
	/**
	 * name 名称
	 */
	private String name;
	
	/**
	 * 部门id
	 */
	private String deptId;

	/**
	 * url 地址、网站、链接
	 */
	private String url;
	
	/**
	 * 值
	 */
	private String value;
	/**
	 * type 类型
	 */
	private String type;
	
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 关键字
	 */
	private String keyword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}