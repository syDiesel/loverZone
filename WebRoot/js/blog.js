function good(id){
	
	$.ajax({

		type : 'post',
		url : 'web/per/good.html',
		data : "id="+id,
		error : function() {

			alert("连接错误");
		},
		success : function(msg) {
			$('#allGood_'+id).html(""+msg+"&nbsp;等人觉得很赞");

		}
	});
}