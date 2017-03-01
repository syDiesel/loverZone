$(function() {

	var userNameValidate = true;
	var passwordOneValidate = true;
	var passwordTwoValidate = true;
	var emailValidate = true;
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
					alert("连接错误");
				},
				success : function(msg) {

					if ($.trim(msg) == "error") {
						$('#userName').empty();
						userNameValidate = false;
						alert("该用户已经注册，请重新填写");
					}else
						userNameValidate = true;

				}
			});
		}

	});

	$('#passWord').blur(function() {

		var passWord = $.trim($('#passWord').val());
		if (passWord.length < 6) {
			$('#passWord').val("");
			passwordOneValidate = false;
			alert("密码少于6位，请重新输入");
		}else
			passwordOneValidate = true;
	});

	$('#passWord_two').blur(function() {

		var passWord_one = $.trim($('#passWord').val());
		var passWord_two = $.trim($('#passWord_two').val());
		if (passWord_two.length < 6) {
			$('#passWord_two').val("");
			passwordTwoValidate = false;
			alert("密码少于6位，请重新输入");
		}else if($.trim(passWord_one) != $.trim(passWord_two)){
			$('#passWord_two').val("");
			passwordTwoValidate = false;
			alert("两次输入的密码不一致，请重新输入");
		}else
			passwordTwoValidate = true;
	});
	
	
	$('#email').blur(function(){
		
		var email = $.trim($('#email').val());
        if(!isEmail(email)){
			
			$('#sign_email').val("");
			emailValidate = false;
			alert("邮箱不合法，请重新填写");
		}else
			emailValidate = true;
	});
	
	
	$('#sub_reg').click(function(){

		var userName = $.trim($('#userName').val());
		var passWord = $.trim($('#passWord').val());
		var email = $.trim($('#email').val());
		if(userNameValidate & passwordOneValidate & passwordTwoValidate & emailValidate
				& userName.length != 0){
			
			$.ajax({
				type : 'post',
				url : basePath + 'com/register.html',
				data : "userName=" + encodeURI(userName)+"&passWord="+encodeURI(passWord)+"&email="+encodeURI(email),
				error : function() {
					alert("连接错误");
				},
				success : function(msg) {

					alert(msg);
					window.location.href=basePath +"web/com/login.jsp";

				}
			});
		}else
			alert("存在不合法，请检查");
	});

});


//----------------------------------------------------------------------------------------------
function isEmail(vEMail) {// 判断邮箱函数，
	if (vEMail != "") {
		var regInvalid = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
		var regValid = /^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?)$/;
		return (!regInvalid.test(vEMail) && regValid.test(vEMail));
	}
}




