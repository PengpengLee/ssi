<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/modules/common/include/common_consts.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>出错了！</title>
<%@ include file="/modules/common/include/common_head.jsp" %>
</head>

<body>
<div><h1>对不起系统发生内部错误.</h1></div>
<div><a href="${ctx}/user/login.action">重新登录</a>&nbsp;<a href="${ctx}/user/index.action">返回首页</a></div>
<hr/>
<h3 style="color:red;">错误信息：</h3>
<hr/>
<p>
	<s:property value="exception.message"/>
</p>
<hr/>

<h6 onclick="javascript:$('#exceptionStack').toggle();">管理员链接</h6>
<div id="exceptionStack" style="display:none;">
	<hr/>
	<p>
	<s:property value="exceptionStack"/>
	</p>
	<hr/>
</div>
</body>
</html>
