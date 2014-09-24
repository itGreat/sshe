<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/default/easyui.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/icon.css" rel="stylesheet" type="text/css"></link>

<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js" type="text/javascript"></script> --%>
<script type="text/javascript">
/* $(function(){
	 
}); */

function openRadioDept(){
	var $dialog = $('#js_radiodept_dialog');
	$("#js_display").show();
	
	var url = ctx+"/sys/userdept/tree.action";
 	var data = {};
    $.post(url,data,function(result){
    	var db = $.parseJSON(result);
    	 $('#js_radiodept_tree').tree({ data: db });
 	},'text');
    
  /*   $('#js_radiodept_tree').tree({
    	onClick: function(node){
    		radioDeptcallBack();
    	}
    }); */
    
	$dialog.dialog('open');
}

function radioDeptOk(){
	var node = $('#js_radiodept_tree').tree('getSelected');
	if(!!!node){
		alert("请选择部门!"); return;
	}else{
		radioDeptCallBack(node.id,node.text);
		$('#js_radiodept_dialog').dialog('close');
	}
}
</script>
 <div id="js_radiodept_dialog" class="easyui-dialog" title="选择部门" style="width:225px;height:360px;" data-options="iconCls:'icon-save',closed:true,modal: true">
	<div id="js_display" style="display: none;">
		<div  style="height: 280px;border: 1px solid red;">
			<ul id="js_radiodept_tree"></ul>
		</div>
		<div style="height: 30px;border: 1px solid black;text-align: center;">
			<input type="button" value="确认" onclick="radioDeptOk();"/>
		</div>
	</div>
 </div>
 