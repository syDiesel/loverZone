function beLoverApply(id,page){

	$.ajax({

		type : 'post',
		url : 'com/beLoverApply.html',
		data : "id="+id,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			
			if($.trim(msg) == "no_login"){
				
				alert("用户没有登录，请重新登录");
				top.location="web/com/login.jsp";
			}else{
				
				parent.mainFrame.location.href="web/set/searchLover.html?page="+page;
			}
			

		}
	});
}