<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/default/easyui.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/icon.css" rel="stylesheet" type="text/css"></link>

<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$(".js_usersearch_btn").click(function(){
		var url = ctx+"/sys/userdept/tree.action";
	 	var data = {};
	    $.post(url,data,function(result){
	    	var db = $.parseJSON(result);
	    	 $('#js_radiodept_tree').tree({ data: db });
	 	},'text');
	 });
});
</script>
 <div id="js_radiodept_dialog">
 	<ul id="js_radiodept_tree"></ul>
 	
 	<input type="button" value="确定"/>
 </div>
 