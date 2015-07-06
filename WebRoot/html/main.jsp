<%@page import="cn.cas.iue.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中心管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/resources/css/ext-all.css"></link>
   <%-- 	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/examples/ux/css/ItemSelector.css" /> --%>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/cm-all.css" />
<%-- 	<script type="text/javascript">
   		var userId = <%=session.getAttribute("userId")%>; //获取登陆系统用户ID
   		var isSMgt = <%=session.getAttribute("isSMgt")%>; //获取登陆后传递的参数
   		var isCMgt = <%=session.getAttribute("isCMgt")%>; //获取登陆后传递的参数
   		var isSSMgt = <%=session.getAttribute("isSSMgt")%>; //获取登陆后传递的参数
   		var username = '<%=session.getAttribute("name")%>';
   	</script> --%>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-all-debug.js"></script>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/TabCloseMenu.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/grid/SearchingFeature.js" ></script>  
<%--    	<script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/layout/component/form/MultiSelect.js" ></script>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/layout/component/form/ItemSelector.js" ></script>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/form/MultiSelect.js" ></script>
   	<script type="text/javascript" src="<%=basePath%>js/extjs/examples/ux/form/ItemSelector.js" ></script> --%>
   	<script type="text/javascript" src="<%=basePath%>js/app/main.js"></script>
  </head>
  
  <body>
    <div id="mainForm"></div>
  </body>
</html>
