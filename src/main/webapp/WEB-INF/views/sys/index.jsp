<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/tags.jsp" %>
<!DOCTYPE HTML >
<html>
<head>
<title>SSHE DEMO</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/default/easyui.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/scripts/jquery-easyui-1.3.1/themes/icon.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/css/sys/content.css" rel="stylesheet" type="text/css"></link>

<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jqUtil.js" type="text/javascript"></script>
<script src="${ctx}/js/sys/index.js" type="text/javascript"></script>
<style type="text/css">
 
</style>
</head>
<body class="easyui-layout">
	<%-- 顶部 --%>
	<div data-options="region:'north'" style="height:60px;"></div>
	<%-- 左边 功能树 --%>
	<div data-options="region:'west'" style="width:200px;">
		<%-- 手风琴组件菜单 --%>
		<div class="easyui-accordion" >
			<div title="后台管理" data-options="iconCls:'icon-search'" style="padding:10px;">
			<%-- 树 --%>
			<ul id="js_sys_tree">
			</ul>
			</div>
		</div>
	</div>
	<%-- 中间 面板 --%>
	<div id="js_sys_tabs" class="easyui-tabs" data-options="region:'center'" style="overflow: hidden;">
	</div>
	<%-- 底部 --%>
	<div data-options="region:'south'" style="height:20px;"></div>
 <script type="text/javascript">
 var ctx = "${ctx}";
 
 function openTab(title,url){
	 var $tabs = $('#js_sys_tabs');
	if ($tabs.tabs('exists',title)){
		$tabs.tabs('select', title);
		var tab = $tabs.tabs('getSelected');
		$tabs.tabs('update', {
			tab: tab,
			options: {title: title,href: url}
		});
	} else {
    	var data = {};
   	    $.post(url,data,function(result){
   	    	$tabs.tabs('add',{
				title:title,
				content:result,
				closable:true,
				tools:[{
				iconCls:'icon-mini-refresh',
				handler:function(){
					var tab = $tabs.tabs('getSelected');
					$tabs.tabs('update', {
						tab: tab,
						options: {title: title,href: url}
					});
				}
				}]
				});
    	},'html'); 
	}
}
 </script>
</body>
</html>
