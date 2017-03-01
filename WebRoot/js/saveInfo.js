function save_personInfo(){
	
	var sex = $("input[name='sex']:checked").val();
	var age = $.trim($('#age').val());
	var address = $.trim($('#address').val());
	var contact = $.trim($('#contact').val());
	var homeName = $.trim($('#homeName').val());
	var homeDesc = $.trim($('#homeDesc').val());
	var basePath = $.trim($('#basePath').val());

	$.ajax({

		type : 'post',
		url : basePath+'web/set/personInfo.html',
		data : "sex="+sex+"&age="+age+"&address="+address+"&contact="+contact+"&homeName="+homeName+"&homeDesc="+homeDesc,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			alert(msg);

		}
	});
	
}