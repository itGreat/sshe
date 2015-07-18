<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${ctx}/scripts/jquery/plugins/jquery.form.js"></script>
<script type="text/javascript">
 $(function(){
	 $("#js_upload_btn").click(function(){
		 
		var $form =  $("#js_file_form");
		var url = $form.attr('action');
		var options  = { url:url, type:'post',
	        success:function(result) {
	    		if(result= "1"){
	    			$("#js_flow").trigger('click');
	    		}else{
	    			alert("操作失败!");
	    		}
	        }  
	    };
	    //使用jquery.form.js 插件提交form数据
		$form.ajaxSubmit(options);
	 });
 });
</script>
<form id="js_file_form" action="${ctx}/oa/leave/deploy.action" method="post" enctype="multipart/form-data">
	流程名称:
	<input type="text" value="请假流程" name="procName">
	流程规则：
	<input type="file" name="file"/>
	<input id="js_upload_btn" type="button" value="提交">
</form>
<hr/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="gridtable">
       <tr class="th1">
         <th width="20%" >序号</th>
         <th width="20%" >名称</th>
         <th width="10%">标识</th>
         <th width="10%">版本</th>
         <th width="30%">操作</th>
       </tr>
       <c:forEach items="${list }" var="e">
       		<tr class="curOne_r">
       			<td align="center">${e.id }</td>
       			<td align="center">${e.name }</td>
       			<td align="center">${e.key }</td>
       			<td align="center">${e.version }</td>
       			<td align="center" class="trainFunction">
	  				<a href="detail.action?type=xml&id=${e.id }&version=${e.version }"  class="detail">流程定义</a>&nbsp;
	  				<a href="detail.action?type=img&id=${e.id }&version=${e.version }" class="edit">&nbsp;流程图</a>&nbsp;
					<a href="#" onClick="return toDelete('')" onFocus="this.blur()" class="delete">&nbsp;删除</a>
				</td>
       		</tr>
      </c:forEach>
      <c:if test="${empty list }">
	  	<tr>
	    	<td class="list_page" colspan="6">没有数据！</td>
	    </tr>
      </c:if>
</table>