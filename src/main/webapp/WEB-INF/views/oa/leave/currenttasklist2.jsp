<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<script type="text/javascript">
$(function(){
		
	$(".js_complateleave").click(function(){
		var activitiTaskId = $(this).data('actid');
		var taskKey = $(this).data('taskkey');
		var procInstId = $(this).data('procinstid');
		var url = "${ctx}/oa/leave/completeLeave.action";
	    var data = {activitiTaskId:activitiTaskId,taskKey:taskKey,procInstId:procInstId};
	    $.post(url,data,function(result){
    	 	if(result == "1"){
    		 	$("#js_currenttask").trigger('click');
	        }else{
	            alert( "操作失败");
	        }
	    },'text'); 
	});
	
	$(".js_view").click(function(){
		var procInstId = $(this).data('procinstid');
		var url = "${ctx}/oa/leave/viewNode.action";
		var data = {procInstId:procInstId};
	    $.post(url,data,function(result){
	    	 $("#content").html(result);
	    },'html'); 
	});
});
</script>
<table class="gridtable">
	<thead>
		<tr>
			<th>序号</th>
			<th>类型</th>
			<th>姓名</th>
			<th>原因</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="e" varStatus="sta">
			<tr>
				<td>${sta.count }</td>
				<td>${e.taskKey }</td>
				<td>${e.uname }</td>
				<td>${e.reason}</td>
				<td>
					<a class="js_complateleave" href="javascript:void(0);" data-actid="${e.activitiTaskId }" data-taskkey="${e.taskKey }" data-procinstid="${e.procInstId }">处理 </a>
					<a style="margin-left: 5px;" class="js_view" href="javascript:void(0);"  data-procInstId="${e.procInstId }">当前流程图</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
