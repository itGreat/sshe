<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<link href="${ctx}/css/sys/userdept_main.css" rel="stylesheet" type="text/css"></link>
<script src="${ctx}/js/sys/userdept/main.js" type="text/javascript"></script>
<style type="text/css">

</style>
<div id="js_ud_dialog" style="display: none;"  data-options="iconCls:'icon-save',resizable:true,modal:true"></div>
<jsp:include page="../comm/radio_dept.jsp"/>
<%-- 部门树 --%>
<div class="div_left">
	<div class="css_title">菜单树</div>
     <ul id="js_ud_tree" class="easyui-tree">
    </ul>
</div>
<!-- <div class="div_right">
	<div class="div_criteria">
		<form id="js_search_fm" action="">
			<input class="clear_input " type="hidden" id="js_deptId" name="criteria.deptId" value=""/>
			<input class="input_n clear_input" type="hidden" id="js_page_no" name="page.pageNo" value=""/>
			登录账号： <input type="text" class="clear_input" style="width: 110px;" id="username" name="criteria.username" />
			用户姓名： <input type="text" class="clear_input" style="width: 110px;" id="name" name="criteria.name" />
			<input id="js_usersearch_btn" type="button" value="查询">
			<input id="js_search_clear" type="button" value="重置" onclick="$('.clear_input').val('');">
		</form>
	</div>
	<div class="div_btn">
		<input id="js_adddept_btn" type="button" value="新增部门">
        <input id="js_editdept_btn" type="button" value="编辑部门">
        <input id="js_deldept_btn" type="button" value="删除部门">
        <input id="js_adduser_btn" type="button" value="新增用户">
         <input id="js_demo_btn" type="button" value="demo" onclick="openRadioDept();">
	</div>
	<div class="listBox"  style="width:100%">
	<table id="js_user_tb"></table>
	</div>
</div> -->
<script>

</script>