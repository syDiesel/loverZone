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
    
    <title>个人空间</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/photo.css">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/button.css">

  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   	 <center>
		<div class="main" style="margin-top:20px;">
			<div class="head0">
				<a class="submitButton"
					style="float:right;margin-top:-20px;"
					href="javascript:history.go(-1);">返回</a>
				<c:if test="${blogUser.id eq loginUser.id }">
					<a href="<%=basePath%>web/per/uploadImg.html" class="submitButton"
						name="submit"
						style="margin-top:-20px; float: right;"> 上传</a>
				</c:if>
			</div>
			<c:if test="${!empty userPhoto }">
				<img src="<%=basePath %>${userPhoto.photo }" width=600px
					style="margin: 6px auto;" />
				<br>
				<span style="font-size: 15px;">${userPhoto.photoDesc }</span>
			</c:if>
			<c:if test="${blogUser.id eq loginUser.id}">
					<%-- <table style="margin-top: 30px; padding-bottom: 40px;">
						<tr>
							<td><a href="blogAlbum.do?photoId=${userAlbumPhoto.id }"
								class="submitButton">设为博客封面</a></td> --%>
				<div style="margin-top:15px; padding-bottom: 20px;">
				<a
					href="<%=basePath %>web/per/photoAlbum.do?photoId=${userPhoto.id }"
					class="submitButton">设为相册封面</a>
               </div>
			</c:if>
		</div>
	</center>
<!--body end  -->
  </body>
</html>
