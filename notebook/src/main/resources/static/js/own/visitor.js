//动态改变背景图
$(document).ready(function(){  
	//替换background和图片自适应大小
	$(".login-bg").css("background",
		getBgStr()
	);
});  

//随机获取图片链接
function getBgStr(){
	var x = 3;
	var y = 1;
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	return "url( static/images/lg-bg/bg_"+rand+".jpg )";
}