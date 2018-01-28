<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>记事本管理</h2>
					</div>
					
					<div class="panel panel-widget forms-panel">
						<div class="forms">
							<div class="form-two widget-shadow">
								<div class="form-title" class="form-horizontal">
									<h4>事件查询</h4>
								</div>
								<div class="form-body" data-example-id="simple-form-inline">
									<form class="form-inline" action="/ui/notes" method="get"> 
										<div class="form-group"> 
											<label for="exampleInputName2">记录时间：</label> 
											<input type="text" class="form-control" id="createDate" name="createDate"
											placeholder="2018-1-28">
										</div>
										<!---->
										<div class="form-group">
											<label for="noteTagID">标签类别：</label> 
											<div class="form-group">
												<select name="noteTagID" id="noteTagID" class="form-control">
													<#list noteTags as noteTag>
													<option value="${noteTag.notetagId}">${noteTag.notetagName}</option>
													</#list>
												</select>
											</div>
										</div>
										
										
										<button type="sumbit" class="hvr-outline-in btn btn-info">快速搜索</button>
										<a href="/ui/notePage" class="hvr-outline-in btn btn-success">新建事件</a>
									</form> 
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-widget">
						<div class="grid_3 grid_4">
							<!--
							<h3 class="hdg">事件一览</h3>
							-->
							<div class="bs-example">
								<table class="table">
								  <tbody>
									
									<#list pageModels.records as note>
									<!-- title-->
									<tr>
										<td>
											<h2 id="h2">${note.noteTitle}
											<a class="anchorjs-link" href="#h2.-bootstrap-heading">
												<span class="anchorjs-icon"></span>
											</a>
											
											<a href="/ui/notePage?noteID=${note.noteId?c}" class="btn btn-success">修改</a>
											<a href="/ui/noteToClose?noteID=${note.noteId?c}" class="btn btn-danger">删除</a>
											</h2>
											
										</td>
									  <td class="type-info">${note.createDate}</td>
									</tr>
									
									<!--content-->
									<tr>
									  <td>
										<h5 id="h5.-bootstrap-heading">
											 ${note.noteContent}
									    </h5>
									  </td>
									  
									  <!--
									  <td class="type-info">标签1</td>
									  -->
									</tr>
									</#list>
									
								  </tbody>
								</table>
							</div>
							 
							<#include "../commons/pageModel.ftl">
						</div>	
					</div>
				</div>
				<!--//typography-->
			</div>
		</div>