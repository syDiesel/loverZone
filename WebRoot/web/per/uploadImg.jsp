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
	<link type="type/css" rel="stylesheet" href="<%=basePath%>css/per/uploadImg.css">
	<link type="type/css" rel="stylesheet" href="<%=basePath%>css/button.css">
	<link type="type/css" rel="stylesheet" href="<%=basePath%>css/album_creat.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/album_creat.js"></script>

  </head>
  
  <body>
  
    <!---------------------------creat_begin  ------------------------->
  <div id="main_creat" class="main_creat">
  </div>
  <div class="creat_div" id="creat_div">
        <div class="creat_div_head">
            <h1>创建相册</h1>
        </div>
        <div class="creat_div_main">
           <form action="<%=basePath %>web/per/creat.html" method="post">
              <input type="hidden" value="" id="creat_href" name="creat_href">
              <div class="album_creat_name">
                 <h>相册名：</h>
                 <span><input type="text" name="album_creatName"></span>
              </div>
              <div class="album_creat_pwd">
                 <h>设置密码：</h>
                 <span id="make_pwd">
                     <input type="radio" name="album_isPwd" value="0" id="pwd_no" checked="checked">否
                     <input type="radio" name="album_isPwd" value="1" id="pwd_yes">是
                 </span>
                 <span id="input_pwd" class="input_pwd">
                     <input type="text" name="album_pwd" class="inputPwd" id="inputPwd">
                     <input type="button" name="button" id="but_no" value="取消">
                 </span>
              </div>
              <div class="album_creat_desc">
                 <h>相册描述：</h>
                 <span><textarea name="album_desc" cols="60" rows="10" class="album_desc">
                            </textarea></span>
              </div>
              <div class="album_creat_but">
                 <input type="submit" name="submit" value="创建">
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="reset" name="reset" id="album_reset" value="取消">
              </div>
           </form>
        </div>
  </div>
    <!---------------------------creat_end  ------------------------->
  
  
    <jsp:include page="${basePath }/top.jsp" flush="true"/>

<!--body Star  -->
   <!--main begin  -->
    <div class="upload_main">   
       <div class="upload_body">
           <div class="upload_body_head">
               <h3>相片上传</h3>
               <div class="upload_body_right" style="margin-top:-20px;">
                   <a class="submitButton" href="<%=basePath%>web/per/listAlbum.do?id=${loginUser.id}">返回相册首页</a>
               </div>
           </div>
           <form action="<%=basePath %>web/per/uploadImgFile.html" method="post" enctype="multipart/form-data">
               <div class="upload_style" style="margin-top:20px;">
                   <table class="upload_table">
                      <tr  class="upload_table_tr">
                         <td class="upload_table_td">相册名：</td>
                         <td class="upload_table_td2">
                            <select name="selectAlbum">
                                <option value="please">请选择</option>
                                <c:forEach items="${listAlbum }" var="listAlbum">
									<option value="${listAlbum.albumName }">${listAlbum.albumName}</option>
								</c:forEach>
                            </select>
                            <span class="album_creat" id="album_creat">创建</span>
                         </td>
                      </tr>
                      <tr  class="upload_table_tr">
                         <td class="upload_table_td">选择文件：</td>
                         <td class="upload_table_td2">
                           <input type="file" name="file">
                         </td>
                      </tr>
                      <tr  class="upload_table_tr">
                         <td class="upload_table_td">相片描述：</td>
                         <td class="upload_table_td2">
                            <textarea name="photo_desc" cols="60" rows="10" class="photo_desc">
                            </textarea>
                         </td>
                      </tr>
                   </table>
                   <div class="uploadtip" style="margin-left:30px;">
						<SPAN>提示：你可以上传JPG、BMP、JPEG、GIF或PNG文件。（限传2M以内）</SPAN><INPUT
							class="righttip" type="submit" value="立即上传">
				  </div>
               </div>
           </form>
       </div>
    </div>
    <!--main end  -->
<!--body end  -->
  </body>
</html>
