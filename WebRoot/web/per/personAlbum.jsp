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
	href="<%=basePath%>css/per/personAlbum.css">
    <link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/album_creat.css">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/button.css">
	
	<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
	function edit(value) {

		$("#" + value).click(function() {

			var height = $(document.body).height();
			$("#main_creat").css("width", "100%");
			$("#main_creat").css("height", height);
			$("#main_creat").css("position", "absolute");
			$("#main_creat").css("background", "black");
			$("#main_creat").css("opacity", "0");
			$("#main_creat").css("z-index", "10");
			$("#main_creat").css("display", "block");

			$("#creat_div" + value).css("position", "absolute");
			$("#creat_div" + value).css("z-index", "20");
			$("#creat_div" + value).css("display", "block");

		});

		$("#main_creat").click(function() {

			$("#main_creat").css("display", "none");
			$("#creat_div" + value).css("display", "none");
		});

		$("#album_reset" + value).click(function() {
			$("#main_creat").css("display", "none");
			$("#creat_div" + value).css("display", "none");
		});

		$("#pwd_yes" + value).click(function() {
			$("#input_pwd" + value).css("display", "block");
			$("#make_pwd" + value).css("display", "none");
		});

		$("#but_no" + value).click(function() {
			$("#inputPwd" + value).val("");
			$("#input_pwd" + value).css("display", "none");
			$("#make_pwd" + value).css("display", "block");
			$('input:radio[name=album_isPwd]')[0].checked = true;
		});

	}
   </script>

  </head>
  
  <body>
    
   <jsp:include page="${basePath }/top.jsp" flush="true"/>
   <div id="main_creat" class="main_creat"></div>
  <!--body Star  -->
  <!--main begin  -->
	<div class="album_main">
		<div class="album_title">
			<h3>个人相册</h3>
			<c:if test="${loginUser.id eq blogUser.id }">
				<a href="<%=basePath%>web/per/uploadImg.html"
					class="submitButton" style="float: right;margin-top:-22px;"> 上传</a>
			</c:if>
		</div>
		<br>
		<div class="album_content">
			<div class="album_result">
				<c:if test="${clickPass != '1' }">
					<c:if test="${!empty albumdt }">
						<div style="width:960px;overflow:auto;">
							<c:forEach begin="0" end="3" items="${albumdt }" var="item">
								<div class="album_img">
									<a
										href="<%=basePath %>web/per/permission.do?id=${item.id}&blId=${blogUser.id}">
										<img src="<%=basePath %>${item.userPhoto.photo}" width=220px>
									</a>
									<div class="album_name">
										${item.albumName}&nbsp;&nbsp;&nbsp;
										<c:if test="${loginUser.id eq blogUser.id }">
											<a
												href="<%=basePath%>web/per/deleteAlbum.do?id=${item.id}&page=${page }&blId=${blogUser.id}">删除</a>
											<a href="javascript:void(0)" class="editAlbum"
												id="${item.id }" onmouseover="edit('${item.id }')">编辑</a>
										</c:if>
									</div>
								</div>
								<!---------------------------creat_begin  ------------------------->
								<div class="creat_div" id="creat_div${item.id }">
									<div class="creat_div_head">
										<h1>编辑相册</h1>
									</div>
									<div class="creat_div_main">
										<form action="<%=basePath%>web/per/editAlbum.html"
											method="post">
											<input type="hidden" value="${item.id }" id="creat_href"
												name="hidden_album">
												<input type="hidden" value="${page}" name="page">
											<div class="album_creat_name">
												<h>相册名：</h>
												<span><input type="text" name="album_creatName"
													value="${item.albumName}"> </span>
											</div>
											<div class="album_creat_pwd">
												<h>设置密码：</h>
												<c:if test="${item.albumPsw == null }">
													<span id="make_pwd${item.id }"> <input type="radio"
														name="album_isPwd" value="0" id="pwd_no${item.id }" checked="checked">否
														<input type="radio" name="album_isPwd" value="1"
														id="pwd_yes${item.id }">是 </span>
													<span id="input_pwd${item.id }" class="input_pwd"> <input
														type="text" name="album_pwd" class="inputPwd"
														id="inputPwd"> <input type="button" name="button"
														id="but_no${item.id }" value="取消"> </span>
												</c:if>
												<c:if test="${item.albumPsw != null }">
													<input type="text" name="oldPsw" class="oldPsw" value=""
														placeholder="输入原始密码">
													<input type="text" name="newPsw" class="newPsw" value=""
														placeholder="输入新密码(不输即默认不设)">
												</c:if>
											</div>
											<div class="album_creat_desc">
												<h>相册描述：</h>
												<span><textarea name="album_desc" cols="60" rows="10"
														class="album_desc">${item.albumDesc}
                            </textarea> </span>
											</div>
											<div class="album_creat_but">
												<input type="submit" name="submit" value="确定">
												&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" name="reset"
													id="album_reset${item.id }" value="取消">
											</div>
										</form>
									</div>
								</div>
								<!---------------------------creat_end  ------------------------->
							</c:forEach>
						</div>
						<div style="width:960px;overflow:auto;">
							<c:forEach begin="4" end="7" items="${albumdt }" var="item">
								<div class="album_img">
									<a
										href="<%=basePath %>web/per/permission.do?id=${item.id}&blId=${blogUser.id}">
										<img src="<%=basePath %>${item.userPhoto.photo}" width=220px>
									</a>
									<div class="album_name">
										${item.albumName}&nbsp;&nbsp;&nbsp;
										<c:if test="${loginUser.id eq blogUser.id }">
											<a
												href="<%=basePath%>web/per/deleteAlbum.do?id=${item.id}&page=${page }&blId=${blogUser.id}">删除</a>
											<a href="javascript:void(0)" class="editAlbum"
												id="${item.id }">编辑</a>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</div>
						<div style="width:960px;overflow:auto;">
							<c:forEach begin="8" end="11" items="${albumdt }" var="item">
								<div class="album_img">
									<a
										href="<%=basePath %>web/per/permission.do?id=${item.id}&blId=${blogUser.id}">
										<img src="<%=basePath %>${item.userPhoto.photo}" width=220px>
									</a>
									<div class="album_name">
										${item.albumName}&nbsp;&nbsp;&nbsp;
										<c:if test="${loginUser.id eq blogUser.id }">
											<a
												href="<%=basePath%>web/per/deleteAlbum.do?id=${item.id}&page=${page }&blId=${blogUser.id}">删除</a>
											<a href="javascript:void(0)" class="editAlbum"
												id="${item.id }">编辑</a>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${empty albumdt }">
						<div class="noneAlbum">暂时没有相册</div>
					</c:if>
				</c:if>
			</div>
			<!-- fenye -->
			<c:if test="${!empty albumdt }">
				<div class="page">
					<ul>
						<li><a
							href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=1">首页</a>
						</li>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page-1}">上一页</a>
							</li>
						</c:if>
						<c:if test="${page-3 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page-3}">${page-3
									}</a>
							</li>
						</c:if>
						<c:if test="${page-2 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page-2}">${page-2
									}</a>
							</li>
						</c:if>
						<c:if test="${page-1 >0}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page-1}">${page-1
									}</a>
							</li>
						</c:if>
						<li><a class="nowpage">${page }</a>
						</li>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page+1}">${page+1
									}</a>
							</li>
						</c:if>
						<c:if test="${page+1<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page+2}">${page+2
									}</a>
							</li>
						</c:if>
						<c:if test="${page+2<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page+3}">${page+3
									}</a>
							</li>
						</c:if>
						<c:if test="${page<lastPage}">
							<li><a
								href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${page+1}">下一页</a>
							</li>
						</c:if>
						<li><a
							href="<%=basePath %>web/per/listAlbum.do?id=${blogUser.id }&page=${lastPage}">尾页</a>
						</li>
					</ul>

				</div>
			</c:if>
		</div>
	</div>
	<!--main end  -->
<!--body end  -->
  </body>
</html>
