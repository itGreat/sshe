<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp"%>
<form id="js_enity_fm" method="post" action="${ctx}/sys/entity/save.action">
	<input type="hidden" name="entity.id" value="${model.id }"/>
	<div class="fitem">
		<label>名称:</label> 
		<input name="entity.name" value="${model.name }" class="easyui-textbox" required="true">
	</div>
	<div class="fitem">
		<label>类型:</label> 
		<select name="entity.type">
			<option value="menu" ${model.type eq "menu" ? "selected='selected'" : ""}>menu</option>
			<option value="other" ${model.type eq "other" ? "selected='selected'" : ""}>other</option>
		</select>
	</div>
	<div class="fitem">
		<label>链接:</label> <input name="entity.value" value="${model.value }" class="easyui-textbox">
	</div>
	<div class="fitem">
		<label>备注:</label> <input name="entity.remark" value="${model.remark }" class="easyui-textbox" >
	</div>
	<div class="fitem">
		<input value="保存" type="button" onclick="saveEntity();" /><input value="清空" type="button" onclick="$('#js_enity_fm').form('clear');" />
	</div>
</form>
