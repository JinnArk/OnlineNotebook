
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>首页</title>
	<link type="text/css" href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="static/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link type="text/css" href="static/css/theme.css" rel="stylesheet">
	<link type="text/css" href="static/images/icons/css/font-awesome.css" rel="stylesheet">
	<link type="text/css" href="static/css/googleapis_css.css" rel='stylesheet'>
</head>
<body>
	<#include "head.ftl">

	<div class="wrapper">
		<div class="container">
			
			<div class="row">
				<div class="module module-login span3 offset2" style="margin-top: 10px;">
					<form class="form-vertical">
						<div class="module-head">
							<h3>老用户</h3>
						</div>
						<div class="module-body">
							
							<div class="control-group">
								<div class="controls row-fluid"><img src="static/images/old_user.png" alt="">
									
								</div>
							</div>
						</div>
						<div class="module-foot">
							<div class="control-group">
								<div class="controls clearfix">
									<a class="btn btn-success pull-right" href="ui_login.html">
										马上登录
									</a>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="module module-login span3 offset2" style="margin-top: 10px;">
						<form class="form-vertical">
							<div class="module-head">
								<h3>新用户</h3>
							</div>
							<div class="module-body">
								<div class="control-group">
									<div class="controls row-fluid"><img src="static/images/new_user.png" alt="">
										
									</div>
								</div>
								
							</div>
							<div class="module-foot">
								<div class="control-group">
									<div class="controls clearfix">
									
										<a class="btn btn-primary pull-right" href="ui_register.html">
											立即注册
										</a>
									</div>
								</div>
							</div>
						</form>
				</div>
			</div>
			
		</div>
	</div>

	<#include "foot.ftl">
	
	<script src="static/scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="static/scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
	<script src="static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>