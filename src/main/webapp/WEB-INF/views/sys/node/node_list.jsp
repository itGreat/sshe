<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<style type="text/css">
.div_left{
 	width: 225px;
 	height: 100%;
 	border: 1px solid red;
	float: left;
}
.div_left .css_title{
	background-color: #106DCD;
    color: white;
    height: 25px;
    line-height: 25px;
    text-align: center;
    font: normal bold 14px arial,sans-serif;
}
.div_right{
 	height: 100%;
 	border: 1px solid green;
 	margin-left: 230px !important;
}
.div_btn{
	border: 1px solid grey;
	padding-top:5px;
	height: 30px;
}
</style>
<div id="js_node_dialog"></div>
<%-- 部门树 --%>
<div class="div_left">
	<div class="css_title">菜单树</div>
    <ul id="js_node_tree" class="easyui-tree" data-options="url:'${ctx}/sys/node/tree.action',method:'post',animate:true">
    </ul>
</div>
<div class="div_right">
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
		<input id="js_addnode_btn" type="button" value="新增">
        <input id="js_editnode_btn" type="button" value="编辑">
        <input id="js_delnode_btn" type="button" value="删除">
	</div>
	<div class="listBox"  style="width:100%">
	<table id="js_user_tb"></table>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#js_addnode_btn").click(function(){
		var node = $('#js_node_tree').tree('getSelected');
		var parentId = null != node ? node.id : ''; 
// 		if(!!!node){
// 			$.messager.alert('提示','请选择!');
// 		}
		var url = ctx+"/sys/node/input.action?parentId="+parentId;
		openEntityDialog('新增',url);
	});
	$("#js_editnode_btn").click(function(){
		var id = $("#js_node_tree").tree('getSelected').id;
		var url = ctx+"/sys/node/input.action?id="+id;
		openEntityDialog('编辑',url);
	});
	$("#js_delnode_btn").click(function(){
// 		if(!confirm("确认删除吗?")){return;}
		 $.messager.confirm('My Title', '确认删除吗?', function(r){
			if (r){
				var id = $("#js_node_tree").tree('getSelected').id;
				var url = ctx+"/sys/node/delete.action";
				var data = {id:id};
				$.post(url,data,function(result){
					if(result == '1'){
// 						alert('操作成功!');
						$('#js_node_tree').tree('reload');
					}else{
						alert('操作失败!');
					}
				},'text');
			}
		});
		
	});
});

function saveNode(){
	var $fm = $("#node_form");
	var data = $fm.serializeArray();
	var url = $fm.attr('action');
    $.post(url,data,function(result){
    	if(result == 1){
    		$('#js_node_dialog').dialog('close');
    		$('#js_node_tree').tree('reload');
        }else{
            alert("操作失败");
        }
 	},'text');
}

function openEntityDialog(title,url){
	$('#js_node_dialog').dialog({
        title: title,
        width: 400,
        height: 200,
        closed: false,
        cache: false,
        href: url,
        modal: true
    });
//		$('#dd').dialog('refresh', 'new_content.php');
}
</script>