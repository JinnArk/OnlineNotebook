<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-widget">
					<div class="panel-title">
					  公告栏
					  <ul class="panel-tools">
						<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
						<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						<li><a class="icon closed-tool"><i class="fa fa-times"></i></a></li>
					  </ul>
					</div>
					<div class="panel-body">
						<!-- candlestick -->
						<div class="candlestick">
							
							<#if notices??>
							<#list notices as notice>
								<p>
								<h3>${notice.noticeTitle}</h3> ${notice.createDate}
								</p>
								<p>
									${notice.noticeContent}
								</p>
								<#if ((notices?size) != (notice_index+1))>
									<hr>
								</#if>
							</#list>
							</#if>
						</div>
						<!-- //candlestick -->
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="panel panel-widget">
					<div class="panel-title">
					  登录记录
					  <ul class="panel-tools">
						<li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
						<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
						<li><a class="icon closed-tool"><i class="fa fa-times"></i></a></li>
					  </ul>
					</div>
					<div class="panel-body">
						<div class="lines-points">
						<#if loginRecords??>
							<#list loginRecords as loginRecord>
								登录IP：${loginRecord.loginIP} 登录时间：${loginRecord.loginDate}
								
								<#if ((loginRecords?size) != (loginRecord_index+1))>
									<hr>
								</#if>
							</#list>
						</#if>	
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
		
	</div>
</div>