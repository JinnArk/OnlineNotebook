package com.notebook.config.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "com.notebook.util.email")
@PropertySource("classpath:emailConfig.properties")

/**
 * 
 * @author 2ing
 * @createTime 2018年1月24日
 * @remarks 从properties文件中读取配置
 */
public class EmailConfig {
    private static String host;
    private static String username;
    private static String password;
    
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		EmailConfig.host = host;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		EmailConfig.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		EmailConfig.password = password;
	}
}
