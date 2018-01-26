<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		<!--typography-->
		<div class="typography">
			<div class="typo-heading">
				<h2>公告编辑</h2>
			</div>
			
			<div class="panel panel-widget forms-panel">
				<div class="forms">
					<div class="form-grids widget-shadow" data-example-id="basic-forms"> 
						<div class="form-title">
							<h4>公告内容 :</h4>
						</div>
						<div class="form-body">
							<form action="/admin/savenotice" method="post"> 
							<#if notice??>
								<div class="form-group">
									<label for="noticeTitle">公告名称</label>
									<input type="text" class="form-control" id="noticeTitle" name="noticeTitle"
									value='${notice.noticeTitle!''}' placeholder="事件名称"> 
								</div> 
								
								<div class="form-group" hidden>
									<label for="noticeID" class="control-label">公告ID</label>
									<input type="text" class="form-control1" id="noticeID" name="noticeID"
									value='${notice.noticeId?c !''}'  placeholder="公告ID" readonly="readonly" />
								</div>
								
								<div class="form-group">
									<label for="createDate" class="control-label">创建日期</label>
									<div class="">
										<input disabled="" type="text" class="form-control1"
										value='${notice.createDate!''}' id="createDate" name="createDate" placeholder="创建日期">
									</div>
								</div>
								
								<div class="form-group">
									<label for="noticeContent" class="control-label">公告内容</label>
									<div class="">
										<textarea name="noticeContent" id="noticeContent" cols="50"
											rows="4" class="form-control1" style="height: 300px;">${notice.noticeContent!''}</textarea>
									</div>
								</div>
							<#else>
								<div class="form-group">
									<label for="noticeTitle">公告名称</label>
									<input type="text" class="form-control" id="noticeTitle" name="noticeTitle"
									value='' placeholder="事件名称"> 
								</div> 
								
								<div class="form-group">
									<label for="noticeContent" class="control-label">公告内容</label>
									<div class="">
										<textarea name="noticeContent" id="noticeContent" cols="50"
											value='' rows="4" class="form-control1" style="height: 300px;"></textarea>
									</div>
								</div>
							</#if>
								<div class="form-group">
									<button type="submit" class="btn btn-success">保存</button> 
									
									<a href="/admin/notices" type="button" class="btn btn-info pull-right">返回</a> 
								</div> 
								
							</form> 
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!--//typography-->
	</div>
</div>