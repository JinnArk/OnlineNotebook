<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		<!--grids-->
		<div class="grids">
			<div class="progressbar-heading grids-heading">
				<h2>站内信</h2>
			</div>
			
			<div class="panel panel-widget forms-panel">
				<div class="forms">
					<div class="form-two widget-shadow">
						<div class="form-title">
							<h4>已发信件查询</h4>
						</div>
						<div class="form-body" data-example-id="simple-form-inline">
							<form class="form-inline" action="/commons/letterSended" method="get"> 
								<div class="form-group"> 
									<label for="exampleInputName2">发信时间：</label> 
									<input type="text" class="form-control" id="createDate" name="createDate"
									placeholder="2018-1-25">
								</div>
								
								<button type="sumbit" class="hvr-outline-in btn btn-info">快速搜索</button>
								
							</form> 
						</div>
					</div>
				</div>
			</div>
			
			<div class="panel panel-widget">
				<div class="inbox-page">
					<h4>已发出站内信</h4>
					
					<#list pageModels.records as letter>
					
						<div class="inbox-row widget-shadow" id="accordion" role="tablist" aria-multiselectable="true">
							
							<div class="mail">
								<h6>收信用户：${letter.username} </h6>
							</div>
							
							<a role="button" data-toggle="collapse" data-parent="#accordion" 
								href="#${letter.letterID?c}" aria-expanded="true" aria-controls="${letter.letterID?c}">
								<div class="mail">
									<p>标题：${letter.letterTitle} </p>
								</div>
							</a>
							
							<div class="mail-right">
								<div class="dropdown">
								<p>
									<#if letter.letterState==0>
									未读
									<#elseif letter.letterState==1>
									已读未回复
									<#else>
									已回复
									</#if>
								</p>
								</div> 
							</div>
							
							<div class="mail-right"><p>${letter.createDate}</p></div>
	
							<div class="clearfix"> </div>
							
							<div id="${letter.letterID?c}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
								<div class="mail-body">
									<p>${letter.letterContent}</p>
								</div>
							</div>
						</div>
					</#list>
					
					<#include "../commons/pageModel.ftl">
				</div>
			</div>
			
		</div>
		<!--//grids-->
		

	</div>
</div>