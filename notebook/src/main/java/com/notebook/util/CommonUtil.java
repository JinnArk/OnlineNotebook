package com.notebook.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks 公用工具类
 */
public class CommonUtil {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks object是否为空
	 */
	public final static boolean isObjectNull(Object object){
		if(object == null){
			return true;			
		}
		return false;
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks list是否为空
	 */
	public final static boolean isListEmpty(List<Object> list){
		if(list != null){
			return list.isEmpty();
		}
		return false;
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks 取得访问用户的IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		String requestType = "x-forwarded-for";
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			requestType = "Proxy-Client-IP" ; 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			requestType = "WL-Proxy-Client-IP" ; 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			requestType = "remote Addr" ;
		}
		return requestType + " : " + ip;
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 随机获取一个1-999999991的整形数字
	 */
	public final static int getNum(){
		return 1+(int)(Math.random()*999999990);
	}
}
