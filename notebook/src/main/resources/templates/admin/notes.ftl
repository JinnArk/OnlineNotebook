<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>事件管理</h2>
					</div>
					
					<div class="panel panel-widget">
						<div class="tables">
							<table class="table table-hover mobileTableFont"> 
								<thead> 
									<tr>
										<th>标签名称</th> <th>创建时间</th> <th>最近修改时间</th> <th>事件状态</th> <th>可用操作</th> 
									</tr> 
								</thead> 
								<tbody> 
									<#list pageModels.records as note>
									<tr> 
										<td>${note.noteTitle}</td> <td>${note.createDate}</td> <td>${note.modifyDate}</td> 
										<td>
											<#if note.noteState=1>
											<a href="javascript:;" class="label label-primary">正常</a>
											<#else>
											<a href="javascript:;" class="label label-warning">已删除</a>
											</#if>
										</td> 
										<td>
											<#if note.noteState=1>
											<a href="/admin/noteToClose?noteID=${note.noteId?c}&userID=${note.userId?c}" class="label label-danger">删除事件</a>
											<#else>
											<a href="/admin/noteToOpen?noteID=${note.noteId?c}&userID=${note.userId?c}" class="label label-success">恢复事件</a>
											</#if>
										</td> 
									</tr> 
									</#list>
								</tbody> 
							</table>
						</div>
						
						<#include "../commons/pageModel.ftl">
						<div class="form-group hidden_button">
							<a href="/admin/userInfo" type="button" class="btn btn-info pull-right">返回</a> 
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
				<!--//typography-->
			</div>
		</div>