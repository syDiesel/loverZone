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
					  <c:if test="${!empty loginUser || !empty blogUser }">
					    <li><a href="<%=basePath %>web/per/toHomeIndex/${blogUser.id}" class="scroll">首页</a></li>
					    <li><a href="<%=basePath %>web/per/blog/${blogUser.id}.html" class="scroll">说说</a></li>
					    <li><a href="<%=basePath %>web/per/diary/${blogUser.id}.html" class="scroll">日志</a></li>
					    <li><a href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id}" class="scroll">相册</a></li>
					    <li><a href="<%=basePath %>web/per/question/${blogUser.id}.html" class="scroll">留言板</a></li>
					   <c:if test="${loginUser.id eq blogUser.id }">
					    <li><a href="javascript:(0)" class="scroll"  onclick="toSetMain()">设置</a></li>
					    <li><a class="scroll"></a></li>
					    <li><a class="scroll">欢迎${loginUser.userName}</a></li>
					    <li><a href="<%=basePath %>com/logout.html" class="scroll">注销</a></li>
					    </c:if>
					  </c:if>
					  <c:if test="${empty loginUser }">
						<li><a href="<%=basePath %>web/com/login.jsp" class="scroll">登录</a></li>
						<li><a href="<%=basePath %>web/com/register.jsp" class="scroll">注册</a></li>
					  </c:if>
					</ul>
			</div>
		</div>
	</div>
	</div>

  </body>
</html>
