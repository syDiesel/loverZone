<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/left.js"></script>
	<script src="<%=basePath%>emotion/js/jquery.min.js"></script>
	
  </head>
     <frameset rows="20%,*" frameborder="no" border="0" framespacing="0" name="main">
        <frame src="<%=basePath%>web/setting/top.jsp" name="topFrame"
			scrolling="No" noresize="noresize" id="topFrame" />
		<frameset cols="30%,*" frameborder="yes" border="1" framespacing="10">
		    <frame src="<%=basePath%>web/setting/left.jsp" name="leftFrame"
			scrolling="No" noresize="noresize" id="leftFrame" />
		    <frame src="<%=basePath%>web/setting/info.jsp" name="mainFrame"
			id="mainFrame" />
		</frameset>
    </frameset>
</html>
