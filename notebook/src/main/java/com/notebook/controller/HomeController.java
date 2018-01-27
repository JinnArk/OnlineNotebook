package com.notebook.controller;

import java.util.Date;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;
import com.notebook.util.CommonUtil;
import com.notebook.util.ConstantUtil;
import com.notebook.util.DateUtil;
import com.notebook.util.EmailUtil;
import com.notebook.util.PasswordUtil;
import com.notebook.util.StringUtil;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月12日
 * @remarks 用户进入系统的Controller
 */

@Controller
public class HomeController {
	@Autowired
	JedisPool jedisPool;
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月14日
	 * @remarks 在shiro中设置的未授权页面
	 */
	@RequestMapping(value={"/unauthorized"},method=RequestMethod.GET)
	public ModelAndView unauthorized(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView(ConstantUtil.UNAUTHORIZED);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入游客首页(未登录)
	 */
	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	public ModelAndView index(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LOGIN);
		return new ModelAndView(ConstantUtil.MAIN);
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入注册页面
	 */
	@RequestMapping(value={"/register"},method=RequestMethod.GET)
	public ModelAndView register(final Model model, final HttpServletRequest request, HttpServletResponse response){

		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.REGISTER);
		return new ModelAndView(ConstantUtil.MAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-27
	 * @remarks 注册新用户
	 */
	@RequestMapping(value={"/registerUser"},method=RequestMethod.POST)
	@ResponseBody
	public String registerUser(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		Jedis jedis = null;
		String key = null;
		
		int backState=0;
		int midState=0;
		try{
			jedis = jedisPool.getResource();
			//4.进行验证
			String ip = CommonUtil.getIpAddress(request);
			String email = request.getParameter("email");
			key = ConstantUtil.EMAILTYPEREGISTER+ip+email;
			String value = jedis.get(key);
			
			//5.首先判断，验证码存不存在(是否发送过验证邮件)
			if(!StringUtil.isEmpty(value)){
				
				String verificationCode = request.getParameter("newemailVer");
				//5.1然后判断，验证吗是否正确
				if(verificationCode.equals(value)){
					//验证成功
					//新建UserInfo,自动初始化密码 
					String password = PasswordUtil.createSalt();
					String salt = PasswordUtil.createSalt();
					String encryptPassword = PasswordUtil.encryptPassword(password, salt);
					
					//调用注册
					userInfoService.saveUserInfo(null, email, encryptPassword, "user", salt, 1, 0);
					midState=1;
					
					//同时再发送邮件提示密码
					StringBuffer title = new StringBuffer("用户：");
					title.append(email).append("关于NOTEBOOK的初始密码");
					StringBuffer content = new StringBuffer("您的初始密码为：");
					content.append(password).append("，请妥善保管");
					EmailUtil.sentEmail(email, title.toString(), content.toString());
					
					jedis.del(key);//删除redis中的数据
					backState=3;
					//return "注册成功";
				}else{
					//验证失败
					//AJAX提示用户输入错误
					backState=1;
					//return "验证码错误";
				}
			}else{
				//尚未-发送/创建-验证码
				backState=0;
				//return "该邮箱尚未申请验证";
			}
		}catch (Exception e){
			if(jedis!=null){
				jedis.del(key);//出现异常需要回删已存数据
			}
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.close();
			}	
		}
		
		if(backState==0){
			return "该邮箱尚未申请验证 ";
		}else if(backState==1){
			return "验证码错误";
		}else if(backState==3){
			return "注册成功，同时发送提示邮件成功";
		}else if(backState==4){
			if(midState!=0){
				return "注册成功，但是发送提示邮件失败，同时出现异常";
			}else{
				return "出现异常";
			}
		}else{
			return "出现未知错误";
		}
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入登陆页面(未登录)
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(final Model model, final HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LOGIN);
		return new ModelAndView(ConstantUtil.MAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入找回密码页面
	 */
	@RequestMapping(value={"/password"},method=RequestMethod.GET)
	public ModelAndView password(final Model model, final HttpServletRequest request, HttpServletResponse response){

		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.PASSWORD);
		return new ModelAndView(ConstantUtil.MAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-27
	 * @remarks 通过邮箱验证修改密码
	 */
	@RequestMapping(value={"/repassword"},method=RequestMethod.POST)
	@ResponseBody
	public String repassword(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		Jedis jedis = null;
		String key = null;
		//0.该邮箱尚未申请验证修改密码 1.验证码错误 
		//3.用户修改密码成功，同时发送提示邮件成功
		//4.出现异常
		int backState=0;
		int midState=0;//1.用户修改密码成功 0.用户修改密码失败
		
		try{
			jedis = jedisPool.getResource();
			String ip = CommonUtil.getIpAddress(request);
			String email = request.getParameter("email");
			key = ConstantUtil.EMAILTYPEFINDPASSWORD+ip+email;
			String value = jedis.get(key);
			
			if(!StringUtil.isEmpty(value)){
				
				String verificationCode = request.getParameter("findpasswordVer");
				if(verificationCode.equals(value)){
					//验证成功
					//使用新的密码
					String password = request.getParameter("newpassword");
					String salt = PasswordUtil.createSalt();
					String encryptPassword = PasswordUtil.encryptPassword(password, salt);
					
					//根据username获取用户
					UserInfo user = userInfoService.getUserInfoByUsername(email);
					user.setSalt(salt);
					user.setPassword(encryptPassword);
					//调用修改密码
					userInfoService.saveUserInfo(user);
					
					midState=1;
					
					//同时再发送邮件提示用户修改了密码
					StringBuffer title = new StringBuffer("用户：");
					title.append(email).append("关于NOTEBOOK的修改密码");
					StringBuffer content = new StringBuffer("您与：\n");
					content.append(DateUtil.dateToString(new Date())).append("\n修改了密码");
					EmailUtil.sentEmail(email, title.toString(), content.toString());
					
					jedis.del(key);//删除redis中的数据
					backState=3;
					//return "修改成功";
				}else{
					//验证失败
					//AJAX提示用户输入错误
					backState=1;
					//return "验证码错误";
				}
			}else{
				//尚未-发送/创建-验证码
				backState=0;
				//return "该邮箱尚未申请验证修改密码";
			}
		}catch (Exception e){
			backState=4;
			if(jedis!=null){
				jedis.del(key);//出现异常需要回删已存数据
			}
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.close();
			}	
		}
		
		if(backState==0){
			return "该邮箱尚未申请验证修改密码 ";
		}else if(backState==1){
			return "验证码错误";
		}else if(backState==3){
			return "用户修改密码成功，同时发送提示邮件成功";
		}else if(backState==4){
			if(midState!=0){
				return "用户修改密码成功，但是发送提示邮件失败，同时出现异常";
			}else{
				return "出现异常";
			}
		}else{
			return "出现未知错误";
		}
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 进入修改密码页面
	 */
	@RequestMapping(value={"/newpassword"},method=RequestMethod.GET)
	public ModelAndView newpassword(final Model model, final HttpServletRequest request, HttpServletResponse response){

		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NEWPASSWORD);		
		return new ModelAndView(ConstantUtil.MAIN);
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
            
            //进入-管理员/用户-页面
            if(username.equals("admin")){
            	return new ModelAndView(ConstantUtil.TOADMININDEX);
            }else{
            	return new ModelAndView(ConstantUtil.TOUSERINDEX);
            }
            
		}else{
			token.clear();
			return new ModelAndView(ConstantUtil.TOLOGIN);
		}
		
		
	}
}
