<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<script type="text/javascript">
 $(function(){
	 $(".js_startleave").click(function(){
		 if(!confirm("确定要提交吗?")){ return; }
		 
		var url = "${ctx}/oa/leave/startLeave.action" ;
		var id = $(this).data('id');
	    var data = {id:id};
		 $.post(url,data,function(result){
	        if(result == "1"){
	            leaveList();
	        }else{
	            alert( "操作失败");
	        }
	    },'text'); 
	 });
 })
 function leaveList(){
	var url = "${ctx}/oa/leave/list.action" ;
    var data = {};
    $.post(url,data,function(result){
       $("#content").html(result);
    },'html'); 
}
</script>
<a id="js_addleave"  href="javascript:void(0);" onclick="addLeave();" style="margin: 10px;"> 新增请假条 </a>
<hr/>
<table class="gridtable">
	<thead>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>原因</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="e" varStatus="sta">
			<tr>
				<td>${sta.count }</td>
				<td>${e.uname }</td>
				<td>${e.reason }</td>
				<td>
					<c:if test="${empty e.procInstId}">
						<a class="js_startleave" href="javascript:void(0);" data-id="${e.id }">申请请假</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
