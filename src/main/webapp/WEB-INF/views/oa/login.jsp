<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti-web</title>
</head>
<body>

<form action="${ctx}/oa/checklogin.action" method="post">
	<table>
		<tr>
			<td align="right">用户名：</td>
			<td><!-- <input type="text" name="username" value="jshuz"> -->
				<div style="position: relative;width: 155px;">
			        <span style="margin-left: 100px; width: 18px; overflow: hidden;">
			            <select style=" width: 100%; margin-left: -100px;height:25px;border"
			            onchange="document.getElementById('js_bank_text').value=this.value;document.getElementById('password').value=this.value;">
			                <option value="gongchang">gongchang</option>
			                <option value="gc">gc</option>
			                <option value="tmm">tmm</option>
			                <option value="jshuz">manager</option>
			                <option value="tw">boss</option>
			        </select>
			        </span> 
			        <input id="js_bank_text" name="username" value="gongchang"
			        style="position: absolute; left: 1px; top: 1px;width:calc(100% - 19px);height:21px;border:none;" />
			    </div>    
			</td>
		</tr>
		<tr>
			<td align="right">密码：</td>
			<td><input id="password" type="password" name="password" value="gongchang"></td>
		</tr>
		<tr>
			<td align="right">记住密码：</td>
			<td><input type="checkbox" ></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="登录" ><input type="reset" value="重置" ></td>
		</tr>
	</table>
</form>

</body>
</html>