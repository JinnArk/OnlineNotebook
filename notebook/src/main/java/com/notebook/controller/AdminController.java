package com.notebook.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.config.email.EmailConfig;
import com.notebook.entities.SysNotice;
import com.notebook.entities.UserInfo;
import com.notebook.entities.UserNote;
import com.notebook.entities.UserNotetag;
import com.notebook.model.admin.AdminIndexModel;
import com.notebook.model.common.LoginRecordModel;
import com.notebook.model.common.NoticeModel;
import com.notebook.service.NoticeService;
import com.notebook.service.UserInfoService;
import com.notebook.service.UserLoginRecordService;
import com.notebook.service.UserNoteService;
import com.notebook.service.UserNoteTagService;
import com.notebook.util.CommonUtil;
import com.notebook.util.ConstantUtil;
import com.notebook.util.EmailUtil;
import com.notebook.util.StringUtil;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserLoginRecordService userLoginRecordService;
	@Autowired
	UserNoteService userNoteService;
	@Autowired
	UserNoteTagService userNoteTagService;

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 管理员登录后首页
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView adminIndex(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			//获取当前用户
		 	UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			AdminIndexModel adminIndexModel = userInfoService.getAdminIndexModel();
			List<NoticeModel> notices = noticeService.getIndexNoticeModel();
			List<LoginRecordModel> loginRecords = userLoginRecordService.getIndexLoginRecordModel(user.getUserId());

			model.addAttribute(ConstantUtil.ADMININDEXMODEL, adminIndexModel);
			model.addAttribute(ConstantUtil.NOTICEMODELS, notices);
			model.addAttribute(ConstantUtil.LOGINRECORDMODELS, loginRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//模板没写Null判断
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.INDEX);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 系统邮箱设置页面
	 */
	@RequestMapping(value="/systemMail", method=RequestMethod.GET)
	public ModelAndView systemMail(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.EMAILHOST, EmailConfig.getHost());
		model.addAttribute(ConstantUtil.EMAILUSERNAME, EmailConfig.getUsername());
		model.addAttribute(ConstantUtil.EMAILPASSWORD, EmailConfig.getPassword());
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.SYSTEMMAIL);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks AJAX测试公用email账号
	 */
	@RequestMapping(value = "/emailConnectTest", method = RequestMethod.POST)
    @ResponseBody
    public String emailConnectTest(String host,String username, String password) {
		
		try {
			EmailUtil.emailconnectTest(host, username, password);
			return ConstantUtil.AJAX_SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantUtil.AJAX_ERROR;
		}
	}
		
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks 保存邮箱信息
	 */
	@RequestMapping(value="/emailSave", method=RequestMethod.POST)
	public ModelAndView emailSave(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		
		String host = request.getParameter("emailHost");
		String username = request.getParameter("emailUserName");
		String password = request.getParameter("emailPassword");
		
		try {
			EmailUtil.emailUtilSet(host, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOSYSTEMMAIL);
	}
	

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 公告notice管理
	 */
	@RequestMapping(value="/notices", method=RequestMethod.GET)
	public ModelAndView notices(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		String noticeTitle = request.getParameter("noticeTitle");
		String pagenow = request.getParameter("pagenow");
		
		Page<NoticeModel> noticesPage = null;
		if(!StringUtil.isEmpty(pagenow)){
			noticesPage = new Page<NoticeModel>(Integer.valueOf(pagenow).intValue(), 5);
		}else{
			noticesPage = new Page<NoticeModel>(1, 5);
		}
		
		try {
			noticesPage = noticeService.getNoticeModelsByPageAndCondition(noticesPage, noticeTitle);//分页之后的信息
			noticesPage.setTotal(noticeService.getAllNoticeNumByCondition(noticeTitle));//总数
			
			//pagemodel
			model.addAttribute(ConstantUtil.PAGEMODELS, noticesPage);
			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.NOTICESURL);
			//url参数
			if(!StringUtil.isEmpty(noticeTitle)){
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "noticeTitle="+noticeTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTICESPAGE);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 公告notice删除
	 */
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public ModelAndView noticeDelete(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		String noticeId = request.getParameter("noticeId");
		try {
			noticeService.deleteNoticeById(Integer.valueOf(noticeId).intValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TONOTICESPAGE);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 编辑notice公告
	 */
	@RequestMapping(value="/notice", method=RequestMethod.GET)
	public ModelAndView notice(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String noticeId = request.getParameter("noticeId");
			if(!StringUtil.isEmpty(noticeId)){
				SysNotice notice = noticeService.getNoticeById(Integer.valueOf(noticeId).intValue());
				model.addAttribute(ConstantUtil.NOTICE, notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTICEPAGE);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 公告notice保存
	 */
	@RequestMapping(value="/savenotice", method=RequestMethod.POST)
	public ModelAndView savenotice(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String noticeId = request.getParameter("noticeID");
			String noticeTitle = request.getParameter("noticeTitle");
			String noticeContent = request.getParameter("noticeContent");
			
			SysNotice notice = null;
			
			if(!StringUtil.isEmpty(noticeId)){//update
				notice = noticeService.getNoticeById(Integer.valueOf(noticeId).intValue());
				notice.setNoticeTitle(noticeTitle);
				notice.setNoticeContent(noticeContent);
				notice.setModifyDate(new Date());
			}else{//new
				notice = new SysNotice();
				notice.setNoticeId(CommonUtil.getNum());
				notice.setNoticeTitle(noticeTitle);
				notice.setNoticeContent(noticeContent);
				Date currentDate = new Date();
				notice.setCreateDate(currentDate);
				notice.setModifyDate(currentDate);
			}
			noticeService.saveNotice(notice);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(ConstantUtil.TONOTICESPAGE);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 用户管理
	 */
	@RequestMapping(value="/userInfo", method=RequestMethod.GET)
	public ModelAndView userInfo(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			String pagenow = request.getParameter("pagenow");
			String username = request.getParameter("username");
			//UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			
			Page<UserInfo> userInfosPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				userInfosPage = new Page<UserInfo>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				userInfosPage = new Page<UserInfo>(1,5);
			}
			
			userInfosPage = userInfoService.getUsersByPageAndCondition(userInfosPage, username);
			userInfosPage.setTotal(userInfoService.getAllUsersNumByCondition(username));

			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.USERINFOSURL);
			if(!StringUtil.isEmpty(username)){
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "username="+username);
			}
			model.addAttribute(ConstantUtil.PAGEMODELS, userInfosPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.USERINFOS);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks 禁止/允许用户登录(改变用户状态0/1)
	 */
	@RequestMapping(value="/userToClose", method=RequestMethod.GET)
	public ModelAndView userToClose(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String userID = request.getParameter("userID");
			UserInfo user = userInfoService.getUserInfoByID(Integer.valueOf(userID));
			user.setState(0);
			userInfoService.saveUserInfo(user);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUSERINFO);
	}
	@RequestMapping(value="/userToOpen", method=RequestMethod.GET)
	public ModelAndView userToOpen(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			String userID = request.getParameter("userID");
			UserInfo user = userInfoService.getUserInfoByID(Integer.valueOf(userID));
			user.setState(1);
			userInfoService.saveUserInfo(user);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUSERINFO);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 记事本管理
	 */
	@RequestMapping(value="/notes", method=RequestMethod.GET)
	public ModelAndView note(final Model model, final HttpServletRequest request, HttpServletResponse response){
		//userNoteService
		
		try {
			String pagenow = request.getParameter("pagenow");
			String userID = request.getParameter("userID");
			
			Page<UserNote> userNotesPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				userNotesPage = new Page<UserNote>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				userNotesPage = new Page<UserNote>(1,5);
			}
			userNotesPage = userNoteService.getUserNoteByPageAndCondition(userNotesPage, userID, null, null, null);
			userNotesPage.setTotal(userNoteService.getUserNoteNumByCondition(userID, null, null, null));

			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.NOTESURL);
			if(!StringUtil.isEmpty(userID)){
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "userID="+userID);
			}
			model.addAttribute(ConstantUtil.PAGEMODELS, userNotesPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTES);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks	禁止/恢复用户事件(改变用户事件状态0/1)
	 */
	@RequestMapping(value="/noteToClose", method=RequestMethod.GET)
	public ModelAndView noteToClose(final Model model, final HttpServletRequest request, HttpServletResponse response){
		String userID = request.getParameter("userID");
		try {
			String noteID = request.getParameter("noteID");
			userNoteService.saveUserNote(noteID, null, null, null, 0, null, 2);

		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(new StringBuffer(ConstantUtil.TONOTES).append("?userID=").append(userID).toString());
	}
	@RequestMapping(value="/noteToOpen", method=RequestMethod.GET)
	public ModelAndView noteToOpen(final Model model, final HttpServletRequest request, HttpServletResponse response){
		String userID = request.getParameter("userID");
		try {
			String noteID = request.getParameter("noteID");
			userNoteService.saveUserNote(noteID, null, null, null, 1, null, 2);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//return new ModelAndView(ConstantUtil.TONOTES);
		return new ModelAndView(new StringBuffer(ConstantUtil.TONOTES).append("?userID=").append(userID).toString());
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 记事本标签管理
	 */
	@RequestMapping(value="/noteTags", method=RequestMethod.GET)
	public ModelAndView noteTag(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//userNoteTagService
		
		try {
			String pagenow = request.getParameter("pagenow");
			String userID = request.getParameter("userID");
			
			Page<UserNotetag> userNoteTagsPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				userNoteTagsPage = new Page<UserNotetag>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				userNoteTagsPage = new Page<UserNotetag>(1,5);
			}
			userNoteTagsPage = userNoteTagService.getUserNoteTagByPageAndCondition(userNoteTagsPage, userID, null, null);
			userNoteTagsPage.setTotal(userNoteTagService.getUserNoteTagNumByCondition(userID, null, null));

			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.NOTETAGSURL);
			if(!StringUtil.isEmpty(userID)){
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "userID="+userID);
			}
			model.addAttribute(ConstantUtil.PAGEMODELS, userNoteTagsPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTETAGS);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks	禁止/恢复用户标签(改变用户标签状态0/1)
	 */
	@RequestMapping(value="/noteTagToClose", method=RequestMethod.GET)
	public ModelAndView noteTagToClose(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		String userID = request.getParameter("userID");
		try {
			String noteTagID = request.getParameter("noteTagID");
			//userNoteTagService.saveUserNoteTag(noteTagID, null, null, null, 0, 2);
			userNoteTagService.updateUserNoteTagState(noteTagID, 0);//级联
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//return new ModelAndView(ConstantUtil.TONOTETAGS);
		return new ModelAndView(new StringBuffer(ConstantUtil.TONOTETAGS).append("?userID=").append(userID).toString());
	}
	@RequestMapping(value="/noteTagToOpen", method=RequestMethod.GET)
	public ModelAndView noteTagToOpen(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		String userID = request.getParameter("userID");
		try {
			String noteTagID = request.getParameter("noteTagID");
			userNoteTagService.saveUserNoteTag(noteTagID, null, null, null, 1, 2);
			//userNoteTagService.updateUserNoteTagState(noteTagID, 1);//级联
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//return new ModelAndView(ConstantUtil.TONOTETAGS);
		return new ModelAndView(new StringBuffer(ConstantUtil.TONOTETAGS).append("?userID=").append(userID).toString());
	}
}
