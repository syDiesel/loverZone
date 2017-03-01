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

<title>注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="<%=basePath%>css/com/register.css"/>

<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/register.js"></script>

</head>

<body class="main">
    <input type="hidden" value="<%= basePath %>" id="basePath">
    <jsp:include page="${basePath }/top.jsp" flush="true"/>
    <div class="pic">
        <img src="<%= basePath %>img/lover_logo.jpg">
    </div>
	<div class="wrap">
		<h1>注册</h1>
		<form action="com/register.html" method="post">
			<div>
				<div style="height:60px;">
					<input type="text" name="userName" value="" class="sign_input"
						placeholder="请输入用户名" id="userName">
				</div>
				<div style="height:60px;">
					<input type="password" name="passWord" value="" class="sign_input"
						placeholder="请输入至少6位密码" id="passWord">
				</div>
				<div style="height:60px;">
					<input type="password" name="passWord_two" value="" class="sign_input"
						placeholder="请再一次输入密码"  id="passWord_two">
				</div>
				<div style="height:60px;">
					<input type="text" name="email" value="" class="sign_input"
						placeholder="请输入邮箱"  id="email">
				</div>
				<div style="height:60px;padding-left:20px;">
					<input type="reset" value="重置" class="sign_sub" style="width:80px;">
					<input type="button" value="注册" class="sign_sub" style="width:80px;" id="sub_reg">
				</div>
			</div>
		</form>
	</div>
</body>
</html>
