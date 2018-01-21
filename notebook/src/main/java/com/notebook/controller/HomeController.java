package com.notebook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月12日
 * @remarks 用户进入系统的Controller
 */

@Controller
public class HomeController {
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月14日
	 * @remarks 在shiro中设置的未授权页面
	 */
	@RequestMapping(value={"/unauthorized"},method=RequestMethod.GET)
	public ModelAndView unauthorized(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("403");
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入游客首页(未登录)
	 */
	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	public ModelAndView index(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("index");
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入注册页面
	 */
	@RequestMapping(value={"/register"},method=RequestMethod.GET)
	public ModelAndView register(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("register");
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入登陆页面(未登录)
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(final Model model, final HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return new ModelAndView("login");
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入找回密码页面
	 */
	@RequestMapping(value={"/password"},method=RequestMethod.GET)
	public ModelAndView password(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("password");
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入修改密码页面
	 */
	@RequestMapping(value={"/newpassword"},method=RequestMethod.GET)
	public ModelAndView newpassword(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("newpassword");
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进行登陆，验证交给shiro
	 */
	@RequestMapping(value="/sign",method=RequestMethod.POST)
	public ModelAndView sign(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//System.out.println("===================>>>进入登陆方法:sign()");
		
		//从request中获取 登录信息
		//帐号名称
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//System.out.println("username: "+username+", password: "+password);
		
		//生成令牌进行验证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		// rememberme判断-设置
		if(request.getParameter("rememberMe")!=null){
			//System.out.println("rememberMe is not null");
		    token.setRememberMe(true);
		}
		
		//获取授权对象
		Subject currentUser = SecurityUtils.getSubject();
		
		//进行登录验证
		try{
			currentUser.login(token);
		}catch(UnknownAccountException uae){//未知用户
			System.out.println("===============>未知用户");
			uae.printStackTrace();
		}catch(IncorrectCredentialsException ice){//密码错误
			ice.printStackTrace();
			System.out.println("===============>密码错误");
		}catch(LockedAccountException lae){//账号已锁定
			lae.printStackTrace();
			System.out.println("===============>账号已锁定");
		}catch(ExcessiveAttemptsException eae){//用户名或密码错误次数过多
			eae.printStackTrace();
			System.out.println("===============>用户名或密码错误次数过多");
		}catch(AuthenticationException lae){//用户名或密码错误
			lae.printStackTrace();
			System.out.println("===============>用户名或密码错误");
		}
		
		//是否验证成功
		if(currentUser.isAuthenticated()){
			//request.getSession().setAttribute(username, request.getRequestedSessionId());
            return new ModelAndView("redirect:/ui/index");
		}else{
			token.clear();
			return new ModelAndView("redirect:/login");
		}
		
		
	}
}
