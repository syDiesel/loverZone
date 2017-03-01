<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>重置密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/button.css"/>
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">

    <script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/forgetPsw.js"></script>
  </head>
  
  <body>
  <input type="hidden" name="basePath" value="<%=basePath%>" id="basePath">
  <input type="hidden" name="" value="${user.id }" id="hid_user">
    <div>
       <h1 style="font-family: 'Microsoft Yahei';font-size: 24px;margin-left:280px;margin-top:100px;">重置密码</h1>
       <div><span style="font-family: 'Microsoft Yahei';font-size: 15px;color:red;margin-left:280px;">请设置新密码！</span></div>
    </div>
    <center style="margin-top:50px;font-family: 'Microsoft Yahei';">
             <table>
                <tr>
                   <td>新密码：</td>
                   <td><input type="password" name="newPsw" value="" id="newPsw" style="height:30px;"></td>
                </tr>
             </table>
             <div style="margin-top:40px;">
                <a href="javascript:void(0);" onclick="newPsw_form()" class="submitButton" style="height:28px;text-decoration: none;">确定</a>
             </div>
    </center>
  </body>
</html>
