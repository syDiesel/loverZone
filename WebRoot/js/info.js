$(function(){
	
	$('#relieve').click(function(){
		
		$.ajax({

			type : 'post',
			url : 'com/relieve.html',
			error : function() {

				alert("连接错误");
			},
			success : function(msg) {
				
				if($.trim(msg) == "no_login"){
					
					alert("用户没有登录，请重新登录");
					window.open("web/com/login.jsp");
				}else{
					alert("解除成功");
					$('#lover_part').html("<span style='font-family:\"Microsoft Yahei\";'>还没有匹配伴侣</span>");
				}
				

			}
		});
	});
});