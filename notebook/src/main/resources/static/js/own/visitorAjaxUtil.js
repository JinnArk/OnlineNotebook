function appelAjax(data, url, ident) {
	$.ajax({
		type : "GET", 
		data : encodeURI(data), 
		datatype : "json", 
		url : url,
		success : function(object) {
			handle(object, ident);
		},
		
		error : function(e) {
		},
		beforeSend : function() {
		}
	});
}

function appelAjaxPost(data, url, ident) {
	$.ajax({
		type : "POST", 
		data : encodeURI(data), 
		datatype : "json", 
		url : url,
		async : false,
		success : function(object) {
			handle(object, ident);
		},
		error : function(e) {

		},
		beforeSend : function() {
		}
	});
}

function handle(object, ident) {
	if (ident == "newUser") {
		alert("newUser");
		alert(object);
		if (object == "newUser") {
			//bootbox.alert("");
		} else {
			//alert("保存完毕");
			//location.reload();
		}
	}
	
	if (ident == "FindPassword") {
		alert("FindPassword");
		alert(object);
		if (object == "FindPassword") {
			//bootbox.alert("");
		} else {
			//alert("保存完毕");
			//location.reload();
		}
	}
	
	if (ident == "newUserCreate") {
		alert("newUserCreate");
		alert(object);
		if (object == "newUserCreate") {
			//bootbox.alert("");
		} else {
			//alert("保存完毕");
			//location.reload();
		}
	}
	
	if (ident == "passwordChange") {
		alert("passwordChange");
		alert(object);
		if (object == "passwordChange") {
			//bootbox.alert("");
		} else {
			//alert("保存完毕");
			//location.reload();
		}
	}
}