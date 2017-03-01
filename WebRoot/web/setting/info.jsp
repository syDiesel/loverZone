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
    
    <title>My JSP 'info.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet" href="<%=basePath%>css/button.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/info.js"></script>

  </head>
  <style>
   .info_lab{
	
	font-family: "Microsoft Yahei";
	font-size: 17px;
   }
</style>
  <body>
    <div style="margin-left:30px;">
		<h1 style="font-family: 'Microsoft Yahei';font-size: 25px;">${loginUser.userName }的个人资料</h1>
	</div>
	<div style="">
		<div style="margin-left:100px;width:120px;float:left;">
		    <img src="<%=basePath%>${loginUser.headImg }" width=110px height=110px>
		</div>
		<div style="margin-left:80px;margin-top:-30px;float:left;">
		    <h1>
		        TA
		    </h1>
		    <div id="lover_part">
		    <c:if test="${empty loginUser.userInfo }">
		       <span style="font-family:'Microsoft Yahei';">还没有匹配伴侣</span>
		    </c:if>
		    <c:if test="${!empty loginUser.userInfo }">
		       <span style="font-family:'Microsoft Yahei';">
		           <a href="<%=basePath %>web/per/toHomeIndex/${loginUser.userInfo.id}" class="scroll" target="_blank">
                      <img src="<%=basePath%>${loginUser.userInfo.headImg }" width=60px height=60px>
                   </a>
                   <span style="vertical-align: top;font-size: 15px;color: blue;">
                                              姓名：${loginUser.userInfo.userName }
                       <a class="submitButton" href="javascript:(0)" 
                       style="text-decoration: none;vertical-align: super;margin-left:-57px;" id="relieve">解除</a>
                   </span>
               </span>
		    </c:if>
		    </div>
		</div>
    </div>
		<div style="width:90%;margin:20 0px;margin-left:100px;float:left;">
			<table>
				<tr height="80px;">
					<td width=16% class="info_lab">性别：</td>
					<td width=34%><span class="info_lab">${loginUser.sex }</span>
					</td>

					<td width=16% class="info_lab">年龄：</td>
					<td width=34%><span class="info_lab">${loginUser.age }</span>
					</td>
				</tr>
				<tr height="80px;">
					<td class="info_lab">家庭住址：</td>
					<td><span class="info_lab">${loginUser.address }</span>
					</td>
					<td class="info_lab">联系方式：</td>
					<td><span class="info_lab">${loginUser.contact }</span>
					</td>
				</tr>
				<tr height="80px;">
					<td class="info_lab">空间名称：</td>
					<td><span class="info_lab">${loginUser.homeName }</span>
					</td>
					<td class="info_lab">空间描述：</td>
					<td><span class="info_lab">${loginUser.homeDesc }</span>
				</tr>
			</table>
	</div>
  </body>
</html>
