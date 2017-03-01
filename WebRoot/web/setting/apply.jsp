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
    
<script type="text/javascript">
function applyOK(id){
	
	$.ajax({
		type : 'post',
		url : 'com/applyOK.html',
		data : "id="+id,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			
			if($.trim(msg) == "no_login"){
				
				alert("用户没有登录，请重新登录");
				top.location="web/com/login.jsp";
			}else if($.trim(msg) == "ok"){
				
				alert("绑定成功");
				parent.mainFrame.location.href="web/setting/info.jsp";
			}else{
			
			    alert("已经绑定过，不能重复操作");
				parent.mainFrame.location.href="web/setting/info.jsp";
			
			}
			
			

		}
	});
}

function applyNO(id){
	
	$.ajax({
		type : 'post',
		url : 'com/applyNO.html',
		data : "id="+id,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			
			if($.trim(msg) == "no_login"){
				
				alert("用户没有登录，请重新登录");
				top.location="web/com/login.jsp";
			}else if($.trim(msg) == "ok"){
				
				alert("操作成功");
				parent.mainFrame.location.href="web/set/applyCheck.html";
			}
			

		}
	});
}

</script>
</head>

  <body>
    
     <div>
        <h1>
          <span style="font-family: 'Microsoft Yahei';font-size: 17px;">申请处理</span>
        </h1>
        <div style="margin-left:60px;">
          <table style="font-family: 'Microsoft Yahei';font-size: 15px;border-bottom: thin;border-bottom-style:dotted;">
           <c:if test="${!empty listApplyer }">
              <c:forEach begin="0" end="9" items="${listApplyer }" var="item">
                 <tr>
                     <td style="width:83%;">
                         <span style="color: orange;">${item.userInfoByApplyId.userName }</span>
                         申请绑定情侣关系
                     </td>
                     <td>
                        <input type="button" value="同意" class="submitButton" onclick="applyOK('${item.id }')">
                        <input type="button" value="拒绝" class="submitButton" onclick="applyNO('${item.id }')">
                     </td>
                 </tr>
              </c:forEach>
           </c:if>
           </table>
        </div>
     </div>
  </body>
</html>
