<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/saveInfo.js"></script>
	
	<script type="text/javascript">
	   
	   
	   
	   function myFun(){
	   
	      var sex = $('#hid_sex').val();
	      if(sex == '女'){
	       
	         $("input[name=sex]:eq(1)").attr("checked",'checked');
	      
	      }else{
	      
	         $("input[name=sex]:eq(0)").attr("checked",'checked');
	        
	      }
	   
	   }
	</script>

  </head>
  <style>
  .info_save{
	
	float:right;
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
.info_lab{
	
	font-family: "Microsoft Yahei";
	font-size: 17px;
	
}
.info_input{
	
	height:35px;
	width:180px;
	border-radius:5px;
}
.info_input:active{
	
	border-color: blue;
}
.info_area{
	
	border-radius:5px;
}
.info_area:active{
	
	border-color: blue;
}
</style>
  <body onload="myFun()">
  <input type="hidden" name="basePath" id="basePath" value="<%=basePath%>">
    <div style="margin-left:30px;">
		<h1 style="font-family: 'Microsoft Yahei';font-size: 25px;">${loginUser.userName }的个人资料</h1>
		</div>
		<div style="width:90%;margin:20 0px;margin-left:80px;">
			<table>
				<tr height="80px;">
                           <td width=12% class="info_lab">性别：</td><td width=38%>
                                <input type="hidden" name="hid_sex" value="${loginUser.sex }" id="hid_sex">
                                <input type="radio" name="sex" value="男"><span class="info_lab">男</span>
                                &nbsp;<input type="radio" name="sex" value="女" ><span class="info_lab">女</span>
                             
                           </td>
                           
                           <td width=12% class="info_lab">年龄：</td><td width=38%><input type="text" id="age" name="age" value="${loginUser.age }"  class="info_input"></td>
                        </tr>
                        <tr height="80px;">
                           <td class="info_lab">家庭住址：</td><td><input type="text" name="address" id="address" value="${loginUser.address }"  class="info_input"></td>
                           <td class="info_lab">联系方式：</td><td><input type="text" name="contact" id="contact" value="${loginUser.contact }"  class="info_input"></td>
                        </tr>
                        <tr height="80px;">
                           <td class="info_lab">空间名称：</td><td><input type="text" name="homeName" id="homeName" value="${loginUser.homeName }"  class="info_input"></td>
                           <td class="info_lab">空间描述：</td>
                           <td>
                              <textarea cols="40" rows="3" name="homeDesc" id="homeDesc" class="info_area">${loginUser.homeDesc }</textarea>
                           </td>
                        </tr>
                    </table>
                    <div style="margin-top:30px;width:100%;">
                          <input type="submit" value="保存" class="info_save" id="save_personInfo" onclick="save_personInfo()">
                    </div>
	</div>
  </body>
</html>
