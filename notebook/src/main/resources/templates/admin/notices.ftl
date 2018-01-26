		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>公告管理</h2>
					</div>
					
					<div class="panel panel-widget forms-panel">
						<div class="forms">
							<div class="form-two widget-shadow">
								<div class="form-title">
									<h4>公告查询</h4>
								</div>
								<div class="form-body" data-example-id="simple-form-inline">
									<form class="form-inline" action="/admin/notices" method="get"> 
										
										<div class="form-group">
											<label for="exampleInputEmail2">公告名称：</label> 
											<input type="notice" class="form-control" id="noticeTitle" name="noticeTitle" placeholder="×××">
										</div>
										
										<button type="submit" value="Submit" class="hvr-outline-in btn btn-info" >快速搜索</button>
										<a href="/admin/notice" name="noticeCreateButton" class="hvr-outline-in btn btn-success">新建公告</a>
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
										<th>公告名称</th> <th>创建时间</th> <th>可用操作</th> 
									</tr> 
								</thead> 
								<tbody> 
									<#list pageModels.records as notice>
									<tr> 
										<td>${notice.noticeTitle}</td> <td>${notice.createDate}</td> 
										<td>
											<a href="/admin/notice?noticeId=${notice.noticeID?c}" class="label label-success">修改公告</a>
											<a href="/admin/noticeDelete?noticeId=${notice.noticeID?c}" class="label label-danger">删除公告</a>
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