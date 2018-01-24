package com.notebook.config.mail;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.notebook.config.email.EmailConfig;
import com.notebook.util.EmailUtil;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class EmailTest {
	
//	@Test
//	public void sendMail(){
//		try {
//			EmailUtil.emailUtilSet("smtp.exmail.qq.com", "2ing@2inger.top", "Qq7788990");
//			
//			EmailUtil.sentEmail("252631706@qq.com", "关于2ing对于我司产品的投诉", "投诉结果：无效，详情请见，www.xxx.com/wtf");
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}  catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	@Test
	public void connectMail(){
		try {
//			System.out.println(EmailConfig.getHost());
//			System.out.println(EmailConfig.getUsername());
//			System.out.println(EmailConfig.getPassword());
			EmailUtil.emailUtilSet(EmailConfig.getHost(), EmailConfig.getUsername(), EmailConfig.getPassword());
			//EmailUtil.emailUtilSet("smtp.exmail.qq.com", "2ing@2inger.top", "Qq7788990");
			EmailUtil.connectTest();
			System.out.println("连接成功");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
		}
	}
}