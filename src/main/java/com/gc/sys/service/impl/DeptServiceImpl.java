package com.gc.sys.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gc.common.ValidateException;
import com.gc.sys.dao.IDeptDao;
import com.gc.sys.dao.IUserDao;
import com.gc.sys.entity.Dept;
import com.gc.sys.entity.User;
import com.gc.sys.service.IDeptService;

/**
 * @author gongchang
 * 描述：
 * 时间：2014年9月23日 上午11:33:41
 */
@Service
public class DeptServiceImpl implements IDeptService{
	
	@Autowired
	private IDeptDao deptDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public String loadTree() {
		String str = null;
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Dept t where t.parent is null ");
		List<Dept> list = deptDao.find(hql.toString());
		if(!CollectionUtils.isEmpty(list)){
			StringBuffer buf=new StringBuffer("[");
			boolean notFirst=false;
			for (Dept dept : list) {
				if(notFirst){
					buf.append(",");
				}
				buf.append("{");
				buf.append(getJsonStr(dept));
				buf.append(getChildDept(dept));
				buf.append("}");
				notFirst=true;
			}
			buf.append("]");
			str = buf.toString();
		}
		return str;
	}
	
	private String getJsonStr(Dept dept){
		StringBuffer buf=new StringBuffer();
		buf.append("\"id\" : \""+dept.getId()+"\" ");
		buf.append(",\"text\" : \""+dept.getName()+"\" ");
		/*String stats = CollectionUtils.isEmpty(Dept.getChildren()) ? "closed" : "open";
		buf.append(", \"state\": \""+stats+"\" ");*/
		/*buf.append(",\"attributes\":{");
		buf.append("\"url\" :\""+Dept.getEntity().getValue()+"\" ");
		buf.append("}");*/
		return buf.toString();
	}

	private String getChildDept(Dept dept){
		StringBuffer buf=new StringBuffer();
		if(!CollectionUtils.isEmpty(dept.getChildren())){
			buf.append(",\"children\":[");
			//查询下一个记录
			int i=0;
			for (Dept dept_ : dept.getChildren()) {
				if(i==1){
					buf.append(",");
				}
				buf.append("{");
				buf.append(getJsonStr(dept_));
				buf.append(getChildDept(dept_));
				i=1;
				buf.append("}");
			}
			buf.append("]");
		}
		return buf.toString();
	}

	@Override
	public Dept get(String deptId) {
		return deptDao.get(deptId);
	}

	@Override
	public void delete(String deptId) {
		Dept dept = deptDao.get(deptId);
		deptDao.delete(dept);
	}

	@Override
	public void save(Dept dept) {
		if(null == dept){
			throw new ValidateException("save dept is null");
		}
		//关联部门
		final String pid = null != dept.getParent() ? dept.getParent().getId() : null;
		if(StringUtils.isNotEmpty(pid)){
			Dept parent = deptDao.get(pid);
			dept.setParent(parent);
		}
		
		Boolean isNew = StringUtils.isEmpty(dept.getId());
		if(isNew){
			
			deptDao.save(dept);
		}else{
			Dept entity = deptDao.get(dept.getId());
			entity.setName(dept.getName());
			deptDao.saveOrUpdate(entity);
		}
	}
	
	/**
	 * 2014-4-18,gongchang
	 * 功能： 删除部门，检查是否符合删除条件
	 * 逻辑描述： 
	 * 参数：无
	 * 返回结果：无
	 */
	public String check(String deptId) {
		if(StringUtils.isEmpty(deptId)){
			throw new ValidateException("check deptId is empty");
		}
		StringBuffer msg = new StringBuffer("");
		Dept Dept = deptDao.get(deptId);
		Set<Dept> children = Dept.getChildren();
		if(!CollectionUtils.isEmpty(children)) {
			msg.append("该部门下面有子部门无法删除，请先删除子部门!");
			return msg.toString();
		}
		List<User> users = userDao.findByDeptId(deptId);
		if(!CollectionUtils.isEmpty(users)) {
			msg.append("该部门下面有人员无法删除，请先删除人员!");
			return msg.toString();
		}
		return msg.toString();
	}
}
