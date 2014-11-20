<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
<form id="js_saverole_fm" method="post" action="${ctx}/sys/role/save.action">
	 <input type="hidden" name="role.id" value="${model.id }"/>
		<table id="add_role_tb" class="css_role_tb css_fm_tb">
			<tr class="table_row3">
				<td class="css_td_label" align="right">名称：</td>
				<td class="css_td_el" align="left"><input type="text" id="name" name="role.name" maxlength="50" value="${model.name }" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">备注：</td>
				<td class="css_td_el" align="left"><input type="text" id="remark" name="role.remark" value="${model.remark }" maxlength="80" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" height="25" align="center" colspan="2">
				<input value="保存" type="button" onclick="saveRole();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>