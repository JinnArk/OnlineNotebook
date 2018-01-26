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
	if (ident == "changPassword") {
		alert("changPassword");
		alert(object);
		if (object == "2") {
			//bootbox.alert("");
		} else {
			//alert("保存完毕");
			//location.reload();
		}
	}
	
	if (ident == "emailConnectTest") {
		alert("emailConnectTest");
		alert(object);
		if (object == "1") {
		} else {
		}
	}
	
	if (ident == "sendLetter") {
		alert("sendLetter");
		alert(object);
		if (object == "success") {
			location.reload();
		} else {
		}
	}
	
	if (ident == "readLetters") {
		alert("readLetters");
		alert(object);
		if (object == "success") {
			location.reload();
		} else {
		}
	}
}