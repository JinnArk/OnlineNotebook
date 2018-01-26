<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		<!--grids-->
		<div class="grids">
			<div class="progressbar-heading grids-heading">
				<h2>站内信</h2>
			</div>
			<div class="panel panel-widget">
				<div class="inbox-page">
					<h4>未读站内信</h4>
					
					<div class="inbox-row widget-shadow" >
						
						<!--全选-->
						<div class="mail">
							<input name="allLetterCheck" type="checkbox" class="checkbox">
						</div>
						
						<div class="mail-right">
							<button name="readlettersButton" type="button" class="btn btn-sm btn-info">标记已读</button>
						</div>
						
						<div class="clearfix"> </div>
					</div>
					
					<#list pageModels.records as letter>
					
						<div class="inbox-row widget-shadow" id="accordion" role="tablist" aria-multiselectable="true">
							<div class="mail "> 
								<input name="letterbox-${letter.letterID?c}" value="${letter.letterID?c}"  type="checkbox" class="checkbox"> 
							</div>
							<div class="mail">
								<h6>用户：${letter.username} </h6>
							</div>
							
							<a role="button" data-toggle="collapse" data-parent="#accordion" 
								href="#${letter.letterID?c}" aria-expanded="true" aria-controls="${letter.letterID?c}">
								<div class="mail">
									<p>标题：${letter.letterTitle} </p>
								</div>
							</a>
							
							<div class="mail-right">
								<div class="dropdown">
									<a href="#" data-toggle="dropdown" aria-expanded="false">
										<p><i class="fa fa-ellipsis-v mail-icon"></i></p>
									</a>
									<ul class="dropdown-menu float-right">
										<li>
											<a value="" href="/commons/readLetter?letterID=${letter.letterID?c}">
												<i class="fa fa-download mail-icon"></i>
												已读
											</a>
										</li>
									</ul>
								</div> 
							</div>
							
							<div class="mail-right"><p>${letter.createDate}</p></div>
	
							<div class="clearfix"> </div>
							
							<div id="${letter.letterID?c}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
								<div class="mail-body">
									<p>${letter.letterContent}</p>
									
									<form>
										<input type="text" name="reLetterRecipientName" value="${letter.username}" hidden>
										<input type="text" name="reLetterTitle" value="回复:${letter.username},对于:${letter.letterTitle}" hidden>
										<input type="text" name="replyLetterID" value="${letter.letterID?c}" hidden>
										<input type="text" name="reLetterContent" placeholder="需要回复的话" required="">
										
										<input name="reLetterButton" class="replyLetter" type="button" value="发送">
									</form>
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