<!-- main content start-->
<div id="page-wrapper">
	<div class="main-page">
		
		<div class="row four-grids">
			<div class="col-md-3 ticket-grid">
				<div class="tickets">
					<div class="grid-left">
						<div class="book-icon">
							<i class="fa fa-book"></i>
						</div>
					</div>
					<div class="grid-right">
						<h3>新增事件数<span>New Event</span></h3>
						<p>${adminIndexModel.newEventNUM}</p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-3 ticket-grid">
				<div class="tickets">
					<div class="grid-left">
						<div class="book-icon">
							<i class="fa fa-rocket"></i>
						</div>
					</div>
					<div class="grid-right">
						<h3>活跃用户数 <span>Active User</span></h3>
						<p>${adminIndexModel.activeUserNUM}</p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-3 ticket-grid">
				<div class="tickets">
					<div class="grid-left">
						<div class="book-icon">
							<i class="fa fa-bar-chart"></i>
						</div>
					</div>
					<div class="grid-right">
						<h3>新增用户数 <span>New User</span></h3>
						<p>${adminIndexModel.newUserNUM}</p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-3 ticket-grid">
				<div class="tickets">
					<div class="grid-left">
						<div class="book-icon">
							<i class="fa fa-user"></i>
						</div>
					</div>
					<div class="grid-right">
						<h3>用户总数 <span>All User</span></h3>
						<p>${adminIndexModel.allUserNUM}</p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
		
		
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
							<#list loginRecords as loginRecord>
								登录IP：${loginRecord.loginIP} 登录时间：${loginRecord.loginDate}
								
								<#if ((loginRecords?size) != (loginRecord_index+1))>
									<hr>
								</#if>
							</#list>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
		
	</div>
</div>