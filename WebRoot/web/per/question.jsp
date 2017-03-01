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
    
    <title>留言板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/personQ.css">
    <link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/fenye.css">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/button.css">


    <script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
    <script charset="utf-8" src="<%=basePath%>kindeditor/kindeditor.js"></script>
    <script charset="utf-8" src="<%=basePath%>kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8"
	src="<%=basePath%>kindeditor/plugins/code/prettify.js"></script>
	
	
	
	<script>
     var editor;
	 KindEditor.ready(function(K) {
		editor = K.create('textarea[name="QContent"]', {
			cssPath : '<%=basePath%>kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '<%=basePath%>kindeditor/jsp/file_manager_json.jsp',
			//简单模式
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			image : false,
			items : [
						'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'link'],

			//图片空间管理
			//allowFileManager : true,
			autoHeightMode : false,
           });
   
	  
		prettyPrint();

	}); 

	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="replyContent"]', {
			cssPath : '<%=basePath%>kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '<%=basePath%>kindeditor/jsp/file_manager_json.jsp',
									//简单模式
									resizeType : 1,
									allowPreviewEmoticons : false,
									allowImageUpload : false,
									image : false,
									items : [ 'source', 'fontname', 'fontsize',
											'|', 'forecolor', 'hilitecolor',
											'bold', 'italic', 'underline',
											'removeformat', '|', 'justifyleft',
											'justifycenter', 'justifyright',
											'insertorderedlist',
											'insertunorderedlist', '|',
											'emoticons', 'link' ],

									//图片空间管理
									//allowFileManager : true,
									autoHeightMode : false,
								});

				prettyPrint();

			});

	function fanhui(value) {
		$("#reply1" + value).click(function() {

			$("#reply_area1" + value).css("display", "block");

		});

		$("#reset_btn" + value).click(function() {
			$("#reply_area1" + value).css("display", "none");

		});
	}
	
	function fanhui2(value1,value2){
	   var value = value1+value2;
	  $("#reply1" + value).click(function() {

			$("#reply_area1" + value).css("display", "block");

		});

		$("#reset_btn" + value).click(function() {
			$("#reply_area1" + value).css("display", "none");

		});
	   
	}
</script>
  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   <div class="personQ_body">
		<c:if test="${(loginUser.id != blogUser.id) && loginUser!=null }">
			<form action="web/per/newQuestion.html" name="example"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="blogUserId" value="${blogUser.id }">
				<div class="question_form">
					<div class="title">
						标题：<input type="text" name="QSubject" class="QSubject">
					</div>
					<textarea class="input" id="QContent" name="QContent"
						style="width:895px;"></textarea>
					<p style="margin-top:5px;">
						<input type="submit" style="float: right;" class="submitButton" id="sub_btn" value="留言">
					</p>
				</div>
			</form>
		</c:if>

		<div class="question_list">
			<input type="hidden" id="listQuestion" value="${listQuestion.size()}">
			<c:if test="${!empty listQuestion }">
				<c:forEach end="${listQuestion.size()}" begin="1" step="1" var="i">
					<div class="question_result">
						<div class="blog_person">
							<img
								src="<%=basePath %>${listQuestion.get(i-1).userInfoByUseId.headImg}"
								width=70px height=70px style="float:left;">
							<div class="blog_info">
								<a href="<%=basePath %>web/per/toHomeIndex/${listQuestion.get(i-1).userInfoByUseId.id}">${listQuestion.get(i-1).userInfoByUseId.userName }</a>
							</div>
							<div class="blog_time">${listQuestion.get(i-1).questionTime
								}</div>
						</div>
						<div class="question_content">
							<div class="question_sub">标题：${listQuestion.get(i-1).questionSubject
								}</div>
							<div class="question_desc">${listQuestion.get(i-1).questionContent
								}</div>
							<c:if test="${!empty loginUser }">
								<div class="huifu">
									<p id="reply1${i}" class="submitButton" onmouseover="fanhui('${i}')" style="cursor: pointer;float:right;">回复</p>
								</div>
							</c:if>
							<div class="reply_area" id="reply_area1${i}">
								<form action="web/per/newReply/${blogUser.id }.html"
									name="" method="post" enctype="multipart/form-data">
									<input type="hidden" name="replyedId" value="${listQuestion.get(i-1).userInfoByUseId.id }">
									<input type="hidden" name="questionId"
										value="${listQuestion.get(i-1).id }">
									<textarea class="input" id="replyContent" name="replyContent"
										style="width:895px;"></textarea>
									<p style="margin:5px;margin-bottom:20px;">
										<input type="reset" class="reset_btn" id="reset_btn${i}"
											value="取消"> <input type="submit" class="sub_btn"
											id="sub_btn" value="回复">
									</p>
								</form>
							</div>

							<div class="reply_result">
								<c:forEach end="${listAllReply.get(i-1).size()}" begin="1"
									var="j" step="1">
									<a href="">${listAllReply.get(i-1).get(j-1).userInfoByReplyid.userName
										}</a>&nbsp;回复
						        <a href="">${listAllReply.get(i-1).get(j-1).userInfoByUserId.userName
										}</a>&nbsp;:
						        &nbsp;${listAllReply.get(i-1).get(j-1).replyContent }
						        <div class="reply_time">
										${listAllReply.get(i-1).get(j-1).replyTime } 
										<span class="reply2" id="reply1${i}${j}"  onmouseover="fanhui2('${i}','${j}')">回复</span>
										<div class="reply_area" id="reply_area1${i}${j}">
											<form action="web/per/newReply/${blogUser.id }.html"
												name="" method="post" enctype="multipart/form-data">
												<input type="hidden" name="replyedId"
													value="${listAllReply.get(i-1).get(j-1).userInfoByReplyid.id }"> <input type="hidden"
													name="questionId" value="${listQuestion.get(i-1).id }">
												<textarea class="input" id="replyContent"
													name="replyContent" style="width:800px;"></textarea>
												<p style="margin-right:73px;">
													<input type="reset" class="reset_btn" id="reset_btn${i}${j}"
														value="取消"> <input type="submit" class="sub_btn"
														id="sub_btn" value="回复">
												</p>
											</form>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:forEach>
				
				<!-- fenye -->
			<c:if test="${!empty listQuestion }">
					<div class="page">
						<ul>
							<li><a
								href="<%=basePath %>web/per/question/${blogUser.id }.html?page=1">首页</a>
							</li>
							<c:if test="${page-1 >0}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page-1}">上一页</a>
								</li>
							</c:if>
							<c:if test="${page-3 >0}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page-3}">${page-3
										}</a></li>
							</c:if>
							<c:if test="${page-2 >0}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page-2}">${page-2
										}</a></li>
							</c:if>
							<c:if test="${page-1 >0}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page-1}">${page-1
										}</a></li>
							</c:if>
							<li><a class="nowpage">${page }</a></li>
							<c:if test="${page<lastPage}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page+1}">${page+1
										}</a></li>
							</c:if>
							<c:if test="${page+1<lastPage}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page+2}">${page+2
										}</a></li>
							</c:if>
							<c:if test="${page+2<lastPage}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page+3}">${page+3
										}</a></li>
							</c:if>
							<c:if test="${page<lastPage}">
								<li><a
									href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${page+1}">下一页</a>
								</li>
							</c:if>
							<li><a
								href="<%=basePath %>web/per/question/${blogUser.id }.html?page=${lastPage}">尾页</a>
							</li>
						</ul>

					</div>
				</c:if>
				
			</c:if>

			<c:if test="${empty listQuestion }">
				<div class="none">没有留言</div>
			</c:if>
		</div>
	</div>
<!--body end  -->
  </body>
</html>
