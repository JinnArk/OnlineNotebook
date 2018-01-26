		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<!--typography-->
				<div class="typography">
					<div class="typo-heading">
						<h2>用户管理</h2>
					</div>
					
					<div class="panel panel-widget forms-panel">
						<div class="forms">
							<div class="form-two widget-shadow">
								<div class="form-title">
									<h4>用户查询</h4>
								</div>
								<div class="form-body" data-example-id="simple-form-inline">
									<form class="form-inline" action="/admin/userInfo" method="get"> 
										
										<div class="form-group">
											<label for="exampleInputEmail2">用户账号：</label> 
											<input class="form-control" 
												id="username" name="username" placeholder="mail.abc@example.com">
										</div>
										
										<button type="sumbit" class="hvr-outline-in btn btn-info">快速搜索</button>
									</form> 
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-widget">
						<div class="tables">
							<table class="table table-hover mobileTableFont"> 
								<thead> 
									<tr>
										<th>用户账号</th> <th>创建时间</th> <th>最近登录时间</th> <th>可用操作</th> <th>相关信息</th>
									</tr> 
								</thead> 
								<tbody>
									<#list pageModels.records as userInfo>
									<tr> 
										<td>${userInfo.username}</td> <td>${userInfo.createDate}</td> <td>${userInfo.loginDate}</td>
										<td>
											<#if userInfo.state==0>
											<a href="/admin/userToOpen?userID=${userInfo.userId?c}" class="label label-success">允许登录</a>
											<#else>
											<a href="/admin/userToClose?userID=${userInfo.userId?c}" class="label label-danger">禁止登录</a>
											</#if>
										</td> 
										<td>
											<a href="/admin/noteTags?userID=${userInfo.userId?c}" class="label label-primary">标签</a>
											<a href="/admin/notes?userID=${userInfo.userId?c}" class="label label-warning">事件</a>
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