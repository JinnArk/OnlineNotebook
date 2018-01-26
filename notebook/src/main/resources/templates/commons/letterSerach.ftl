<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		<!--grids-->
		<div class="grids">
			<div class="progressbar-heading grids-heading">
				<h2>站内信</h2>
			</div>
			<div class="compose-grids">
				<div class="col-md-4">
					<div class="panel panel-widget">
						<div class="compose-left">
							<div class="folder widget-shadow">
								<ul>
									<li class="head">信箱</li>
									<li><a href="/commons/letterSerach"><i class="fa fa-inbox"></i>找信</a></li>
									<li><a href="/commons/letterNew"><i class="fa fa fa-envelope-o"></i>写信</a></li>
									<!--
									<li><a href="#"><i class="fa fa fa-envelope-o"></i>写信 <span>03</span></a></li>
									-->
								</ul>
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-md-8">
					<div class="panel panel-widget forms-panel">
						<div class="forms">	
							<div class="form-two widget-shadow">
								<div class="form-title">
									<h4>信件查询</h4>
								</div>
								<div class="form-body" data-example-id="simple-form-inline">
									<form class="form-inline" action="/commons/letterSerach" method="get"> 
										<div class="form-group"> 
											<label for="createDate">接收时间：</label> 
											<input type="text" class="form-control" id="createDate" name="createDate" placeholder="2018-1-25">
										</div>
										
										<button type="sumbit" class="hvr-outline-in btn btn-info">快速搜索</button>
									</form> 
								</div>
							</div>
						</div>
					</div>
				
					<div class="panel panel-widget">
					
						<div class="inbox-page">
							<h4>查询结果</h4>
							
							<#if pageModels??>
							<#list pageModels.records as letter>
							<div class="inbox-row widget-shadow">
								<div class="mail">
									<h6>用户：${letter.username}</h6>
								</div>
								
								<a role="button" data-toggle="collapse" data-parent="#accordion" 
									href="#${letter.letterID?c}" aria-expanded="false" aria-controls="${letter.letterID?c}" class="collapsed">
									<div class="mail">
										<p>标题：${letter.letterTitle}</p>
									</div>
								</a>
								
								<div class="mail-right"><p>${letter.createDate}</p></div>
								<div class="clearfix"> </div>
								<div id="${letter.letterID?c}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour" aria-expanded="false" style="height: 0px;">
									<div class="mail-body">
										<p> ${letter.letterContent}</p>
										
										<#if letter.letterState == 2>
										<form>
											<input type="text" name="reLetterRecipientName" value="${letter.username}" hidden>
											<input type="text" name="reLetterTitle" value="回复:${letter.username},对于:${letter.letterTitle}" hidden>
											<input type="text" name="replyLetterID" value="${letter.letterID?c}" hidden>
											<input type="text" name="reLetterContent" placeholder="需要回复的话" required="">
											
											<input name="reLetterButton" class="replyLetter" type="button" value="发送">
										</form>
										</#if>
									</div>
								</div>
							</div>
							</#list>
							</#if>
						</div>
						
						<#include "../commons/pageModel.ftl">
						
					</div>
					
				</div>
				<div class="clearfix"> </div>
			</div>
				
		</div>
		<!--//grids-->
	</div>
</div>