//动态改变背景图
$(document).ready(function(){  
	//替换background和图片自适应大小
	$(".login-bg").css("background",
		getBgStr()
	);
	
	//ajax获取新建用户验证码
	$("a[name='emailNewUserButton']").click(function(){
		var email = $("input[name='newemail']").val();
		
		var url = "/email/Register";
		var data = "email=" + email;
		appelAjaxPost(data, url, "newUser");
	});
	//ajax注册
	$("button[name='newmailRegisterButton']").click(function(){
		var email = $("input[name='newemail']").val();
		var newemailVer = $("input[name='newemailVer']").val();
		
		var url = "/registerUser";
		var data = "email=" + email + "&newemailVer=" + newemailVer ;
		appelAjaxPost(data, url, "newUserCreate");
	});
	
	//ajax获取找回密码验证码
	$("a[name='emailFindPasswordButton']").click(function(){
		var email = $("input[name='findemail']").val();
		
		var url = "/email/FindPassword";
		var data = "email=" + email;
		appelAjaxPost(data, url, "FindPassword");
	});
	//ajax修改密码
	$("button[name='findPasswordButton']").click(function(){
		var email = $("input[name='findemail']").val();
		var newpassword = $("input[name='newpassword']").val();
		var findpasswordVer = $("input[name='findpasswordVer']").val();
		var url = "/repassword";
		var data = "email=" + email + "&newpassword=" + newpassword + "&findpasswordVer=" + findpasswordVer;
		appelAjaxPost(data, url, "passwordChange");
	});
});  

//随机获取图片链接
function getBgStr(){
	var x = 3;
	var y = 1;
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	return "url( static/images/lg-bg/bg_"+rand+".jpg )";
}