<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		<!--typography-->
		<div class="typography">
			<div class="typo-heading">
				<h2>标签编辑</h2>
			</div>
			
			<div class="panel panel-widget forms-panel">
				<div class="forms">
					<div class="form-two widget-shadow">
						<div class="form-title">
							<h4>标签内容</h4>
						</div>
						<div class="form-body" data-example-id="simple-form-inline">
							<form class="form-inline" action="/ui/saveNoteTag" method="post"> 
								
								<#if notetag??>
								<div class="form-group"> 
									<label for="noteTagName">标签名称：</label> 
									<input type="text" class="form-control" id="noteTagName" value="${notetag.notetagName}"
									name="noteTagName" placeholder="标签名称">
								</div>
								
								<div class="form-group" style="display:none">
									<label for="noteTagID" class="control-label">标签ID</label>
									<div class="">
										<input type="text" class="form-control1" name="noteTagID" value="${notetag.notetagId?c}"
										id="noteTagID" placeholder="事件ID" readonly="readonly">
									</div>
								</div>
								<#else>
								<div class="form-group"> 
									<label for="noteTagName">标签名称：</label> 
									<input type="text" class="form-control" id="noteTagName" value=""
									name="noteTagName" placeholder="标签名称" >
								</div>
								
								<div class="form-group" style="display:none">
									<label for="noteTagID" class="control-label">标签ID</label>
									<input type="text" class="form-control1" name="noteTagID" value=""
									id="noteTagID" placeholder="事件ID" readonly="readonly">
								</div>
								</#if>
								<button type="sumbit" class="btn btn-success">保存标签</button>
							</form> 
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!--//typography-->
	</div>
</div>