$(function(){
	
	var userNameValidate = true;
	var passwordValidate = true;
	var basePath = $.trim($('#basePath').val());
	
	$('#userName').blur(function() {

		var userName = $.trim($('#userName').val());
		
		if(userName == null || userName == ""){
			
			alert("用户名不能为空");
			userNameValidate = false;
		}else{
			
			$.ajax({
				type : 'post',
				url : basePath + 'com/checkUserName',
				data : "userName=" + encodeURI(userName),
				error : function() {
					userNameValidate = false;
					alert("连接错误");
				},
				success : function(msg) {

					if ($.trim(msg) != "error") {
						$('#userName').empty();
						userNameValidate = false;
						alert("不存在该用户，请重新填写");
					}else
						userNameValidate = true;

				}
			});
		}
		
	});
	
	
	$('#passWord').blur(function() {

		var passWord = $.trim($('#passWord').val());
		if (passWord.length < 6) {
			passwordValidate = false;
			$('#passWord').val("");
			alert("密码少于6位，请重新输入");
		}else
			passwordValidate = true;
	});
	
	$('#sub_login').click(function(){

		var userName = $.trim($('#userName').val());
		if(userNameValidate & passwordValidate & userName.length != 0){

			$('#form_login').submit();
		}else
			alert("存在不合法，请检查");
	});
	
});