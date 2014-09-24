<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<%-- <link href="${ctx}/css/sys/table.css" rel="stylesheet" type="text/css"></link> --%>


<form id="js_savedept_fm" method="post" action="${ctx}/sys/userdept/dept_save.action">
	<input type="hidden" name="dept.id" value="${dept.id }"/>
	<table id="edit_dept_tb" class="css_fm_tb">
      <tr class="table_row3">
        <td class="css_td_label" align="right">上级部门：</td>
        <td class="css_td_el" align="left">
        	<input id="deptid" type="hidden" name="dept.parent.id"  value="${dept.parent.id }" style="width:80%">
	      	<input id="deptname" type="text" value="${dept.parent.name }" onclick="openRadioDept();"/>
        </td>
      <tr class="table_row3">
        <td class="css_td_label" align="right">部门名称：</td>
        <td class="css_td_el" align="left">
        	<input type="text" id="name" class="input_n" name="dept.name" value="${dept.name }" style="width:80%" title="请输入部门名称">
        	<font color="#FF0000">*</font>
        </td>
      </tr>
      <%-- <tr class="table_row3">
        <td class="css_td_label" align="right">部门职责：</td>
        <td class="css_td_el" align="left">
        	<input type="text" id="duty" class="input_n" name="dept.duty" value="${dept.duty }" style="width:80%" title="请输入部门职责！">
        </td>
      </tr>
      <tr class="table_row3">
        <td class="css_td_label" align="right">部门地址：</td>
        <td class="css_td_el" align="left">
        	<input type="text" name="dept.address" class="input_n" value="${dept.address }" style="width:80%" title="请输入部门地址！">
        </td>
      </tr>
      <tr class="table_row3">
        <td class="css_td_label" align="right">部门电话：</td>
        <td class="css_td_el" align="left">
        	<input type="text" name="dept.phoneList" class="input_n" value="${dept.phoneList }" style="width:80%" title="请输入部门电话！">
        </td>
      </tr> --%>
      <tr class="table_row3">
        <td class="css_td_label" align="center" colspan="2">
        	<input class="ins_button" style="margin-top: 5px;" value="保存" type="button" onclick="saveDept();"/>
        </td>
      </tr>
	</table>
</form>