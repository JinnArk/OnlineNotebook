package com.notebook.util;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月22日
 * @remarks 常量工具类
 */
public final class ConstantUtil {
	private ConstantUtil() {}
	
	//通用变量
	public static final String HEAD = "head";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String FOOT = "foot";
	public static final String INDEX = "index";
	public static final String PASSWORD = "password";
	
	public static final String AJAX_SUCCESS = "success";
	public static final String AJAX_FAIL = "fail";
	public static final String AJAX_ERROR = "error";
	
	//HomeController
	public static final String UNAUTHORIZED = "403";
	public static final String MAIN = "main";
	public static final String LOGIN = "login";
	public static final String NEWPASSWORD = "newpassword";
	public static final String REGISTER = "register";
	
	public static final String TOADMININDEX = "redirect:/admin/index";
	public static final String TOUSERINDEX = "redirect:/ui/index";
	public static final String TOLOGIN = "redirect:/login";
	
	//AdminController
	public static final String ADMINMAIN = "/admin/main";
	public static final String SYSTEMMAIL = "/admin/systemMail";
	public static final String USERINFOS = "/admin/userInfos";
	
	public static final String NOTES = "/admin/notes";
	
	public static final String NOTETAGS = "/admin/noteTags";
	
	public static final String ADMININDEXMODEL = "adminIndexModel";
	public static final String NOTICES = "notices";
	public static final String LOGINRECORDS = "loginRecords";
	
	//LetterController
	public static final String LETTER_UNREAD = "/commons/letterUnread";
	
	public static final String LETTER_NEW = "/commons/letterNew";
	
	public static final String LETTER_SERACH = "/commons/letterSerach";
	
	//UserController
	
	
}
