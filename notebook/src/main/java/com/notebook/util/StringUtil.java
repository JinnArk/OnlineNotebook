package com.notebook.util;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月22日
 * @remarks 字符串判断工具类
 */
public final class StringUtil {
	private StringUtil() {}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 判断字符串是否为空
	 */
	public static boolean isEmpty(final String string) {
		boolean res = false;
		if(string == null){
			res = true;
		}else if(string.trim().length() == 0) {
			res = true;
		}
		return res;
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 判断字符串数组是否为空
	 */
	public static boolean isEmpty(final String[] string) {
		boolean res = true;
		if(string != null){
			for(String ss : string){
				if(!isEmpty(ss)){
					res = false;
					break;
				}
			}
		}
		return res;
	}
}
