<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		<!--typography-->
		<div class="typography">
			<div class="typo-heading">
				<h2>事件编辑</h2>
			</div>
			
			<div class="panel panel-widget forms-panel">
				<div class="forms">
					<div class="form-grids widget-shadow" data-example-id="basic-forms"> 
						<div class="form-title">
							<h4>事件内容 :</h4>
						</div>
						<div class="form-body">
							<form action="/ui/saveNote" method="post"> 
								<#if note??>
								<div class="form-group">
									<label for="noteTitle">事件名称</label>
									<input type="text" class="form-control" id="noteTitle" value="${note.noteTitle}"
									name="noteTitle" placeholder="事件名称"> 
								</div> 
								
								<div class="form-group" hidden>
									<label for="noteID" class="control-label">事件ID</label>
									<div class="">
										<input type="text" class="form-control1" name="noteID" value="${note.noteId?c}"
										id="noteID" placeholder="事件ID" readonly="readonly">
									</div>
								</div>
								<#else>
								<div class="form-group">
									<label for="noteTitle">事件名称</label>
									<input type="text" class="form-control" id="noteTitle" value=""
									name="noteTitle" placeholder="事件名称"> 
								</div> 
								</#if>
								<div class="form-group">
									<label for="noteTagID">标签选择</label>
									
									<select name="noteTagID" id="noteTagID" class="form-control">
										<#list notetags as noteTag>
										<option value="${noteTag.notetagId?c}">${noteTag.notetagName}</option>
										</#list>
									</select>
									
								</div>
								
								<#if note??>
								<div class="form-group">
									<label for="noteContent" class="control-label">事件内容</label>
									<div class="">
										<textarea name="noteContent" id="noteContent" cols="50"
											rows="4" class="form-control1" style="height: 300px;">${note.noteContent}</textarea>
									</div>
								</div>
								<#else>
								<div class="form-group">
									<label for="noteContent" class="control-label">事件内容</label>
									<div class="">
										<textarea name="noteContent" id="noteContent" cols="50"
											rows="4" class="form-control1" style="height: 300px;"></textarea>
									</div>
								</div>
								</#if>
								
								<div class="form-group">
									<button type="submit" class="btn btn-success">保存</button> 
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