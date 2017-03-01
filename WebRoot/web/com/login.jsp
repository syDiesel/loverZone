<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/com/register.css" />
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/login.js"></script>

</head>

<body class="main">
    <jsp:include page="${basePath }/top.jsp" flush="true"/>
    <input type="hidden" value="<%=basePath%>" id="basePath">
	<div class="pic">
		<img src="<%=basePath%>img/lover_logo.jpg">
	</div>
	<div class="wrap" style="padding-top:7%;">
		<h1>登录</h1>
		<form action="com/login.html" method="post" id="form_login">
			<div style="padding-top:20px;">
				<div style="height:60px;">
					<input type="text" name="userName" value="" class="sign_input"
						placeholder="请输入公司名" id="userName">
				</div>
				<div style="height:60px;">
					<input type="password" name="passWord" value="" class="sign_input"
						placeholder="请输入至少6位密码" id="passWord">
				</div>
				<div style="height:60px;padding-left:20px;">
					<input type="reset" value="重置" class="sign_sub" style="width:80px;">
					<input type="button" value="登录" class="sign_sub"
						style="width:80px;" id="sub_login">
						
				</div>
				<div style="margin-top:-15px;margin-left:18px;">
				   <a href="forgetPsw.jsp" style="font-family: 'Microsoft Yahei';font-size: 12px;text-decoration: none;">
				   忘记密码？</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
