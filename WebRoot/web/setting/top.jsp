<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/com/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=basePath%>css/com/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/main.js"></script>
  </head>
  
  <body style="width:1349px;">
    <!-- header -->
	<div class="header">
	<div class="container" style="width:1170px;">
		<div class="header-part">
			<div class="top-nav">
				<span class="menu"><img src="home/images/menu.png" alt="" /></span>
					<ul class="nav1">
					  <c:if test="${empty loginUser }">
						<li><a href="<%=basePath %>web/com/login.jsp" class="scroll" target="_blank">登录</a></li>
						<li><a href="<%=basePath %>web/com/register.jsp" class="scroll" target="_blank">注册</a></li>
					  </c:if>
					  <c:if test="${!empty loginUser }">
					    <li><a href="<%=basePath %>web/per/toHomeIndex/${loginUser.id}" class="scroll" target="_blank">首页</a></li>
					    <li><a href="<%=basePath %>web/per/blog/${loginUser.id}.html" class="scroll" target="_blank">说说</a></li>
					    <li><a href="<%=basePath %>web/per/diary/${loginUser.id}.html" class="scroll" target="_blank">日志</a></li>
					    <li><a href="<%=basePath %>web/per/listAlbum.do?id=${loginUser.id}" class="scroll" target="_blank">相册</a></li>
					    <li><a href="<%=basePath %>web/per/question/${loginUser.id}.html" class="scroll" target="_blank">留言板</a></li>
					    <li><a href="javascript:(0)" class="scroll"  onclick="toSetMain()">设置</a></li>
					    <li><a class="scroll"></a></li>
					    <li><a class="scroll">欢迎${loginUser.userName}</a></li>
					    <li><a href="<%=basePath %>com/logout.html" class="scroll" target="_blank">注销</a></li>
					  </c:if>
					</ul>
			</div>
		</div>
	</div>
	</div>

  </body>
</html>
