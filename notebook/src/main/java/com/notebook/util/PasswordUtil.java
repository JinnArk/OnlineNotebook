package com.notebook.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {
	private PasswordUtil() {}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks 生成随机6位字符串(盐)
	 */ 
	public static String createSalt() {
		StringBuffer salt = new StringBuffer();
		for(int i=0;i<6;i++){
			int intVal=(int)(Math.random()*26+97);
			//salt=salt+(char)intVal;
			salt.append(intVal);
		}
		return salt.toString();
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks 生成密码
	 */
	public static String encryptPassword(final String password,final String newsalt) {
		String hashAlgorithmName = "MD5";//加密算法
		Object credentials = password;//所需加密内容
		Object salt = ByteSource.Util.bytes(newsalt);//盐值
		int hashIterations = 32;//加密次数
		
		return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
		
	}
	
}
