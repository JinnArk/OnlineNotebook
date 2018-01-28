		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>标签管理</h2>
					</div>
					
					<div class="panel panel-widget forms-panel">
						<div class="forms">
							<div class="form-two widget-shadow">
								<div class="form-title">
									<h4>标签查询</h4>
								</div>
								<div class="form-body" data-example-id="simple-form-inline">
									<form class="form-inline" action="/ui/noteTags" method="get"> 
										<div class="form-group"> 
											<label for="exampleInputName2">记录时间：</label> 
											<input type="text" class="form-control" id="createDate" name="createDate"
											placeholder="2018-1-28">
										</div>
										
										<button type="sumbit" class="hvr-outline-in btn btn-info">快速搜索</button>
										<a href="/ui/noteTagPage" class="hvr-outline-in btn btn-success">新建标签</a>
									</form> 
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-widget">
						<div class="tables">
							<table class="table table-hover"> 
								<thead> 
									<tr>
										<th>标签名称</th> <th>创建时间</th> <th>最近修改时间</th> <th>可用操作</th> 
									</tr> 
								</thead> 
								<tbody> 
									<#list pageModels.records as noteTag>
									<tr> 
										<td>${noteTag.notetagName}</td> <td>${noteTag.createDate}</td> <td>${noteTag.modifyDate}</td> 
										<td>
											<a href="/ui/noteTagPage?noteTagID=${noteTag.notetagId?c}" class="label label-warning">修改</a>
											<a href="/ui/noteTagToClose?noteTagID=${noteTag.notetagId?c}" class="label label-danger">删除</a>
										</td> 
									</tr> 
									</#list>
								</tbody> 
							</table>
						</div>
						
						<#include "../commons/pageModel.ftl">
					</div>
				</div>
				<!--//typography-->
			</div>
		</div>