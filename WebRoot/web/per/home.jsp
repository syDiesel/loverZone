<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	import="java.text.*,java.util.*,com.userZone.entity.*"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人网站空间</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
    <link rel="stylesheet" href="<%=basePath%>css/home.css">
    <link href="css/fenye.css" rel="stylesheet" type="text/css" />
    
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/per/home.css" rel="stylesheet" type="text/css" />
<link href="css/per/personDiary.css" rel="stylesheet"
	type="text/css" />
<link href="css/per/personBlog.css" rel="stylesheet"
	type="text/css" />
	
	<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/blog.js"></script>
  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>
    <div class="main">
    <!--------------------------------left-------------------------->
    <div class="left">
        <img src="<%=basePath%>${blogUser.headImg}" width="180px" style="margin:0 auto;">
        <div class="homeName">空间名：${blogUser.homeName }</div>
        <div>姓名：${blogUser.userName }</div>
        <div>性别：${blogUser.sex } &nbsp; <span>年龄：${blogUser.age }</span></div>
        <div>邮箱：${blogUser.email }</div>
        <div>地址：${blogUser.address }</div>
    </div>
    <%
		int i = 0;
	%>
    <!--------------------------------main-------------------------->
    <div class="main_center">
        <c:if test="${!empty listAudit }">
				<c:forEach items="${listAudit }" var="item">
					<!-- --------------------blog---------------------------------------------- -->
					<c:if test="${item.type == 'blog' }">
						<div class="blog_detail">
							<div class="blog_person">
								<img src="<%=basePath %>${item.userInfo.headImg}" width=70px
									height=70px style="float:left;">
								<div class="blog_info">
									<a href="">${item.userInfo.userName }</a>
								</div>
								<div class="blog_time">
									${item.time }
									<c:if test="${loginUser.id eq item.userInfo.id }">
										<span class="delete"><a
											href="<%=basePath%>web/per/deleteAudit/${item.id}?type=blog&page=${page}">删除</a>
										    <c:if test="${!empty blogUser}">
								        <span><a href="javascript:void(0);" onclick="good(${item.id})"><img src="<%=basePath%>img/good.jpg" width=40px height=40px></a><font style="font-family: 'Microsoft Yahei';font-size: 10px;color:red;" id="allGood_${item.id}">${item.visit }&nbsp;等人觉得很赞</font></span>
								      </c:if>
										</span>
									</c:if>
								</div>
							</div>
							<div class="blog_content">
								<c:if test="${!empty item.blogPic }">
									<img src="<%=basePath%>${item.blogPic}" width=120px
										style="float:left;">
								</c:if>
								<div id="<%=i + 1%>" class="content">${item.content }</div>
							</div>
						</div>
					</c:if>

					<!-- --------------------diary---------------------------------------------- -->
					<c:if test="${item.type == 'diary' }">
						<div class="diary_detail">
							<div class="blog_person">
								<img src="<%=basePath %>${item.userInfo.headImg}" width=70px
									height=70px style="float:left;">
								<div class="blog_info">
									<a href="">${item.userInfo.userName }</a>
								</div>
								<div class="blog_time">
									${item.time } <span class="diary_visit">阅读${item.visit
										}次</span>
									<c:if test="${loginUser.id eq item.userInfo.id }">
										<span class="delete"><a
											href="<%=basePath%>web/per/deleteAudit/${item.id}?type=diary&page=${page}">删除</a>
										</span>
									</c:if>
								</div>
							</div>

							<div class="diary_content">
								<div class="diary_sub">
									<a href="<%=basePath%>web/per/detailDiary/${item.id}.html">标题：${item.subject
										}</a>
								</div>
								<div class="diary_desc">${item.content }</div>
							</div>
						</div>
					</c:if>
				</c:forEach>

				<!-- fenye -->
				<div class="page" style="margin-bottom:20px;">
					<ul>
						<li><a
							href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=1">首页</a>
						</li>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page-1}">上一页</a>
							</li>
						</c:if>
						<c:if test="${page-3 >0}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page-3}">${page-3
									}</a>
							</li>
						</c:if>
						<c:if test="${page-2 >0}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page-2}">${page-2
									}</a>
							</li>
						</c:if>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page-1}">${page-1
									}</a>
							</li>
						</c:if>
						<li><a class="nowpage">${page }</a>
						</li>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page+1}">${page+1
									}</a>
							</li>
						</c:if>
						<c:if test="${page+1<lastPage}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page+2}">${page+2
									}</a>
							</li>
						</c:if>
						<c:if test="${page+2<lastPage}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page+3}">${page+3
									}</a>
							</li>
						</c:if>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${page+1}">下一页</a>
							</li>
						</c:if>
						<li><a
							href="<%=basePath %>web/per/toHomeIndex/${loginUser.id }?page=${lastPage}">尾页</a>
						</li>
					</ul>

				</div>
			</c:if>
			<c:if test="${empty listAudit }">
				<div class="noneAudit">没有动态</div>
			</c:if>
    </div>
    </div>
  </body>
</html>
