<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${ctx}/sys/checklogin.action" method="post">
	用户名：<input type="text" name="username"><br/>
	密码：<input type="password" name="password"><br/>
	记住密码：<input type="checkbox" ><br/>
	<input type="submit" value="登录" ><input type="reset" value="重置" >
</form>

</body>
</html>