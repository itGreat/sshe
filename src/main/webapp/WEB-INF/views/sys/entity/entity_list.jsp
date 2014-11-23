<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp"%>

<!--  <div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
</div> -->
	<div id="entity_dialog"></div>
	<div class="div_criteria">
		<form id="js_criteria_fm" action="">
			类型：
			<select name="criteria.type">
					<option value="menu">menu</option>
					<option value="other">other</option>
			</select>
			名称：
			<input type="text" name="criteria.name"/>
			地址：
			<input type="text" name="criteria.value"/>
			
			<input id="js_search_btn" type="button" value="查询"/>
			<input type="reset" value="重置"/>
		</form>
	</div>
 <table id="entity_list" class="easyui-datagrid" 
	data-options="ingleSelect:true,collapsible:true,url:'${ctx}/sys/entity/data.action',method:'post',
	toolbar: [{
		iconCls: 'icon-add',
		text:'新增',
		handler: addEntity
	},'-',{
		iconCls: 'icon-edit',
		text:'编辑',
		handler: editEntity
	},'-',{
		iconCls: 'icon-remove',
		text:'删除',
		handler: delEntity
	}]">
	<thead>
		<tr>
		<th data-options="field:'type',width:80">类型</th>
		<th data-options="field:'name',width:200">名称</th>
		<th data-options="field:'value',width:280">地址</th>
		<th data-options="field:'remark',width:260">备注</th>
		</tr>
	</thead>
	<tbody>
	
	</tbody>
</table>
<script type="text/javascript">
	$(function(){
		$("#js_search_btn").click(function(){
			var data = $("#js_criteria_fm").serializeJson();
		    $('#entity_list').datagrid('load',data);
		});
	});

	function addEntity(){
		var url = ctx+"/sys/entity/input.action";
		openEntityDialog('新增功能',url);
	}
	
	function editEntity(){
		var id = $('#entity_list').datagrid('getSelected').id || '';
		var url = ctx+"/sys/entity/input.action?id="+id;
		openEntityDialog('编辑功能',url);
	}
	
	function delEntity(){
		if(!confirm("确认删除吗?")){return;}
		var id = $('#entity_list').datagrid('getSelected').id || '';
		var url = ctx+"/sys/entity/delete.action";
		var data = {id:id};
		$.post(url,data,function(result){
			if(result == '1'){
				alert('操作成功!');
				$('#entity_list').datagrid('reload');
			}else{
				alert('操作失败!');
			}
		},'text');
	}
	
	function openEntityDialog(title,url){
		$('#entity_dialog').dialog({
	        title: title,
	        width: 400,
	        height: 200,
	        closed: false,
	        cache: false,
	        href: url,
	        modal: true
        });
// 		$('#dd').dialog('refresh', 'new_content.php');
	}
	function saveEntity(){
		var $fm = $("#js_saveentity_fm");
		var data = $fm.serializeArray();
		var url = $fm.attr('action');
	    $.post(url,data,function(result){
	    	if(result == 1){
	    		$('#entity_dialog').dialog('close');
	    		$('#entity_list').datagrid('reload');
	        }else{
	            alert("操作失败");
	        }
	 	},'text');
	}
</script>
 