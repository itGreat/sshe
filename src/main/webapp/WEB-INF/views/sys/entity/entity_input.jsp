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
<form id="js_saveentity_fm" method="post" action="${ctx}/sys/entity/save.action">
	 <input type="hidden" name="entity.id" value="${model.id }"/>
		<table id="add_entity_tb" class="css_entity_tb css_fm_tb">
			<tr class="table_row3">
				<td class="css_td_label" align="right">类型：</td>
				<td class="css_td_el" align="left">
				<%-- <input type="text" id="entitytype"   style="width:80%" name="entity.type" value="${model.type }" maxlength="25"/><font color="#FF0000">*</font> --%>
				<select name="entity.type">
					<option ${model.type eq 'menu' ? "selected='selected'" : ""} value="menu">menu</option>
					<option ${model.type eq 'other' ? "selected='selected'" : ""} value="other">other</option>
				</select>
				</td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">名称：</td>
				<td class="css_td_el" align="left"><input type="text" id="name" name="entity.name" maxlength="50" value="${model.name }" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
	        	<td class="css_td_label" align="right">地址：</td>
		        <td class="css_td_el" align="left">
		        	<input type="text" name="entity.value" value="${model.value }">
		        </td>
	       </tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">备注：</td>
				<td class="css_td_el" align="left"><input type="text" id="remark" name="entity.remark" value="${model.remark }" maxlength="80" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" height="25" align="center" colspan="2">
				<input value="保存" type="button" onclick="saveEntity();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>