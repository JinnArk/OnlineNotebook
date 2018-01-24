<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		<div class="grids">
			<div class="typo-heading">
				<h2>系统邮箱</h2>
			</div>
			<div class="panel panel-widget forms-panel">
				<div class="forms">
					<div class="form-title">
						<h4>邮箱设置 :</h4>
					</div>
					<div class="form-three widget-shadow">
						<div class=" panel-body-inputin">
							<form class="form-horizontal" action="/admin/emailSave" method="post">
								
								<div class="form-group">
									<label class="col-md-2 control-label">邮箱服务器:</label>
									<div class="col-md-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon">
												<i class="fa fa-envelope-o"></i>
											</span>
											<input name="emailHost" class="form-control1" type="text" placeholder="例：smtp.exmail.qq.com"
												value="${emailHost}">
										</div>
									</div>
									
								</div>
								
								<div class="form-group">
									<label class="col-md-2 control-label">邮箱账号:</label>
									<div class="col-md-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon">
												<i class="fa fa-envelope-o"></i>
											</span>
											<input name="emailUserName" class="form-control1" type="text" placeholder="例：12345678qq.com"
												value="${emailUserName}">
										</div>
									</div>
									
								</div>
								
								<div class="form-group">
									<label class="col-md-2 control-label">邮箱密码:</label>
									<div class="col-md-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon">
												<i class="fa fa-envelope-o"></i>
											</span>
											<input name="emailPassword" class="form-control1" type="text" placeholder="例：12345678"
												value="${emailPassword}">
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-4">
										<button type="button" name="emailTestButton" class="btn btn-info pull-right">测试连接</button> 
									</div>
									<div class="col-md-6">
										<button type="submit" name="emailSaveButton" class="btn btn-success pull-right">保存设置</button> 
									</div>
								</div> 
							</form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>