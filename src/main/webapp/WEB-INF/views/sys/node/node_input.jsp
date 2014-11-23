<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/include.jsp" %>
<form id="node_form" method="post" action="${ctx}/sys/node/save.action">
	<input type="hidden" name="node.id" value="${model.id }"/>
	<table id="add_node_tb" class="css_node_tb css_fm_tb">
	<tr class="table_row3">
		<td class="css_td_label" align="right">名称：</td>
		<td class="css_td_el" align="left"><input type="text" id="name" name="node.name" maxlength="50" value="${model.name }" style="width:80%"></td>
	</tr>
	<tr class="table_row3">
		<td class="css_td_label" align="right">序号：</td>
		<td class="css_td_el" align="left">
		<input type="text" id="seq" style="width:80%" name="node.seq" value="${model.seq }" maxlength="25"/><font color="#FF0000">*</font>
		</td>
	</tr>
	<tr class="table_row3">
       	<td class="css_td_label" align="right">对应功能：</td>
        <td class="css_td_el" align="left">
        	<input id="node_entitycombobox" name="node.entity.id" class="easyui-combobox" data-options="
				valueField: 'id',
				textField: 'text',
				url: '${ctx}/sys/entity/getEntitys.action',
				onLoadSuccess: function(data){
					$('#node_entitycombobox').combobox('setValue','${model.entity.id }');
				},
				onSelect: function(rec){
					var url = 'get_data2.php?id='+rec.id;
					$('#cc2').combobox('reload', url);
				}">
        	<!-- <input type="text" name="node.entity.id" value="1"> -->
        </td>
      </tr>
	<tr class="table_row3">
		<td class="css_td_label" align="right">所属上级：</td>
		<td class="css_td_el" align="left">
			<input id="node_parentcombobox" name="node.parent.id" class="easyui-combobox" data-options="
				valueField: 'id',
				textField: 'text',
				url: '${ctx}/sys/node/getNodes.action',
				onLoadSuccess: function(data){
					$('#node_parentcombobox').combobox('setValue','${model.parent.id }');
				},
				onSelect: function(rec){
					var url = 'get_data2.php?id='+rec.id;
					$('#cc2').combobox('reload', url);
				}">
			<%-- <input type="text" id="parentId" name="1" value="${ }" maxlength="80" style="width:80%"> --%>
		</td>
	</tr>
	<tr class="table_row3">
		<td class="css_td_label" height="25" align="center" colspan="2">
		<input value="保存" type="button" onclick="saveNode();" /></td>
	</tr>
	</table>
</form>