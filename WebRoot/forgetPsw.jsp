<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>邮箱找回密码</title>
    
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
    <div>
       <h1 style="font-family: 'Microsoft Yahei';font-size: 24px;margin-left:280px;margin-top:100px;">邮箱找回密码</h1>
       <div><span style="font-family: 'Microsoft Yahei';font-size: 15px;color:red;margin-left:280px;">请输入要找回密码的用户名，注意查收邮件！</span></div>
    </div>
    <center style="margin-top:50px;font-family: 'Microsoft Yahei';">
             <table>
                <tr>
                   <td>用户名：</td>
                   <td><input type="text" name="forUser" value="" id="forUser" style="height:30px;"></td>
                </tr>
             </table>
             <div style="margin-top:40px;">
                <a href="javascript:void(0);" onclick="forUser_form()" class="submitButton" style="height:28px;text-decoration: none;">确定</a>
             </div>
    </center>
  </body>
</html>
