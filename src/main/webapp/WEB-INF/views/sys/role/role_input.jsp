<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/tags.jsp"%>
<style type="text/css">
.css_rolenode_div{
	border: 1px solid #99BBE8;
	width: 270px;
	height: 350px;
	margin-left: 60px;
}
</style>
<script type="text/javascript">
/* $(function(){
	var url = ctx+"/sys/role/tree.action";
	var data = {};
	$.post(url,data,function(result){
	var db = $.parseJSON(result);
	 $('#js_sys_tree').tree({ data: db });
	},'text');
}); */
</script>
<div id="role_box">

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
				<td class="css_td_label" align="right">权限：</td>
				<td class="css_td_el" align="left"></td>
			</tr>
		</table>
		
		<div id="js_rolenode_box" class="css_rolenode_div">
			<ul id="js_node_tree" class="easyui-tree" data-options="url:'${ctx}/sys/role/tree.action?id=${model.id }',method:'post',animate:true,checkbox:true"></ul>
		</div>
		<center>
			<input value="保存" type="button" onclick="saveRole();" style="margin-top:10px;"/>
		</center>
	</form>
</div>