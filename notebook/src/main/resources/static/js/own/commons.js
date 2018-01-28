$(document).ready(function(){  
	//修改密码按钮被点击
	$("button[name='changePassword']").click(function(){

		var oldpassword = $("input[name='oldpassword']").val();
		var password = $("input[name='password']").val();
		var repassword = $("input[name='repassword']").val();
		
		var url = "/commons/newpassword";
		var data = "oldpassword=" + oldpassword + "&newpassword=" + password;
		appelAjaxPost(data, url, "changPassword");
	});
	
	//站内信-发送按钮被点击
	$("button[name='newLetterButton']").click(function(){

		var recipientName = $("input[name='newLetterRecipientName']").val();
		var letterTitle = $("input[name='newLetterTitle']").val();
		var letterContent = $("textarea[name='newLetterContent']").val();
		
		var url = "/commons/sendLetter";
		var data = "recipientName=" + recipientName + "&letterTitle=" + letterTitle + "&letterContent=" + letterContent;
		appelAjaxPost(data, url, "sendLetter");
	});
	
	//站内信-回复-发送按钮被点击
	$("input[name='reLetterButton']").click(function(){

		var recipientName = $("input[name='reLetterRecipientName']").val();
		var letterTitle = $("input[name='reLetterTitle']").val();
		var letterContent = $("input[name='reLetterContent']").val();
		var replyLetterID = $("input[name='replyLetterID']").val();
		
		var url = "/commons/sendLetter";
		var data = "recipientName=" + recipientName + "&letterTitle=" + letterTitle 
			+ "&letterContent=" + letterContent + "&replyLetterID=" + replyLetterID;
		appelAjaxPost(data, url, "sendLetter");
	});
	
	//站内信批量操作-全选
	$("input[name='allLetterCheck']").change(function(){
		if($(this).is(':checked')){//全选是否被选中
			$("input[name^='letterbox-']").each(function(){//模糊遍历
				//alert($(this).val());
				$(this).prop('checked',true);//设置全选
			});
		}
	});

	
	//站内信批量操作-标记已读
	$("button[name='readlettersButton']").click(function(){
		
		var letters=new Array();
		$("input[name^='letterbox-']").each(function(){//模糊遍历
			if($(this).is(':checked')){
				letters.push($(this).val());
			}
		});
		//alert(letters);
		
		var url = "/commons/readLetters";
		var data = "letterIDs=" + letters;
		appelAjaxPost(data, url, "readLetters");
	});
});