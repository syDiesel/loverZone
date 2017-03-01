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
    
    <title>result</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link href="<%=basePath%>css/button.css" rel="stylesheet"
	type="text/css" />
	
	<style>
.content {
	width: 900px;
	background: #fff;
	height: 500px;
	margin: 10px auto;
	text-align: center;
}

.content h3 {
	font-size: 24px;
	color: red;
}
</style>

  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   <div class="mainbox">

		<div class="content">
			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
			<br />
			<br />
			<br />
			<h3>相片上传成功</h3>
			<a href="<%=basePath%>web/per/uploadImg.html">
				<button style="margin-top:20px;" class="submitButton"
					onclick="javascrtpt:window.location.href='<%=basePath%>web/per/uploadImg.html'">继续上传</button>
			</a> <a href="<%=basePath%>web/per/listAlbum.do?id=${loginUser.id}">
				<button style="margin-top:20px;" class="submitButton"
					onclick="javascrtpt:window.location.href='<%=basePath%>web/per/listAlbum.do?id=${loginUser.id}'">查看相册</button>
			</a>
		</div>
	</div>
<!--body end  -->
  </body>
</html>
