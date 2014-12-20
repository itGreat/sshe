<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp"%>
 <div class="div_criteria">
		<form id="js_search_fm" action="">
			<input class="clear_input " type="hidden" id="js_deptId" name="criteria.deptId" value=""/>
			<!-- <input class="input_n clear_input" type="hidden" id="js_page_no" name="page.pageNo" value=""/> -->
			名称： <input type="text" class="clear_input" style="width: 110px;" id="Entityname" name="criteria.name" />
			类型： <input type="text" class="clear_input" style="width: 110px;" id="name" name="criteria.type" />
			链接： <input type="text" class="clear_input" style="width: 110px;" id="name" name="criteria.value" />
			<input id="js_Entitysearch_btn" type="button" value="查询">
			<input id="js_search_clear" type="button" value="重置" onclick="$('.clear_input').val('');">
		</form>
	</div>
 <div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newEntity()">添加</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editEntity()">编辑</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delEntity()">删除</a>
</div>
 <table  id="js_entity_tb"  class="easyui-datagrid" style="width:700px;height:250px"
	data-options="singleSelect:true,collapsible:true,url:'${ctx}/sys/entity/list_json.action',method:'get'">
	<thead>
		<tr>
		<th data-options="field:'name',width:150">名称</th>
		<th data-options="field:'type',width:100">类型</th>
		<th data-options="field:'value',width:280">链接</th>
		<th data-options="field:'remark',width:150">备注</th>
		</tr>
	</thead>
	<tbody>
	
	</tbody>
</table>

<!-- <div id="js_entity_dialog" class="easyui-dialog" style="display: none;width: 300px;height: 350px;"   data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"></div> -->
<div id="js_entity_dialog"></div>
<!-- <div id="dlg" class="easyui-dialog"
	style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
	buttons="#dlg-buttons">
	<div class="ftitle">Entity Information</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>First Name:</label> <input name="firstname"
				class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>Last Name:</label> <input name="lastname"
				class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>Phone:</label> <input name="phone" class="easyui-textbox">
		</div>
		<div class="fitem">
			<label>Email:</label> <input name="email" class="easyui-textbox"
				validType="email">
		</div>
	</form>
</div> -->
<!-- <div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="saveEntity()" style="width: 90px">Save</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
		style="width: 90px">Cancel</a>
</div> -->
<script type="text/javascript">
	var url;
	function newEntity() {
		var url2 = ctx+"/sys/entity/input.action"
	    $('#js_entity_dialog').dialog({
	        title: 'My Dialog',
	        width: 400,
	        height: 200,
	        closed: false,
	        cache: false,
	        href: url2,
	        modal: true
	        });
	}
	function editEntity() {
		var row = $('#js_entity_tb').datagrid('getSelected');
		if(!!!row){
			alert("选择记录!"); return;
		}
		var url2 = ctx+"/sys/entity/input.action?entityId="+row.id;
	    $('#js_entity_dialog').dialog({
	        title: 'My Dialog',
	        width: 400,
	        height: 200,
	        closed: false,
	        cache: false,
	        href: url2,
	        modal: true
	        });
	}
	function saveEntity() {
		var $fm = $("#js_enity_fm");
		var params = $fm.serialize();
		var url = $fm.attr('action');
	 	var data = params;
	    $.post(url,data,function(result){
	    	if(result == 1){
	    		$('#js_entity_dialog').dialog('close');
// 	    		var url = ctx+'/sys/entity/list_json.action';
// 	    		$('#js_entity_tb').datagrid({url:url});
	    		$('#js_entity_tb').datagrid('reload');
	        }else{
	            alert("操作失败");
	        }
	 	},'text');
	}
	
	function delEntity(){
		var row = $('#js_entity_tb').datagrid('getSelected');
		if(!!!row){
			alert("选择记录!"); return;
		}
		var url = ctx+'/sys/entity/del.action';
	 	var data = {"entityId":row.id};
	    $.post(url,data,function(result){
	    	if(result == 1){
	    		$('#js_entity_tb').datagrid('reload');
	        }else{
	            alert("操作失败");
	        }
	 	},'text');
		
	}
	 
</script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>