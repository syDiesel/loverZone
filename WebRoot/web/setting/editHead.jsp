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

<title>My JSP 'info.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

    <link rel="icon" href="<%=basePath%>img/lover.jpg" type="image/x-icon">
	<link type="type/css" rel="stylesheet" href="<%=basePath%>css/album_creat.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/album_creat.js"></script>
<script type="text/javascript">

   function func(){
   
       var basePath = $('#basePath').val();
       var vs = basePath+$('select  option:selected').val();
       $('#show_head').html("<img src='"+vs+"' width=150 height=150>");
   }
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
  <input type="hidden" name="basePath" value="<%=basePath%>" id="basePath">
	<div style="margin-left:30px;">
		<h1 style="font-family: 'Microsoft Yahei';font-size: 25px;">头像编辑</h1>
	</div>
	<div style="margin:20 0px;margin-left:100px;font-family: 'Microsoft Yahei';font-size: 18px;">
	<form action="web/set/setHead.html" method="post">
	    <div style="width:300px;margin-top:50px;">
	        <div>选择系统头像:
	           <span>
	              <select name="syHead" onchange="func()" style="height:30px;font-family: 'Microsoft Yahei';font-size: 15px;">
	                 <option value="img/headImg/head.jpg">阿狸</option>
	                 <option value="img/headImg/head1.jpg">兔子</option>
	                 <option value="img/headImg/head2.jpg">柯南</option>
	                 <option value="img/headImg/head3.jpg">文字</option>
	                 <option value="img/headImg/head4.jpg">爱情</option>
	                 <option value="img/headImg/head5.jpg">猴子</option>
	                 <option value="img/headImg/head6.jpg">小孩儿</option>
	                 <option value="img/headImg/head7.jpg">风车</option>
	                 <option value="img/headImg/head8.jpg">拥抱</option>
	                 <option value="img/headImg/head9.jpg">顺其自然</option>
	              </select>
	           </span>
	        </div>
	    </div>
	    
	    <div style="margin-top:50px;">
	        <input type="submit" value="保存" class="info_save" id="album_creat" target="_blank">
	    </div>
	    </form>
	    <div style="width:200px;height:200px;margin-right:200px;float:right;margin-top:-100px;">
	       <span id="show_head"><img src="<%=basePath%>img/headImg/head.jpg" width=150px height=150px></span>
	    </div>
	
	</div>
</body>
</html>
