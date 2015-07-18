<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<script type="text/javascript">
$(function(){
		
	$(".js_complateleave").click(function(){
		var activitiTaskId = $(this).data('actid');
		var url = "${ctx}/oa/leave/completeLeave.action";
	    var data = {activitiTaskId:activitiTaskId};
	    $.post(url,data,function(result){
    	 	if(result == "1"){
    		 	$("#js_currenttask").trigger('click');
	        }else{
	            alert( "操作失败");
	        }
	    },'text'); 
	});
	
	$(".js_view").click(function(){
		//var activitiTaskId = $(this).data('actid');
		var url = "${ctx}/oa/leave/viewNode.action";
		var data = {};
	    $.post(url,data,function(result){
	    	 $("#content").html(result);
	    },'html'); 
	});
	
});
</script>
<table>
	<thead>
		<tr>
			<th>序号</th>
			<th>类型</th>
			<th>姓名</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="e" varStatus="sta">
			<tr>
				<td>${sta.count }</td>
				<td>${e.name }</td>
				<td>${e.assignee }</td>
				<td><fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<a class="js_complateleave" href="javascript:void(0);" data-actid="${e.id }"> 处理 </a>
					<%-- <a style="margin-left: 5px;" class="js_view" href="javascript:void(0);" data-actid="${e.id }">查看流程图 </a> --%>
					<a style="margin-left: 5px;" class="js_view" href="${ctx}/oa/leave/viewNode.action?procInstId=${e.processInstanceId}" data-actid="${e.id }">当前流程图</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
