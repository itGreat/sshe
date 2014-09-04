<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<!DOCTYPE HTML >
<html>
<head>
<title>SSHE DEMO</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="scripts/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="scripts/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="scripts/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="scripts/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="scripts/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="scripts/syUtil.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;"></div>
	<div data-options="region:'south'" style="height:20px;"></div>
	<div data-options="region:'west'" style="width:200px;">
		<div class="easyui-accordion" >
			<div title="TreeMenu" data-options="iconCls:'icon-search'" style="padding:10px;">
			<ul class="easyui-tree">
				<li>
					<span>系统配置</span>
					<ul>
						<li>用户部门管理</li>
						<li>角色管理</li>
						<li>功能管理</li>
						<li>菜单管理</li>
					</ul>
				</li>
			</ul>
		</div>
		</div>
	</div>
	<!-- <div data-options="region:'east',title:'east',split:true" style="width:200px;"></div> -->
	<div  data-options="region:'center',title:'欢迎使用SSHE示例系统'" style="overflow: hidden;">
	    <div id="tt" class="easyui-tabs" >
    		<div title="Tab1" he>
   			 tab1
		    </div>
		    <!-- <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
		    tab2
		    </div>
		    <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
		    tab3
		    </div> -->
    	</div>
	</div>
 
</body>
</html>
