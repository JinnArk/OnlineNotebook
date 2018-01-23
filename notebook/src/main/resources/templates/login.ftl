		<div class="login-body">
			<div class="login-heading">
				<h1>登录</h1>
			</div>
			<div class="login-info">
				<form action="/sign" method="post">
					<input type="text" class="user" name="username" placeholder="邮箱" required="">
					<input type="password" name="password" class="lock" placeholder="密码">
					<div class="forgot-top-grids">
						<div class="forgot-grid">
							<ul>
								<li>
									<input type="checkbox" id="rememberMe" name="rememberMe">
									<label for="rememberMe"><span></span>记住我</label>
								</li>
							</ul>
						</div>
						<div class="forgot">
							<a href="/password">忘记密码?</a>
						</div>
						<div class="clearfix"> </div>
					</div>
					<input type="submit" name="login" value="登录">
					<div class="signup-text">
						<a href="/register">没有帐号? 马上创建</a>
					</div>
					
				</form>
			</div>
		</div>