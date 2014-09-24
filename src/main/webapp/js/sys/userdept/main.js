$(function(){
	//
	loadTree();
	
	$('#js_ud_tree').tree({
    	onClick: function(node){
    		var deptId = node.id;
    		$('.clear_input').val('');
    		$("#js_deptId").val(deptId);
    		refreshUsers();
    	}
    });
	
	//打开添加用户窗口
	$("#js_adduser_btn").click(function(){
		var node = $('#js_ud_tree').tree('getSelected');
		var url = ctx+"/sys/userdept/user_input.action";
		var deptId = !!node ? node.id : null;
		var data = {"deptId":deptId} ;
   	    $.post(url,data,function(result){
   	    	openDialog('添加用户',result,400,500);
    	},'html'); 
	});
	
	//打开添加部门窗口
	$("#js_adddept_btn").click(function(){
		var node = $('#js_ud_tree').tree('getSelected');
		var url = ctx+"/sys/userdept/dept_input.action";
    	var data = {"parentId":node.id};
   	    $.post(url,data,function(result){
   	    	openDialog('添加部门',result,400,500);
    	},'html'); 
	});
	
	$("#js_editdept_btn").click(function(){
//		$("#js_adddept_btn").trigger('click');
		var node = $('#js_ud_tree').tree('getSelected');
		var url = ctx+"/sys/userdept/dept_input.action";
    	var data = {"deptId":node.id};
   	    $.post(url,data,function(result){
   	    	openDialog('编辑部门',result,400,500);
    	},'html'); 
	});
	
	$("#js_deldept_btn").click(function(){
		var node = $('#js_ud_tree').tree('getSelected');
		var url = ctx+"/sys/userdept/dept_check.action?deptId="+node.id;
		var result = $.ajax({url:url,async:false}).responseText;
		if(!!result){
			alert(result); return;
		}else{
			var url = ctx+"/sys/userdept/dept_del.action";
			var data = {"deptId":node.id};
		    $.post(url,data,function(result){
		        if(result == 1){
		        	loadTree();
		        }else{
		            alert( "删除失败");
		        }
		    },'text');
		}
		
	});
	
	$('#js_user_tb').datagrid({
//		title:'My DataGrid',
//		iconCls:'icon-save',
//		width:900,
//		height:450,
//		nowrap: true,
//		autoRowHeight: false,
//		striped: true,
//		collapsible:true,
		url:ctx+'/sys/userdept/user_json.action',
//		sortName: 'code',
//		sortOrder: 'desc',
		remoteSort: false,
		idField:'id',
		frozenColumns:[[
            {field:'ck',checkbox:true},
		]],
		columns:[
			[ {field:'name',title:'姓名',width:120},
			  {field:'username',title:'登录账号',width:220},
			  {field:'deptname',title:'部门',width:150},
			  {field:'opt',title:'Operation',width:100,align:'center',
					formatter:function(value,rec){
						return "<a class='js_edit_user css_op_a' href='javascript:void(0)' onclick='editUser();'>编辑 </a> <a class='js_del_user css_op_a' onclick='delUser();' href='javascript:void(0)'>删除</a>";
					}
				}
		]],
		pagination:true,
		rownumbers:true,
	});
	
	
	$("#js_usersearch_btn").click(function(){
		refreshUsers();
	});
    
	$("#js_demo_btn").click(function(){
		var url = ctx+"/sys/comm/radio_dept.action";
		$("#js_dept_dialog").dialog({
	        title: 'My Dialog',
	        width: 400,
	        height: 200,
	        closed: false,
	        cache: false,
	        href: url,
	        modal: true
	        });
		$("#js_dept_dialog").dialog('refresh', url);
	});
	
	/*$(".js_edit_user").click(function(){
		alert("js_edit_user");
		var selected = $('#js_user_tb').datagrid('getSelected');
		console.log(selected);
		if (selected){
			alert(selected.id+":"+selected.name);
		}
	});
	
	$(".js_del_user").click(function(){
		
	});*/
});

function refreshUsers(){
	var param = $("#js_search_fm").serialize();
	var url = ctx+'/sys/userdept/user_json.action?'+param;
	$('#js_user_tb').datagrid({url:url});
}

function editUser(){
	var selected = $('#js_user_tb').datagrid('getSelected');
	if (selected){
		var url = ctx+"/sys/userdept/user_input.action";
		var data = {"userId":selected.id};
   	    $.post(url,data,function(result){
   	    	openDialog('编辑用户',result,400,500);
    	},'html'); 
	}else{
		alert("请勾选记录");
	}
}

function delUser(){
	var selected = $('#js_user_tb').datagrid('getSelected');
	if (selected){
		var url = ctx+"/sys/userdept/user_del.action";
		var data = {"userId":selected.id};
   	    $.post(url,data,function(result){
   	    	if(result == 1){
   	    		var param = $("#js_search_fm").serialize();
   	    		var url = ctx+'/sys/userdept/user_json.action?'+param;
   	    		$('#js_user_tb').datagrid({url:url});
   	        }else{
   	            alert("操作失败");
   	        }
    	},'text'); 
	}else{
		alert("请勾选记录");
	}
}

function loadTree(){
	var url = ctx+"/sys/userdept/tree.action";
 	var data = {};
    $.post(url,data,function(result){
    	var db = $.parseJSON(result);
    	 $('#js_ud_tree').tree({ data: db });
 	},'text');
}

function openDialog(title,html,width,height){
	var $dialog = $('#js_ud_dialog');
	$dialog.html(html).show();
   	$dialog.dialog({ title: title, width: width,height: height,closed: false,cache: false, modal: true });
}

function saveUser(){
	var $fm = $("#js_saveuser_fm");
	submitFm($fm);
	callbackFm = function(){
		var url = ctx+'/sys/userdept/user_json.action';
		$('#js_user_tb').datagrid({url:url});
	};
}

function saveDept(){
	var $fm = $("#js_savedept_fm");
	submitFm($fm);
	callbackFm = function(){
		loadTree();
	};
}

function submitFm($fm){
	var params = $fm.serialize();
	var url = $fm.attr('action');
 	var data = params;
    $.post(url,data,function(result){
    	if(result == 1){
    		$('#js_ud_dialog').dialog('close');
    		callbackFm();
        }else{
            alert("操作失败");
        }
 	},'text');
}

function callbackFm(){
	
}