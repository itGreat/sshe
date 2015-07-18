<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti-web</title>
<link href="${ctx}/css/oa/layout.css" rel="stylesheet" type="text/css"><link>
<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.8.3.min.js"></script>
<style type="text/css">
table.gridtable {
	width: 99%;
}
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:12px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
a{
	text-decoration: none;
}
a:HOVER {
	/* text-decoration: blink; */
	color: red;
}
#sidebar li {
	height: 22px;
}
#sidebar li a{
	text-decoration: none;
}
</style>
</head>
<body>
	
<div id="container">
  <div id="header"><a href="${ctx}/oa/login.action" style="margin: 10px;">注销</a>  Welcome! <a href="javascript:void(0);">${username }</a></div>
  <div id="mainContent">
    <div id="sidebar">
		<ul>
			<li><a id="js_leave" href="javascript:void(0);">请假管理</a></li>
			<li><a id="js_currenttask" href="javascript:void(0);">当前任务</a></li>
			<li><a id="js_flow" href="javascript:void(0);">流程管理</a></li>
		</ul>
	</div>
    <div id="content">
    
    </div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	
	$("#js_leave").click(function(){
		leaveList();
	});
	
	$("#js_currenttask").click(function(){
		var url = "${ctx}/oa/leave/currenttasklist2.action";
		 var data = {};
	    $.post(url,data,function(result){
	       $("#content").html(result);
	    },'html'); 
	}).trigger('click');
	
	
	$("#js_flow").click(function(){
		var url = "${ctx}/oa/leave/flow.action";
		 var data = {};
	    $.post(url,data,function(result){
	       $("#content").html(result);
	    },'html'); 
	});
	
});

function leaveList(){
	var url = "${ctx}/oa/leave/list.action" ;
	var username = "${username}";
    var data = {username:username};
    $.post(url,data,function(result){
       $("#content").html(result);
    },'html'); 
}

function addLeave(){
	var url = "${ctx}/oa/leave/add.action";
    var data = {};
    $.post(url,data,function(result){
   	  $("#content").html(result);
    },'html'); 
}
</script>
</body>
</html>