package com.notebook.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public final class EmailUtil{
	
	//邮箱属性
	static private Properties prop = new Properties();
	//邮件会话
	static private Session session;
	
	static private String host;//邮箱服务器
	static private String username;//发送者邮箱
	static private String password;//邮箱密码
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月21日
	 * @remarks 用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication
	 */
    static class MyAuthenricator extends Authenticator {
        String u = null;
        String p = null;
        public MyAuthenricator(String u, String p) {
            this.u = u;
            this.p = p;
        }
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(u, p);
        }
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月21日
     * @remarks 设置邮箱属性
     */
    public static void emailUtilSet(String newHost, String newUsername, String newPassword) throws Exception{
    	host = newHost;
    	username = newUsername;
    	password = newPassword;
    	
    	emailUtilInit();//设置之后进行初始化
    }
    
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月21日
     * @remarks 初始化邮箱属性
     */
    public static void emailUtilInit() throws Exception{
	   	 
    	 //测试用协议
	     prop.setProperty("mail.store.protocol", "pop3");
    	
	     //协议
	     prop.setProperty("mail.transport.protocol", "smtp");
	     //服务器
	     prop.setProperty("mail.smtp.host", host);
	     //端口
	     prop.setProperty("mail.smtp.port", "465");
	     //使用smtp身份验证
	     prop.setProperty("mail.smtp.auth", "true");
	     
	     //使用SSL，企业邮箱必需！
	     //开启安全协议
		 MailSSLSocketFactory sf = new MailSSLSocketFactory();
         sf.setTrustAllHosts(true);

	     prop.put("mail.smtp.ssl.enable", "true");
	     prop.put("mail.smtp.ssl.socketFactory", sf);
	     
    	 session = Session.getDefaultInstance(prop, new MyAuthenricator(username, password));
	     //session.setDebug(true);//打印debug信息
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月21日
     * @remarks 邮箱服务器连接测试
     */
    public static void connectTest() throws Exception{
    	Store store = session.getStore();//获取测试实体
    	store.connect(host,username,password);//若抛出异常则连接失败
    	store.close();//关闭测试连接
    }
    
    /**
     * 
     * @author 2ing
     * @createTime 2018年1月21日
     * @remarks 发送邮件
     */
    public static void sentEmail(String tomail, String mailTitle, String mailContent) throws Exception{
    	
		MimeMessage mimeMessage = new MimeMessage(session);   
		mimeMessage.setFrom(new InternetAddress(username));

		mimeMessage.setSentDate(new Date());
		
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));//收件者邮箱
		mimeMessage.setSubject(mailTitle);//邮件名称
		mimeMessage.setText(mailContent);//邮件内容
		
		mimeMessage.saveChanges();
		Transport.send(mimeMessage);
    }

}
