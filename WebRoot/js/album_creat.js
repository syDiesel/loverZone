$(function(){

	$("#album_creat").click(function() {
		/*var href = encodeURIComponent(window.location.href);*/
		var href = window.location.href;
		var height = $(document.body).height();
		
		$("#main_creat").css("width", "100%");
		$("#main_creat").css("height", height);
		$("#main_creat").css("position", "absolute");
		$("#main_creat").css("background", "black");
		$("#main_creat").css("opacity", "0");
		$("#main_creat").css("z-index", "10");
		$("#main_creat").css("display", "block");
		
		$("#creat_div").css("position", "absolute");
		$("#creat_div").css("z-index", "20");
		$("#creat_div").css("display", "block");
	});
	
	$("#main_creat").click(function(){
		
		$("#main_creat").css("display", "none");
		$("#creat_div").css("display", "none");
	});
	
	$("#album_reset").click(function(){
		$("#main_creat").css("display", "none");
		$("#creat_div").css("display", "none");
	});
	
	$("#pwd_yes").click(function(){
		$("#input_pwd").css("display","block");
		$("#make_pwd").css("display","none");
	});
	
	$("#but_no").click(function(){
		$("#inputPwd").val("");
		$("#input_pwd").css("display","none");
		$("#make_pwd").css("display","block");
		$('input:radio[name=album_isPwd]')[0].checked = true;
	});
	
	
	
	
});
