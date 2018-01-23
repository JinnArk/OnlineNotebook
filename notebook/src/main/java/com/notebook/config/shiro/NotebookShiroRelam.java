package com.notebook.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.notebook.entities.SysPermission;
import com.notebook.entities.SysRole;
import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;

public class NotebookShiroRelam extends AuthorizingRealm{
	
	@Autowired
	UserInfoService userInfoService;

	/**
     * 认证信息.(身份验证):
     * Authentication 是用来验证用户身份
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//System.out.println("Shiro验证配置:doGetAuthenticationInfo()===>已启用");
		
		//获取用户输入的：账户名称
		String username = (String)token.getPrincipal();
		
//		System.out.println(username);
//		System.out.println(token.getCredentials());
		
		UserInfo userInfo = userInfoService.getUserInfoByUsername(username);
		
//		System.out.println("====================用户信息======================");
//		
//		System.out.println(userInfo.getUserId());
//		System.out.println(userInfo.getUuid());
//		System.out.println(userInfo.getUsername());
//		System.out.println(userInfo.getPassword());
//		System.out.println(userInfo.getNickname());
//		System.out.println(userInfo.getSalt());
//		System.out.println(userInfo.getState());
//		System.out.println(userInfo.getCreateDate());
//		System.out.println(userInfo.getLoginDate());
//		
//		System.out.println("====================角色信息======================");
//		for(SysRole r1:userInfo.getRoles()){
//			System.out.println(r1.getRoleId());
//			System.out.println(r1.getRole());
//			System.out.println(r1.getRoleState());
//			System.out.println(r1.getDescription());
//		}
//		System.out.println("====================权限信息=======================");
//		for(SysRole r1:userInfo.getRoles()){
//			for(SysPermission p1:r1.getPermissions()){
//				System.out.println(p1.getPermissionId());
//				System.out.println(p1.getPermissionName());
//				System.out.println(p1.getPermissionStr());
//				System.out.println(p1.getResourceType());
//				System.out.println(p1.getResourceUrl());
//			}
//		}
//		System.out.println("==================================================");
		
		//判断用户是否存在
		if(userInfo == null){
			return null;
		}
		
		//无盐加密密码验证
//		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//				userInfo,
//				userInfo.getPassword(),
//				getName()
//				);
		
		/**
		 * 重点注释：
		 * 因为本系统采用freemarker作为模版框架，而在此框架中shiro标签不能直接使用，所以需要引入第三方包
		 * 而在第三方包中，shiro的参数获取方式有所不同。
		 * 如下方的构造方法初始化的，第一个参数userInfo，在ftl中会对应<@shiro:principal />标签，
		 * 而如果想显示为username属性，则需要修改为 <@shiro:pricipal preperty="username">。
		 */
		//盐加密密码验证
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo,
				userInfo.getPassword(),
				ByteSource.Util.bytes(userInfo.getSalt()),
				getName()
				);
		
		return authenticationInfo;
	}
	
    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * 
     * @param principals
     * 
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pribcipals){
		//System.out.println("Shiro权限配置:doGetAuthorizationInfo()===>已启用");
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo)pribcipals.getPrimaryPrincipal();
		
		//从缓存中获取
		//UserInfo userInfoRolesAndPermission = userInfoService.getUserInfoByUsername(userInfo.getUsername());
		
		for(SysRole role:userInfo.getRoles()){
			authorizationInfo.addRole(role.getRole());
			for(SysPermission permission: role.getPermissions()){
				authorizationInfo.addStringPermission(permission.getPermissionStr());
			}
		}
//		System.out.println("======================已授予角色=========================");
//		for(String role:authorizationInfo.getRoles()){
//			System.out.println("=> "+role);
//		}
//		System.out.println("======================已授予权限=========================");
//		for(String permission:authorizationInfo.getStringPermissions()){
//			System.out.println("=> "+permission);
//		}
		
		return authorizationInfo;
	}
	
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月13日
	 * @remarks 手动生成加密之后的密码，算法+盐+重复加密
	 */
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";//加密算法
		Object credentials = "123456";//所需加密内容
		Object salt = ByteSource.Util.bytes("nosalt");//盐值
		int hashIterations = 32;//加密次数
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
