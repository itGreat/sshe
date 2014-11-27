<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/tags.jsp" %>

<style>
<!--
.tTitle{
	border: none;
	margin-top: 10px;
}
-->
</style>
<script type="text/javascript">
 
</script>
	<form id="js_saveuser_fm" method="post" action="${ctx}/sys/userdept/user_save.action">
	 <input type="hidden" name="user.id" value="${user.id }"/>
		<table id="add_user_tb" class="css_user_tb css_fm_tb">
			<tr class="table_row3">
				<td class="css_td_label" align="right">登录账号：</td>
				<td class="css_td_el" align="left"><input type="text" id="username" ${!empty user.id ? "readonly='readonly'" : ""} style="width:80%" name="user.username" value="${user.username }" maxlength="25"/><font color="#FF0000">*</font></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">用户姓名：</td>
				<td class="css_td_el" align="left"><input type="text" id="name" name="user.name" maxlength="50" value="${user.name }" style="width:80%"><font color="#FF0000">*</font></td>
			</tr>
			<tr class="table_row3">
	        	<td class="css_td_label" align="right">所属部门：</td>
		        <td class="css_td_el" align="left">
		        	<input id="deptid" type="hidden" name="user.dept.id" value="${user.dept.id }"><%--${user.dept.id }  ${user.dept.name }--%>
		        	<input id="deptname" type="text" value="${user.dept.name }" readonly="readonly" onclick="openRadioDept();">
		        </td>
	       </tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">密码：</td>
				<td class="css_td_el" align="left"><input type="password" id="password" name="user.password" value="111111" maxlength="80" style="width:80%"><font color="#FF0000">*</font></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">确认密码：</td>
				<td class="css_td_el" align="left"><input type="password" id="password2" value="111111" maxlength="80" style="width:80%"><font color="#FF0000">*</font></td>
			</tr>
			<%-- <tr class="table_row3">
				<td class="css_td_label" align="right">职责类型：</td>
				<td class="css_td_el" align="left"> 
					<select class="select_n" name="user.dutyType" id="dutyType" style="width: 80px;">
						<c:forEach items="${dutyType }" var="e">
							<option value="${e.key}" ${e.key eq "0" ? "selected='selected'" : ""} >${e.value }</option>
						</c:forEach>
					</select>
					<!-- <div></div> -->
					<c:forEach items="${dutyType }" var="e">
						<input type="checkbox" name="user.dutyTypes" value="${e.key}">${e.value}
					</c:forEach>
				</td>
			</tr> --%>
			<!-- <tr class="table_row3">
				<td class="css_td_label" align="right">性别：</td>
				<td class="css_td_el" align="left"> 
					<select class="select_n" name="user.sex" id="sex" style="width: 80px;">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</td>
			</tr> -->
			<!-- <tr class="table_row3">
				<td class="css_td_label" align="right">身份证号：</td> valid="identityform"
				<td class="css_td_el" align="left"><input id="id_card" name="user.id_card" type="text" maxlength="20" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">手机号码：</td>
				<td class="css_td_el" align="left"><input name="user.mobile_no" type="text" id="mobile_no"maxlength="40" style="width:80%"></td>
			</tr> -->
			<!-- <tr class="table_row3">
				<td width="10%" align="center">联系电话：</td>
				<td align="left"><input type="text" name="mobile" id="mobile" style="width:80%"></td>
			</tr> -->
			<!-- <tr class="table_row3">
				<td class="css_td_label" align="right">电子邮箱：</td>valid="emailform" 
				<td class="css_td_el" align="left"><input type="text" id="email" name="user.email" maxlength="40" style="width:80%"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">住址：</td>
				<td class="css_td_el" align="left"><input type="text" name="user.address" id="address" maxlength="100" style="width: 80%;"></td>
			</tr>
			<tr class="table_row3">
				<td class="css_td_label" align="right">备注：</td>
				<td class="css_td_el" align="left"><input type="text" name="user.note" id="note" maxlength="100" style="width: 80%;"></td>
			</tr> -->
			<tr class="table_row3">
				<td class="css_td_label" height="25" align="center" colspan="2">
				<input value="保存" type="button" onclick="saveUser();" /></td>
			</tr>
		</table>
	</form>