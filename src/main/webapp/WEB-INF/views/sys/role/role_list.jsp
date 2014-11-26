<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp"%>
<div id="role_dialog"></div>
<div class="div_criteria">
	<form id="js_criteria_fm" action="">
		名称：
		<input type="text" name="criteria.name"/>
		
		<input id="js_search_btn" type="button" value="查询"/>
		<input type="reset" value="重置"/>
	</form>
</div>
 <table id="role_list" class="easyui-datagrid" 
	data-options="ingleSelect:true,collapsible:true,url:'${ctx}/sys/role/data.action',method:'post',
	toolbar: [{
		iconCls: 'icon-add',
		text:'新增',
		handler: addRole
	},'-',{
		iconCls: 'icon-edit',
		text:'编辑',
		handler: editRole
	},'-',{
		iconCls: 'icon-remove',
		text:'删除',
		handler: delRole
	}]">
	<thead>
		<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'name',width:200">名称</th>
		<th data-options="field:'remark',width:260">备注</th>
		</tr>
	</thead>
	
</table>
<script type="text/javascript">
	$(function(){
		$("#js_search_btn").click(function(){
			var data = $("#js_criteria_fm").serializeJson();
		    $('#role_list').datagrid('load',data);
		});
	});

	function addRole(){
		var url = ctx+"/sys/role/input.action";
		openRoleDialog('新增角色',url);
	}
	
	function editRole(){
		var checkeds = $('#role_list').datagrid('getChecked');
		if(checkeds.length > 1){
			alert('只能编辑一条!');return;
		}
		var url = ctx+"/sys/role/input.action?id="+checkeds[0].id;
		openRoleDialog('编辑角色',url);
	}
	
	function delRole(){
// 		var id = $('#role_list').datagrid('getSelected').id || '';
		var checkeds =$('#role_list').datagrid('getChecked');
		if(!!!checkeds){
			alert('请选择需要删除的记录'); return;
		}
		if(!confirm('确定删除吗?')){
			return;
		}
		var ids = $(checkeds).map(function(){ return this.id;}).get().join(",");
		var url = ctx+"/sys/role/delete.action";
		var data = {ids:ids};
		$.post(url,data,function(result){
			if(result == '1'){
				$('#role_list').datagrid('reload');
				alert('操作成功!');
			}else{
				alert('操作失败!');
			}
		},'text');
	}
	
	function openRoleDialog(title,url){
		$('#role_dialog').dialog({
	        title: title,
	        width: 400,
	        height: 500,
	        closed: false,
	        cache: false,
	        href: url,
	        modal: true
        });
// 		$('#dd').dialog('refresh', 'new_content.php');
	}
	function saveRole(){
// 		var box = $('#role_box');
		var $fm = $("#js_saverole_fm");
		var entityIds = $($("#js_node_tree",$fm).tree('getChecked')).map(function(){return this.attributes.entityId;}).toArray().join(',');
		var html = "<input type='hidden' name='role.entityIds' value='"+ entityIds +"' />";
		$fm.append(html);
		var data = $fm.serializeArray();
		var url = $fm.attr('action');
	    $.post(url,data,function(result){
	    	if(result == 1){
	    		$('#role_dialog').dialog('close');
	    		$('#role_list').datagrid('reload');
	        }else{
	            alert("操作失败");
	        }
	 	},'text');
	}
</script>
 