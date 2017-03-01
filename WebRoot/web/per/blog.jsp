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

<title>说说</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
<link href="css/per/personBlog.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>emotion/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link rel="stylesheet" href="<%=basePath%>emotion/css/reset.css">
<link rel="stylesheet" href="<%=basePath%>emotion/css/editBlog.css">
<link rel="stylesheet" href="<%=basePath%>css/fenye.css">

<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script src="<%=basePath%>emotion/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>emotion/js/jquery.qqFace.js"></script>
<script type="text/javascript" src="<%=basePath%>js/blog.js"></script>
<script type="text/javascript">
$(function(){
	$('.emotion').qqFace({
		id : 'facebox', 
		assign:'saytext', 
		path:'<%=basePath%>emotion/arclist/'	//表情存放的路径
	});
	$(".sub_btn").click(function(){
		$("#saytext").val((replace_em($("#saytext").val())));
	});
});
//查看结果
function replace_em(str){
	str = str.replace(/\</g,'&lt;');
	str = str.replace(/\>/g,'&gt;');
	str = str.replace(/\n/g,'<br/>');
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="<%=basePath%>emotion/arclist/$1.gif" border="0" />');
	return str;
}
</script>
</head>
<%
	int i = 0;
%>
<body>
	<jsp:include page="${basePath }/top.jsp" flush="true"/>
	
	<!--body Star  -->
	<div class="audit">
			<c:if test="${loginUser.id eq blogUser.id }">
				<form action="web/per/newBlog.html" name="example"
					method="post" enctype="multipart/form-data">
					<input type="hidden" id="hidden_path" value="<%=basePath%>">
					<div class="comment">
						<div class="com_form">
							<textarea class="input" id="saytext" name="blogContent"></textarea>

							<p>
								<input type="file" name="file" style="width:176px;">
								&nbsp;&nbsp;&nbsp; <span class="emotion" id="emotion">表情</span>
								<input type="submit" class="sub_btn" id="sub_btn" value="发表">
							</p>
						</div>
					</div>
				</form>
			</c:if>

			<div class="blog_content">
				<c:if test="${!empty listBlog }">
					<c:forEach items="${listBlog }" var="item">
						<div class="blog_detail">
							<div class="blog_person">
								<img src="<%=basePath %>${item.userInfo.headImg}"
									width=70px height=70px style="float:left;">
								<div class="blog_info">
								<a href="">${item.userInfo.userName
									}</a></div>
								<div class="blog_time">${item.blogTime }
								   <c:if test="${loginUser.id eq blogUser.id }">
								      <span class="delete"><a href="<%=basePath%>web/per/deleteBlog/${item.id}.html?page=${page}">删除</a></span>
								      <c:if test="${!empty blogUser}">
								        <span><a href="javascript:void(0);" onclick="good(${item.id})"><img src="<%=basePath%>img/good.jpg" width=40px height=40px></a><font style="font-family: 'Microsoft Yahei';font-size: 10px;color:red;" id="allGood_${item.id}">${item.praise }&nbsp;等人觉得很赞</font></span>
								      </c:if>
								   </c:if>
								</div>
							</div>
							<div class="blog_content">
								<c:if test="${!empty item.blogPic }">
									<img src="<%=basePath%>${item.blogPic}" width=120px
										style="float:left;">
								</c:if>
								<div id="<%=i + 1%>" class="content">${item.blogContent }</div>
							</div>
						</div>

					</c:forEach>

					<!-- fenye -->
						<div class="page">
							<ul>
								<li><a
									href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=1">首页</a>
								</li>
								<c:if test="${page-1 >0}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page-1}">上一页</a>
									</li>
								</c:if>
								<c:if test="${page-3 >0}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page-3}">${page-3
											}</a>
									</li>
								</c:if>
								<c:if test="${page-2 >0}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page-2}">${page-2
											}</a>
									</li>
								</c:if>
								<c:if test="${page-1 >0}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page-1}">${page-1
											}</a>
									</li>
								</c:if>
								<li><a class="nowpage">${page }</a>
								</li>
								<c:if test="${page<lastPage}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page+1}">${page+1
											}</a>
									</li>
								</c:if>
								<c:if test="${page+1<lastPage}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page+2}">${page+2
											}</a>
									</li>
								</c:if>
								<c:if test="${page+2<lastPage}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page+3}">${page+3
											}</a>
									</li>
								</c:if>
								<c:if test="${page<lastPage}">
									<li><a
										href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${page+1}">下一页</a>
									</li>
								</c:if>
								<li><a
									href="<%=basePath %>web/per/blog/${blogUser.id }.html?page=${lastPage}">尾页</a>
								</li>
							</ul>

						</div>
				</c:if>

				<c:if test="${empty listBlog }">
					<div class="noneAudit">没有动态</div>
				</c:if>
			</div>
		</div>
	<!--body end  -->
</body>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script src="<%=basePath%>emotion/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>emotion/js/jquery.qqFace.js"></script>



</html>
