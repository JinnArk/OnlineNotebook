		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>标签管理</h2>
					</div>
					
					<div class="panel panel-widget">
						<div class="tables">
							<table class="table table-hover mobileTableFont"> 
								<thead> 
									<tr>
										<th>标签名称</th> <th>创建时间</th> <th>最近修改时间</th> <th>标签状态</th> <th>可用操作</th> 
									</tr> 
								</thead> 
								<tbody> 
									<#list pageModels.records as noteTag>
									<tr> 
										<td>${noteTag.notetagName}</td> <td>${noteTag.createDate}</td> <td>${noteTag.modifyDate}</td> 
										<td>
											<#if noteTag.notetagState=1>
											<a href="javascript:;" class="label label-primary">正常</a>
											<#else>
											<a href="javascript:;" class="label label-warning">已删除</a>
											</#if>
										</td> 
										<td>
											<#if noteTag.notetagState=1>
											<a href="/admin/noteTagToClose?noteTagID=${noteTag.notetagId?c}&userID=${noteTag.userId?c}" class="label label-danger">删除标签</a>
											<#else>
											<a href="/admin/noteTagToOpen?noteTagID=${noteTag.notetagId?c}&userID=${noteTag.userId?c}" class="label label-success">恢复标签</a>
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