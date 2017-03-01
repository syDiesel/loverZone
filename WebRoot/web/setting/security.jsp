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
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/security.js"></script>
<script language="text/javascript">
    $('#security_psw').empty();
</script>
</head>
<style>
    .info_save{
	font-family: "Microsoft Yahei";
	font-size: 18px;
	background: #1C86EE;
	border: none;
	border-radius: 0;
	/* height: 28px; */
	line-height: 16px;
	color: #FFF;
	padding-bottom: 6px;
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 6px;
	text-align: center;
	cursor:pointer;
	margin-right:40px;
	border-radius:5px;
}
</style>
<body>
	<div style="margin-left:30px;">
		<h1 style="font-family: 'Microsoft Yahei';font-size: 25px;">密保改密</h1>
	</div>
	<div style="width:90%;margin:20 0px;margin-left:100px;">
	    <c:if test="${empty listSec }">
	        <div style="font-family: 'Microsoft Yahei';font-size: 14px;color:gray;">您还没设置，请先设置密保问题，设置不可更改，便用于找回密码</div>
	       <form action="web/set/setSecurity.html" method="post">
	        <table style="margin-top:50px;font-family: 'Microsoft Yahei';font-size: 18px;">
	            <tr style="height:50px;">
	               <td>密保问题：</td>
	               <td>
	                  <select name="security_q" style="height:30px;font-family: 'Microsoft Yahei';font-size: 16px;">
	                     <c:forEach items="${listQuestion }" var="item">
	                        <option value="${item.id }">${item.question }</option>
	                     </c:forEach>
	                  </select>
	               </td>
	            </tr>
	            <tr style="height:50px;">
	               <td>问题答案：</td>
	               <td>
	                  <input type="text" name="security_a" value="" style="height:30px;font-family: 'Microsoft Yahei';font-size: 15px;">
	               </td>
	            </tr>
	        </table>
	        <div>
	           <input type="submit" value="保存" class="info_save">
	        </div>
	       </form>
	    </c:if>
	    <input type="hidden" name="basePath" value="<%=basePath%>" id="basePath">
	    <c:if test="${!empty listSec }">
	       
	       <div style="font-family: 'Microsoft Yahei';font-size: 14px;color:gray;">您已设置密保问题，可用于找回密码</div>
	       <form action="web/set/resetPsw.html" method="post" id="rePsw_form">
	        <table style="margin-top:50px;font-family: 'Microsoft Yahei';font-size: 18px;">
	            <tr style="height:50px;">
	               <td>密保问题：</td>
	               <td>
	                  <select name="security_q" style="height:30px;font-family: 'Microsoft Yahei';font-size: 16px;">
	                     <c:forEach items="${listQuestion }" var="item">
	                        <option value="${item.id }">${item.question }</option>
	                     </c:forEach>
	                  </select>
	               </td>
	            </tr>
	            <tr style="height:50px;">
	               <td>问题答案：</td>
	               <td>
	                  <input type="text" name="security_a" id="security_a" value="" style="height:30px;font-family: 'Microsoft Yahei';font-size: 15px;">
	               </td>
	            </tr>
	             <tr style="height:50px;">
	               <td>重置密码：</td>
	               <td>
	                  <input type="password" name="reset_psw" id="security_psw" value="" style="height:30px;font-family: 'Microsoft Yahei';font-size: 15px;">
	               </td>
	            </tr>
	        </table>
	        <div>
	           <input type="button" value="保存" class="info_save" id="resetPsw">
	        </div>
	       </form>
	    </c:if>
	</div>
</body>
</html>
