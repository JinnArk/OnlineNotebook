
$(document).ready(function(){  
	//表单验证
	//
	
	//修改密码按钮被点击
	$("button[name='changePassword']").click(function(){

		var oldpassword = $("input[name='oldpassword']").val();
		var password = $("input[name='password']").val();
		var repassword = $("input[name='repassword']").val();
		
		var url = "/admin/newpassword";
		var data = "oldpassword=" + oldpassword + "&newpassword=" + password;
		appelAjaxPost(data, url, "changPassword");
	});
	
	//测试连接按钮被点击
	$("button[name='emailTestButton']").click(function(){

		var host = $("input[name='emailHost']").val();
		var username = $("input[name='emailUserName']").val();
		var password = $("input[name='emailPassword']").val();
		
		var url = "/admin/emailConnectTest";
		var data = "host=" + host + "&username=" + username + "&password=" + password;
		appelAjaxPost(data, url, "emailConnectTest");
	});
});  


function t1(){
	
}