<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link  rel="stylesheet" type="text/css" href="<%=basePath%>css/setting/left.css" >
	
	

  </head>
  
  <body style="float:right;">
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
  <script src="<%=basePath%>emotion/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/left.js"></script>
              <div class="pingtaifuwu_body clearfix">
					<div class="pingtaifuwu_body_1 clearfix">
			            <a href="javascript:(0)" target="mainFrame" onclick="perInfo()">个人资料</a>
			            <a href="<%=basePath %>web/setting/updateInfo.jsp" target="mainFrame">资料编辑</a>
						<a href="<%=basePath %>web/setting/editHead.jsp" target="mainFrame">头像编辑</a>
						<a href="<%=basePath %>web/set/searchLover.html" target="mainFrame">发现伴侣</a>
						<a href="<%=basePath %>web/set/applyCheck.html" target="mainFrame">申请处理</a>
						<a href="<%=basePath %>web/set/secuQues/${loginUser.id}.html" target="mainFrame">密保改密</a>
					</div>
		      </div>
  </body>
</html>
