<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/tags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${ctx}/scripts/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery/plugin/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
var ctx = "${ctx}";
function ajaxFileUpload() {
    $.ajaxFileUpload
    (
        {
            url: ctx+"/upload.action", //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'file1', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
                console.log(data);
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
    return false;
}
</script>
</head>
<body>
	
	<input type="file" id="file1" name="file" />
	<input type="button" value="上传" onclick="ajaxFileUpload()"/>
</body>
</html>