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
	href="<%=basePath%>css/per/personPhoto.css">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/button.css">

  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   <!--main begin  -->
	 <div class="photo_main" style="margin-top:20px;">
		<div class="photo_title">
		       <h3>个人相片</h3>
		        <c:if test="${album.userInfo.id == loginUser.id }">
		           <a class="submitButton" href="<%=basePath%>web/per/uploadImg.html" style="float: right;margin-top:-23px;">上传</a>
		       </c:if>
			<button class="submitButton" onclick="location.href='javascript:history.go(-1);'"
			style="float: right;margin-top:-22px;">返回</button>
		</div>
		<br>
		<div class="photo_content">
		  <c:if test="${clickPass != '1' }">
			<c:if test="${!empty photodt }">
				<div style="width:960px;overflow:auto;">
				<c:forEach begin="0" end="3" items="${photodt }" var="item">
					<div class="photo_list">
					<a href="<%=basePath%>web/per/photo.do?id=${item.id}&blId=${blogUser.id}">
						<img src="<%=basePath %>${item.photo}" width=220px ></a>
						<div class="photo_time">
							${item.photoTime}
							<c:if test="${loginUser.id eq blogUser.id }">
						   &nbsp;&nbsp;&nbsp;<span class="photo_delete"><a
									href="<%=basePath%>web/per/deletePhoto.do?id=${item.id}&page=${page}&blId=${blogUser.id}">删除</a>
								</span>
							</c:if>
						</div>
					</div>
				</c:forEach>
               </div>
               <div style="width:960px;overflow:auto;">
				<c:forEach begin="4" end="7" items="${photodt }" var="item">
					<div class="photo_list">
					<a href="<%=basePath%>web/per/photo.do?id=${item.id}&blId=${blogUser.id}">
						<img src="<%=basePath %>${item.photo}" width=220px ></a>
						<div class="photo_time">
							${item.photoTime}
							<c:if test="${loginUser.id eq blogUser.id }">
						   &nbsp;&nbsp;&nbsp;<span class="photo_delete"><a
									href="<%=basePath%>web/per/deletePhoto.do?id=${item.id}&page=${page}&blId=${blogUser.id}">删除</a>
								</span>
							</c:if>
						</div>
					</div>
				</c:forEach>
               </div>
               <div style="width:960px;overflow:auto;">
				<c:forEach begin="8" end="11" items="${photodt }" var="item">
					<div class="photo_list">
					<a href="<%=basePath%>web/per/photo.do?id=${item.id}&blId=${blogUser.id}">
						<img src="<%=basePath %>${item.photo}" width=220px ></a>
						<div class="photo_time">
							${item.photoTime}
							<c:if test="${loginUser.id eq blogUser.id }">
						   &nbsp;&nbsp;&nbsp;<span class="photo_delete"><a
									href="<%=basePath%>web/per/deletePhoto.do?id=${item.id}&page=${page}&blId=${blogUser.id}">删除</a>
								</span>
							</c:if>
						</div>
					</div>
				</c:forEach>
               </div>

				<!-- fenye -->
				<div class="page">
					<ul>
						<li><a
							href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=1">首页</a>
						</li>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page-1}">上一页</a>
							</li>
						</c:if>
						<c:if test="${page-3 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page-3}">${page-3
									}</a>
							</li>
						</c:if>
						<c:if test="${page-2 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page-2}">${page-2
									}</a>
							</li>
						</c:if>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page-1}">${page-1
									}</a>
							</li>
						</c:if>
						<li><a class="nowpage">${page }</a>
						</li>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page+1}">${page+1
									}</a>
							</li>
						</c:if>
						<c:if test="${page+1<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page+2}">${page+2
									}</a>
							</li>
						</c:if>
						<c:if test="${page+2<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page+3}">${page+3
									}</a>
							</li>
						</c:if>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${page+1}">下一页</a>
							</li>
						</c:if>
						<li><a
							href="<%=basePath %>web/per/listAlbumPhoto.html?id=${album.id }&blId=${blogUser.id }&page=${lastPage}">尾页</a>
						</li>
					</ul>

				</div>
			</c:if>
			<c:if test="${empty photodt }">
			     <img src="<%=basePath%>images/error.jpg" style="margin-left:460px;margin-top:100px;">
				     <div class="noneAlbum">没有可看的个人相片</div>
			</c:if>
			</c:if>
			<c:if test="${clickPass == '1' }">
					<div class="clickPass" id="clickPass_">
						<form action="<%=basePath %>web/per/confirmPass.html" method="post">
							<div class="click_pass">
								<span class="margin-top:15px;">请输入密码：</span><input type="password" name="clickPass" value=""
									class="click_text"><input type="hidden" name="page"
									value="${page }"> <input type="hidden" name="blId"
									value="${blogUser.id }"><input type="hidden" name="album" value="${album.id }">
							</div>
							<div class="click_butt">
								<input type="submit" value="确定"
									class="submitButton" id="clickButt_ok">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="" value="重置" class="submitButton">
							</div>
						</form>
					</div>
				</c:if>
		</div>
	</div>
	<!--main end  -->
<!--body end  -->
  </body>
</html>
