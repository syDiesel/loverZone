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
    
    <title>日志详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/detailDiary.css">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/button.css">
	
	
	<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>

  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

    <!--body Star  -->
   <div class="detail_body">
	   <div class="detal_head">
	      <h3>${diary.diarySubject }</h3>
	      <div class="diary_other">
	         作者：${diary.userInfo.userName }&nbsp;&nbsp;&nbsp;&nbsp;
	         时间：${diary.diaryTime }&nbsp;&nbsp;&nbsp;&nbsp;
	         阅读：${diary.diaryVisited }次
	      </div>
	   </div>
	    
	    <div class="detail_content">
	        ${diary.diaryContent }
	    </div>
	    
	    <div class="fanhui">
	        <a class="fanhui_btn"
					href="javascript:history.go(-1);">返回</a>
	    </div>
	</div>
<!--body end  -->
  </body>
</html>
