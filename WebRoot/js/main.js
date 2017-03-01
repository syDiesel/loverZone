function toSetMain(){

	$.ajax({

		type : 'post',
		url : 'web/set/toSetMain.html',
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			
			if($.trim(msg) == "no_login"){
				
				alert("用户没有登录，请重新登录");
				top.location="web/com/login.jsp";
			}else{
				
				top.location="web/setting/main.jsp";
			}
			

		}
	});
}