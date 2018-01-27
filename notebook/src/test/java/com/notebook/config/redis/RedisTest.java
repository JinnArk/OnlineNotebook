package com.notebook.config.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.notebook.util.EmailUtil;
import com.notebook.util.PasswordUtil;
import com.notebook.util.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	JedisPool jedisPool;
	
   
	@Test
	public void redisTest(){
		Jedis jedis = null;
		
		//0 确认是注册还是找回密码

		//1.ip - email - 1200s
		String ip = "0.0.0.1";
		String email = "252631706@qq.com";
		String key = ip+email;//key
		String value = PasswordUtil.createSalt();//value-随机6位验证码
		int time = 1200;//存活时间 1200s//20分钟
		try{
			
			//2.验证邮箱是否注册过
			//2.1若已注册则AJAX回复已注册
			//2.1若未注册则继续进行下一步
			
			jedis = jedisPool.getResource();

			//3.0验证是否在20分钟内已发送过验证请求
			if(!StringUtil.isEmpty(jedis.get(key))){
				//3.0.1不为空，则已经发送过
				//AJAX返回提示 同一邮箱只能注册一次，同时20分钟只能发送一次验证码
			}else{
				//3.0.1为空，则没有发送过
				jedis.setex(key, time, value);
				
				//3.1email
				String title = "NOTEBOOK邮箱注册验证码";
				StringBuffer content = new StringBuffer("尊敬的用户：");
				content.append(email).append("\n您本次的验证码为：").append(value);
				EmailUtil.sentEmail(email, title, content.toString());
			}
			//===============================//
			
			//4.进行验证
			String ip2 = "";
			String email2 = "";
			String key2 = ip2+email2;
			String verificationCode = "";
			String value2 = jedis.get(key2);
			
			//5.首先判断，验证码存不存在(是否发送过验证邮件)
			if(!StringUtil.isEmpty(value2)){
				//5.1然后判断，验证吗是否正确
				if(verificationCode.equals(value2)){
					//验证成功
					//新建UserInfo,自动初始化密码 
					String password = "";
					//同时再发送邮件提示密码
					StringBuffer title2 = new StringBuffer("用户：");
					title2.append(email2).append("关于NOTEBOOK的初始密码");
					StringBuffer content2 = new StringBuffer("您的初始密码为：");
					title2.append(password).append("，请妥善保管");
					EmailUtil.sentEmail(email2, title2.toString(), content2.toString());
				}else{
					//验证失败
					//AJAX提示用户输入错误
				}
			}else{
				//尚未-发送/创建-验证码
			}

			
		}catch(Exception e){
			jedis.del(key);//出现异常需要回删已存数据
			e.printStackTrace();
		}finally{
			jedis.close();//关闭连接
		}
	}
}
