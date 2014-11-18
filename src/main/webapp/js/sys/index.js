$(function(){
	 
	var url = ctx+"/sys/tree.action";
 	var data = {};
    $.post(url,data,function(result){
    	var db = $.parseJSON(result);
    	 $('#js_sys_tree').tree({ data: db });
 	},'text');
	 
    $('#js_sys_tree').tree({
    	onClick: function(node){
    		var title = node.text;
	    	var url = ctx+node.attributes.url;
	    	openTab(title,url);
    	}
    });
	 
 });