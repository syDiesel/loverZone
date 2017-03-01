<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
<link rel="stylesheet" href="<%=basePath%>css/fenye.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/friend.js"></script>
</head>
<style>
.AddFriendBtn {
	float: left;
	display: block;
	height: 20px;
	background: url(img/addFriend1.png) no-repeat;
	color: #fff;
	cursor: pointer;
	margin-left:26px;
	text-decoration: none;
	
}
</style>
<body>
	<div style="margin-left:30px;">
		<h1 style="font-family: 'Microsoft Yahei';font-size: 25px;">发现伴侣</h1>
	</div>
	<div style="width:90%;margin:20 0px;margin-left:50px;">
		<c:if test="${!empty listLover }">
			<c:forEach begin="0" end="9" items="${listLover }" var="item">
				<div style="float:left;margin:15px 0px;margin-left:40px;">
					<a
						href="<%=basePath%>web/per/toHomeIndex/${item.id}" target="_blank"><img
						src="<%=basePath%>${item.headImg}" width=110px
						height=110px>
					</a>
					<div
						style="text-align:center; font-family:'Microsoft Yahei';font-size: 15px;">
					 	${item.userName} <span style="margin-left:5px;">${item.sex}</span>
					    <div style="text-align: center;">
					      <a class="AddFriendBtn" href="javascript:(0)" onclick="beLoverApply('${item.id}','${page }')">申请绑定</a>
					    </div>
					</div>
				</div>
			</c:forEach>

			<!-- fenye -->
			<div class="page">
				<ul>
					<li><a
						href="<%=basePath %>web/set/searchLover.html?page=1">首页</a>
					</li>
					<c:if test="${page-1 >0}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page-1}">上一页</a>
						</li>
					</c:if>
					<c:if test="${page-3 >0}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page-3}">${page-3
								}</a></li>
					</c:if>
					<c:if test="${page-2 >0}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page-2}">${page-2
								}</a></li>
					</c:if>
					<c:if test="${page-1 >0}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page-1}">${page-1
								}</a></li>
					</c:if>
					<li><a class="nowpage">${page }</a></li>
					<c:if test="${page<lastPage}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page+1}">${page+1
								}</a></li>
					</c:if>
					<c:if test="${page+1<lastPage}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page+2}">${page+2
								}</a></li>
					</c:if>
					<c:if test="${page+2<lastPage}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page+3}">${page+3
								}</a></li>
					</c:if>
					<c:if test="${page<lastPage}">
						<li><a
							href="<%=basePath %>web/set/searchLover.html?page=${page+1}">下一页</a>
						</li>
					</c:if>
					<li><a
						href="<%=basePath %>web/set/searchLover.html?page=${lastPage}">尾页</a>
					</li>
				</ul>

			</div>
		</c:if>
		<c:if test="${empty listLover }">
			<div
				style="text-align:center;margin-top:50px;font-family: 'Microsoft YaHei';font-size: 18px;">暂时不存在符合的伴侣</div>
		</c:if>
	</div>
</body>
</html>
