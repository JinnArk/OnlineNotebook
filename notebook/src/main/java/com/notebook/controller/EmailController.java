package com.notebook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.notebook.service.UserInfoService;
import com.notebook.util.CommonUtil;
import com.notebook.util.ConstantUtil;
import com.notebook.util.EmailUtil;
import com.notebook.util.PasswordUtil;
import com.notebook.util.StringUtil;

@Controller
@RequestMapping(value="/email")
public class EmailController {
	@Autowired
	JedisPool jedisPool;
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value="/Register",method=RequestMethod.POST)
	@ResponseBody
	public String userRegister(final HttpServletRequest request){
		
		//0. 验证种类
		//1.ip - email - 1200s
		Jedis jedis = null;
		String key = null;
		try{
			
			//2.验证邮箱是否注册过
			String email = request.getParameter("email");
			//2.1若已注册则AJAX回复已注册
			//2.1若未注册则继续进行下一步
				if(userInfoService.getUserInfoByUsername(email)==null){
					String ip = CommonUtil.getIpAddress(request);
					key = ConstantUtil.EMAILTYPEREGISTER+ip+email;//key
					jedis = jedisPool.getResource();//从连接池取出
					//3.0验证是否在20分钟内已发送过验证请求
					if(!StringUtil.isEmpty(jedis.get(key))){
						//3.0.1不为空，则已经发送过
						//AJAX返回提示 同一邮箱只能注册一次，同时20分钟只能发送一次验证码
						return "20分钟内只能发送一次验证码";
					}else{
						//3.0.1为空，则没有发送过
						String value = PasswordUtil.createSalt();//value-随机6位验证码
						int time = 1200;//存活时间 
						//time=60;
						jedis.setex(key, time, value);
						//3.1email
						String title = "NOTEBOOK邮箱注册验证码";
						StringBuffer content = new StringBuffer("尊敬的用户：");
						content.append(email).append("\n您本次的验证码为：").append(value);
						EmailUtil.sentEmail(email, title, content.toString());
						return "验证码已发送";
					}
				}else{
					return "该邮箱已被已注册";
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
		
		return "出现异常";
	}
	
	@RequestMapping(value="/FindPassword",method=RequestMethod.POST)
	@ResponseBody
	public String userFindPassword(final HttpServletRequest request){
		Jedis jedis = null;
		String key = null;
		try{
			String email = request.getParameter("email");
				if(userInfoService.getUserInfoByUsername(email)!=null){
					String ip = CommonUtil.getIpAddress(request);
					key = ConstantUtil.EMAILTYPEFINDPASSWORD+ip+email;//key
					jedis = jedisPool.getResource();
					if(!StringUtil.isEmpty(jedis.get(key))){
						return "20分钟内只能发送一次验证码";
					}else{
						String value = PasswordUtil.createSalt();//value-随机6位验证码
						int time = 1200;//存活时间 
						jedis.setex(key, time, value);
						String title = "NOTEBOOK找回密码验证码";
						StringBuffer content = new StringBuffer("尊敬的用户：");
						content.append(email).append("\n您本次的验证码为：").append(value);
						EmailUtil.sentEmail(email, title, content.toString());
						return "验证码已发送";
					}
				}else{
					return "该邮箱还未被注册";
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
		
		return "出现异常";
	}
}
