<!--left-fixed -navigation-->
<div class="sidebar" role="navigation">
    <div class="navbar-collapse">
		<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right dev-page-sidebar mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar" id="cbp-spmenu-s1">
			<div class="scrollbar scrollbar1">
				<!-- 右侧导航 -->
				<ul class="nav" id="side-menu">
					<li>
						<a href="/ui/index"><i class="fa fa-home nav_icon"></i>控制台</a>
					</li>
					<li>
						<a href="javascript:;">
							<i class="fa fa-cogs nav_icon"></i>
							设置
							<span class="fa arrow">
							</span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li>
								<a href="/commons/password">修改密码</a>
							</li>
						</ul>
						<!-- /nav-second-level -->
					</li>
					
					<li>
						<a href="/ui/notes"><i class="fa fa-book nav_icon"></i>
							事件管理
						</a>
					</li>
					
					<li>
						<a href="/ui/noteTags"><i class="fa fa-book nav_icon"></i>
							标签管理 
						</a>
					</li>
					
					<#include "../commons/rightNav_letter.ftl">
					
					<li>
						<a href="/logout"><i class="fa fa-location-arrow nav_icon"></i>
						登出
						</a>
					</li>
					
				</ul>
			</div>
			<!-- //sidebar-collapse -->
		</nav>
	</div>
</div>
<!--left-fixed -navigation-->