$(function(){
	
	var basePath = $('#basePath').val();
	
	$('#resetPsw').click(function(){
		
		if(check()){
			
			$.ajax({

				cache : true,
				type : "POST",
				url : basePath+"web/set/resetPsw.html",
				data : $('#rePsw_form').serialize(),
				async : false,
				error : function(request) {
					alert("连接错误");
				},
				success : function(msg) {
					
					alert(msg);
					if(msg == 'ok'){
						window.location.href=basePath+"index.jsp";
					}
				}
			});
		}
		
	});
	
});

function check(){
	
	var security_a = $('#security_a').val();
	var security_psw = $('#security_psw').val();
	
	if(security_a==null||security_a==""){
		alert("请填写密保问题答案");
		return false;
	}else if(security_psw==null||security_psw==""){
		
		alert("请填写重置密码");
		return false;
	}else
		return true;
}

