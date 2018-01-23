
$(document).ready(function(){  
	
	//修改密码按钮被点击
	$("button[name='changePassword']").click(function(){
		var newpassword = $("input[name='newpassword']").val();
		
		var url = "/admin/newpassword";
		var data = "newpassword=" + newpassword;
		appelAjaxPost(data, url, "changPassword");
	});
});  


function t1(){
	
}