<html>
	<head>
	</head>
	<body>
		这里是， 用户index页面，欢迎你 <@shiro.principal property="username"/>
		
		<@shiro.hasRole name="admin">
		<br>
		<a href="/ui/userAdd">这里进入用户添加页</a>
		</@shiro.hasRole>
		
		<@shiro.hasRole name="admin">
		<br>
		<a href="/ui/userUpdate">这里进入用户更新页</a>
		</@shiro.hasRole>
		
		<@shiro.hasRole name="admin">
		<br>
		<a href="/ui/userDel">这里进入用户删除页</a>
		</@shiro.hasRole>
		
		<br>
		<a href="/logout">Logout</a>
	</body>
</html>