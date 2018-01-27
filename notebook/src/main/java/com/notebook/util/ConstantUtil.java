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
	public static final String PAGEMODELS = "pageModels";
		//AJAX
	public static final String AJAX_SUCCESS = "success";
	public static final String AJAX_FAIL = "fail";
	public static final String AJAX_ERROR = "error";
		//PageModel
	public static final String PAGEMODEL_URL = "pageModelUrl";
	public static final String PAGEMODEL_PARAM = "pageModelParam";
	
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
		//userinfo
	public static final String USERINFOS = "/admin/userInfos";
	public static final String USERINFOSURL = "/admin/userInfo";
	public static final String TOUSERINFO = "redirect:/admin/userInfo";
		//notice
	public static final String NOTICESPAGE = "/admin/notices";
	public static final String NOTICEPAGE = "/admin/notice";
	public static final String NOTICES = "notices";
	public static final String NOTICE = "notice";
	public static final String NOTICESURL = "/admin/notices";
	public static final String TONOTICESPAGE = "redirect:/admin/notices";
	
		//note-notes
	public static final String NOTES = "/admin/notes";
	public static final String NOTESURL = "/admin/notes";
	public static final String TONOTES = "redirect:/admin/notes";
		//note-noteTag
	public static final String NOTETAGS = "/admin/noteTags";
	public static final String NOTETAGSURL = "/admin/noteTags";
	public static final String TONOTETAGS = "redirect:/admin/noteTags";
	
		//index
	public static final String ADMININDEXMODEL = "adminIndexModel";
	public static final String NOTICEMODELS = "notices";
	public static final String LOGINRECORDMODELS = "loginRecords";
		//email
	public static final String EMAILHOST = "emailHost";
	public static final String EMAILUSERNAME = "emailUserName";
	public static final String EMAILPASSWORD = "emailPassword";
	public static final String TOSYSTEMMAIL = "redirect:/admin/systemMail";
	
	//LetterController
		//unread
	public static final String LETTER_UNREAD = "/commons/letterUnread";
	public static final String UNREADLETTERS = "unreadLetters";
	public static final String UNREADLETTERSURL = "/commons/letterUnread";
	public static final String TOUNREADLETTERSPAGE = "redirect:/commons/letterUnread";

		//sended
	public static final String LETTER_SENDED = "/commons/letterSended";
	public static final String SENDEDLETTERSURL = "/commons/letterSended";
		//send
	public static final String LETTER_NEW = "/commons/letterNew";
	
		//seache
	public static final String LETTER_SERACH = "/commons/letterSerach";
	public static final String SERACHLETTERSURL = "/commons/letterSerach";
	
	
	//UserController
	
	//EmailController
	public static final String EMAILTYPEREGISTER = "Register";
	public static final String EMAILTYPEFINDPASSWORD = "FindPassword";
}
