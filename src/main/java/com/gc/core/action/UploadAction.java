package com.gc.core.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONObject;
import com.gc.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@Action("/")
//@ParentPackage("json-default")
public class UploadAction extends ActionSupport{
	
	@Action(value="upload")
	public void upload() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=utf-8");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("data", 1);
//		String jsonString = JSONObject.toJSONString(resultMap);
//		log.info(jsonString);
		response.getWriter().print(JSONObject.toJSON(resultMap));
	}
}
