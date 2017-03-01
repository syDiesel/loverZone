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
    
    <title>日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/personDefault.css">
<link type="type/css" rel="stylesheet"
	href="<%=basePath%>css/per/personDiary.css">
<link rel="stylesheet" href="<%=basePath%>css/fenye.css">

<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script charset="utf-8" src="<%=basePath%>kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath%>kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="<%=basePath%>kindeditor/plugins/code/prettify.js"></script>
<script>
var editor;
	 KindEditor.ready(function(K) {
		editor = K.create('textarea[name="diaryContent"]', {
			cssPath : '<%=basePath%>kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '<%=basePath%>kindeditor/jsp/file_manager_json.jsp',
			//简单模式
			/* resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
 */
			//图片空间管理
			allowFileManager : true,
			autoHeightMode : false,
			
			 
			afterCreate : function() {
				this.loadPlugin('autoheight');
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			},afterBlur: function(){this.sync();}
		});
		
	    K('input[name=submit]').click(function(e) {
            var imgs = editor.edit.iframe.get().contentWindow.document.body.getElementsByTagName('img');
            if (imgs.length > 0) {
                document.getElementById('hiddenImg').value=imgs[0].src;
            }
        });
		
		prettyPrint();
		

	}); 
	 
	function setImg()
	{
	         
	          var imgs = editor.edit.iframe.get().contentWindow.document.body.getElementsByTagName('img');
	         
			    if (imgs.length > 0) {
			        document.getElementById('hiddenImg').value=imgs[0].src;
			      
			    }
			    
			    submitForm();
			    
	}
	
</script>
  </head>
  
  <body>
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   <div class="personDiary_body">
	   <c:if test="${loginUser.id eq blogUser.id }">
				<form action="web/per/addDiary.html" name="example"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="hidden_img" id="hidden_img" value="">
						<div class="com_form">
						   <div><h class="title">标题：</h><input type="text" name="diarySub" class="diarySub"></div>
							<textarea id="diaryContent"  cols="100" rows="8" name="diaryContent"
					style="width: 898px; height: 200px;"></textarea>
							<input type="submit" class="sub_btn" id="sub_btn" value="发表">
						</div>
				</form>
		</c:if>
	    
	    <div class="diary_list">
	        <c:if test="${!empty listDiary }">
	              <c:forEach items="${listDiary }" var="item">
	                 <div class="diary_detail">
	                     <div class="blog_person">
								<img
									src="<%=basePath %>${item.userInfo.headImg}"
									width=70px height=70px style="float:left;">
								<div class="blog_info">
								<a href="">${item.userInfo.userName
									}</a></div>
								<div class="blog_time">${item.diaryTime }
								   <span class="diary_visit">阅读${item.diaryVisited }次</span>
								   <c:if test="${loginUser.id eq blogUser.id }">
								      <span class="delete"><a href="<%=basePath%>web/per/deleteDiary/${item.id}.html?page=${page}">删除</a></span>
								   </c:if>
								</div>
						</div>
						
						<div class="diary_content">
						   <div class="diary_sub"><a href="<%=basePath%>web/per/detailDiary/${item.id}.html">标题：${item.diarySubject }</a></div>
						   <div class="diary_desc">${item.diaryContent }</div>
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
	        
	        <c:if test="${empty listDiary }">
	              <div class="none">没有日记</div>
	        </c:if>
	    </div>
	</div>
<!--body end  -->
  </body>
</html>
