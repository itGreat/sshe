<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.css_rolenode_div{
	border: 1px solid red;
	width: 270px;
	height: 330px;
}
</style>
<script type="text/javascript">
$(function(){
	var url = ctx+"/sys/role/tree.action";
	var data = {};
	$.post(url,data,function(result){
	var db = $.parseJSON(result);
	 $('#js_sys_tree').tree({ data: db });
	},'text');
});
</script>
<div id="js_rolenode_box" class="css_rolenode_div">
		asdfs
	<ul id="js_node_tree" class="easyui-tree" data-options="url:'${ctx}/sys/role/tree.action',method:'post',animate:true,checkbox:true">
	</ul>
</div>	
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
			 
		</table>
		
		<!-- <div></div> -->
		<center>
			<input value="保存" style="" type="button" onclick="saveRole();" />
		</center>
	</form>