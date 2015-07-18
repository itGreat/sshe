<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<script type="text/javascript">
$(function(){
	$("input[type='button']").click(function(){
		var $fm = $("#js_leavesave_fm");
		var data = $fm.serialize();
		var url = $fm.attr('action') ;
	    $.post(url,data,function(result){
	        if(result == "1"){
	            leaveList();
	        }else{
	            alert( "删除失败");
	        }
	    },'text'); 
	});
});

function leaveList(){
	var url = "${ctx}/oa/leave/list.action" ;
    var data = {};
    $.post(url,data,function(result){
       $("#content").html(result);
    },'html'); 
}
</script>
<form id="js_leavesave_fm" method="post" action="${ctx}/oa/leave/save.action">
<table>
	<tr>
		<td align="right">请假人：</td>
		<td><input  type="text" name="leave.uname" value="${username }"></td>
	</tr>
	<tr>
		<td align="right">原因：</td>
		<td><input  type="text" name="leave.reason" ></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" value="保存" ><input type="reset" value="重置" ></td>
	</tr>
</table>
</form>