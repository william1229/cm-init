<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用中心管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

   	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/resources/css/ext-all.css"></link>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/cm-all.css" />
   	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-all-debug.js"></script>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-lang-zh_CN.js"></script>
   	<script type="text/javascript" src="<%=basePath%>js/app/login.js"></script>
  </head>
  
  <body>
    <div id="loginForm"></div>
  </body>
</html>
