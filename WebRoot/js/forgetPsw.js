function forUser_form(){
	
	var userName = $('#forUser').val();
	
	$.ajax({
		
		type : 'post',
		url : 'com/forUser.html',
		data : "userName="+userName,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			alert(msg);

		}
	});
}



function newPsw_form(){
	
	var basePath = $('#basePath').val();
	var id = $('#hid_user').val();
	var psw = $('#newPsw').val();
	
	$.ajax({
		
		type : 'post',
		url : 'com/setPsw.html',
		data : "id="+id+"&psw="+psw,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			if(msg == 'ok'){
				window.location.href=basePath+"web/com/login.jsp";
			}

		}
	});
}