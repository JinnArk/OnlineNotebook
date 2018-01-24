
<div class="grid_3 grid_5">
	<div class="col-md-6 page_1">
		<nav>
		<!--pageModel原则 1.是否只有一页 2.是否为第一页/最后一页/其他 3.是否为当前页-->
			
				<#if notices.pages==1 || notices.pages==0>
					<ul class="pagination">
						<li class="disabled"><a href="#" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
						<li class="disabled"><a href="#" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
					</ul>
				<#else>
					<ul class="pagination">
					<#if notices.current == 1>
						<li class="disabled"><a href="#" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						
						<#list 1..notices.pages as num>
							<#if num == notices.current>
								<li class="active"><a href="#">${notices.current}<span class="sr-only">(current)</span></a></li>
							<#else>
								<#if !pageModelParam??>
									<li><a href="${pageModelUrl}?pagenow=${num}">${num}</a></li>
								<#else>
									<li><a href="${pageModelUrl}?pagenow=${num}&${pageModelParam}">${num}</a></li>
								</#if>
							</#if>
						</#list>
						
						<#if !pageModelParam??>
							<li><a href="${pageModelUrl}?pagenow=${notices.current+1}" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
						<#else>
							<li><a href="${pageModelUrl}?pagenow=${notices.current+1}&${pageModelParam}" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
						</#if>
						
					<#elseif notices.current == notices.pages>
					
						<#if !pageModelParam??>
							<li><a href="${pageModelUrl}?pagenow=${notices.current-1}" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						<#else>
							<li><a href="${pageModelUrl}?pagenow=${notices.current-1}&${pageModelParam}" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						</#if>
						
						<#list 1..notices.pages as num>
							<#if num == notices.current>
								<li class="active"><a href="#">${notices.current}<span class="sr-only">(current)</span></a></li>
							<#else>
								<#if !pageModelParam??>
									<li><a href="${pageModelUrl}?pagenow=${num}">${num}</a></li>
								<#else>
									<li><a href="${pageModelUrl}?pagenow=${num}&${pageModelParam}">${num}</a></li>
								</#if>
							</#if>
						</#list>
						<li class="disabled"><a href="#" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
						
					<#else>
						<#if !pageModelParam??>
							<li><a href="${pageModelUrl}?pagenow=${notices.current-1}" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						<#else>
							<li><a href="${pageModelUrl}?pagenow=${notices.current-1}&${pageModelParam}" aria-label="Previous"><i class="fa fa-angle-left"></i></a></li>
						</#if>
						
						<#list 1..notices.pages as num>
							<#if num == notices.current>
								<li class="active"><a href="#">${notices.current}<span class="sr-only">(current)</span></a></li>
							<#else>
								<#if !pageModelParam??>
									<li><a href="${pageModelUrl}?pagenow=${num}">${num}</a></li>
								<#else>
									<li><a href="${pageModelUrl}?pagenow=${num}&${pageModelParam}">${num}</a></li>
								</#if>
							</#if>
						</#list>
						
						<#if !pageModelParam??>
							<li><a href="${pageModelUrl}?pagenow=${notices.current+1}" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
						<#else>
							<li><a href="${pageModelUrl}?pagenow=${notices.current+1}&${pageModelParam}" aria-label="Next"><i class="fa fa-angle-right"></i></a></li>
						</#if>
					</#if>
					</ul>
				</#if>

	   </nav>
	</div>
 	<div class="clearfix"> </div>
</div>