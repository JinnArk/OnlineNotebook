package com.notebook.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserInfo;
import com.notebook.entities.UserNote;
import com.notebook.entities.UserNotetag;
import com.notebook.model.common.LoginRecordModel;
import com.notebook.model.common.NoticeModel;
import com.notebook.service.NoticeService;
import com.notebook.service.UserInfoService;
import com.notebook.service.UserLoginRecordService;
import com.notebook.service.UserNoteService;
import com.notebook.service.UserNoteTagService;
import com.notebook.util.ConstantUtil;
import com.notebook.util.StringUtil;

@Controller
@RequestMapping(value="/ui")
public class UserController {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	UserNoteService userNoteService;
	@Autowired
	UserNoteTagService userNoteTagService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserLoginRecordService userLoginRecordService;

	//@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	//@RequiresPermissions(value={"userAdd"},logical = Logical.AND)
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-27
	 * @remarks	普通用户后台首页
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView userIndex(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			List<NoticeModel>  notices = noticeService.getIndexNoticeModel();
			List<LoginRecordModel> loginRecords = userLoginRecordService.getIndexLoginRecordModel(user.getUserId());
			model.addAttribute(ConstantUtil.NOTICEMODELS, notices);
			model.addAttribute(ConstantUtil.LOGINRECORDMODELS, loginRecords);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.INDEX);
		return new ModelAndView(ConstantUtil.UIMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-27
	 * @remarks 记事本管理
	 */
	@RequestMapping(value="/notes", method=RequestMethod.GET)
	public ModelAndView note(final Model model, final HttpServletRequest request, HttpServletResponse response){
		//userNoteService
		
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			String pagenow = request.getParameter("pagenow");
			String userID = String.valueOf(user.getUserId());
			String noteTagID = request.getParameter("noteTagID");
			String createDate = request.getParameter("createDate");
			
			Page<UserNote> userNotesPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				userNotesPage = new Page<UserNote>(Integer.valueOf(pagenow).intValue(), 2);
			}else{
				userNotesPage = new Page<UserNote>(1,2);
			}
			userNotesPage = userNoteService.getUserNoteByPageAndCondition(userNotesPage, userID, noteTagID, createDate, "1");
			userNotesPage.setTotal(userNoteService.getUserNoteNumByCondition(userID, noteTagID, createDate, "1"));
			List<UserNotetag> noteTags = userNoteTagService.getNoteTagByCondition(userID,"1");

			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.UINOTESURL);
			
			//url参数
			StringBuffer param = null;
			if(!StringUtil.isEmpty(noteTagID)){
				if(!StringUtil.isEmpty(createDate)){
					param = new StringBuffer();
					param.append("noteTagID=").append(noteTagID).append("&createDate=").append(createDate);
				}
			}else{
				param = new StringBuffer();
				param.append("createDate=").append(createDate);
			}
			if(param!=null){
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, param.toString());
			}
			
			model.addAttribute(ConstantUtil.PAGEMODELS, userNotesPage);
			model.addAttribute(ConstantUtil.USERTAGS, noteTags);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.UINOTES);
		return new ModelAndView(ConstantUtil.UIMAIN);
	}
	
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月27日
	 * @remarks 进入-新建/修改记事本
	 */
	@RequestMapping(value="/notePage", method=RequestMethod.GET)
	public ModelAndView notePage(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			String userID = String.valueOf(user.getUserId());
			String noteID = request.getParameter("noteID");
			UserNote note = userNoteService.getNoteByCondition(noteID);
			List<UserNotetag> noteTags = userNoteTagService.getNoteTagByCondition(userID, "1");
			
			model.addAttribute("note", note);
			model.addAttribute("notetags", noteTags);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.UINOTEPAGE);
		return new ModelAndView(ConstantUtil.UIMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月27日
	 * @remarks 新建/修改记事本
	 */
	@RequestMapping(value="/saveNote", method=RequestMethod.POST)
	public ModelAndView saveNote(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			String userID = String.valueOf(user.getUserId());
			String noteID = request.getParameter("noteID");
			String noteTagID = request.getParameter("noteTagID");
			String noteTitle = request.getParameter("noteTitle");
			String noteContent = request.getParameter("noteContent");
			
			if(!StringUtil.isEmpty(noteID)){//修改
				userNoteService.saveUserNote(noteID, noteTitle, noteTagID, userID, 1, noteContent, 1);
			}else{//新建
				userNoteService.saveUserNote(null, noteTitle, noteTagID, userID, 1, noteContent, 0);
			}

		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUINOTES);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月27日
	 * @remarks 删除记事本
	 */
	@RequestMapping(value="/noteToClose", method=RequestMethod.GET)
	public ModelAndView noteToClose(final Model model, final HttpServletRequest request, HttpServletResponse response){
		try {
			String noteID = request.getParameter("noteID");
			userNoteService.saveUserNote(noteID, null, null, null, 0, null, 2);

		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUINOTES);
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-27
	 * @remarks 记事本标签管理
	 */
	@RequestMapping(value="/noteTags", method=RequestMethod.GET)
	public ModelAndView noteTag(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//userNoteTagService
		
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			String pagenow = request.getParameter("pagenow");
			String userID =	String.valueOf(user.getUserId());
			String createDate = request.getParameter("createDate");
			
			Page<UserNotetag> userNoteTagsPage = null;
			if(!StringUtil.isEmpty(pagenow)){
				userNoteTagsPage = new Page<UserNotetag>(Integer.valueOf(pagenow).intValue(), 5);
			}else{
				userNoteTagsPage = new Page<UserNotetag>(1,5);
			}
			userNoteTagsPage = userNoteTagService.getUserNoteTagByPageAndCondition(userNoteTagsPage, userID, createDate, "1");
			userNoteTagsPage.setTotal(userNoteTagService.getUserNoteTagNumByCondition(userID, createDate, "1"));

			//用于pagemodel跳转的url
			model.addAttribute(ConstantUtil.PAGEMODEL_URL, ConstantUtil.UINOTETAGSURL);
			if(!StringUtil.isEmpty(createDate)){
				//url参数
				model.addAttribute(ConstantUtil.PAGEMODEL_PARAM, "createDate="+createDate);
			}
			model.addAttribute(ConstantUtil.PAGEMODELS, userNoteTagsPage);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.UINOTETAGS);
		return new ModelAndView(ConstantUtil.UIMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-28
	 * @remarks 进入-新增/修改标签
	 */
	@RequestMapping(value="/noteTagPage", method=RequestMethod.GET)
	public ModelAndView noteTagEdit(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			String noteTagID = request.getParameter("noteTagID");
			UserNotetag noteTag = userNoteTagService.getNoteTagsByID(noteTagID);
			model.addAttribute("notetag", noteTag);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.UINOTETAGPAGE);
		return new ModelAndView(ConstantUtil.UIMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-28
	 * @remarks 新增/修改标签
	 */
	@RequestMapping(value="/saveNoteTag", method=RequestMethod.POST)
	public ModelAndView saveNoteTag(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
			String userID = String.valueOf(user.getUserId());
			String noteTagID = request.getParameter("noteTagID");
			String noteTagName = request.getParameter("noteTagName");
			
			if(!StringUtil.isEmpty(noteTagID)){//修改
				userNoteTagService.saveUserNoteTag(noteTagID, "remark", noteTagName, userID, 1, 1);
			}else{//新建
				userNoteTagService.saveUserNoteTag(null, "remark", noteTagName, userID, 1, 0);
			}

		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView(ConstantUtil.TOUINOTETAGS);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月27日
	 * @remarks 删除标签
	 */
	@RequestMapping(value="/noteTagToClose", method=RequestMethod.GET)
	public ModelAndView noteTagToClose(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		try {
			String noteTagID = request.getParameter("noteTagID");
			userNoteTagService.updateUserNoteTagState(noteTagID, 0);//级联
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//return new ModelAndView(ConstantUtil.TONOTETAGS);
		return new ModelAndView(ConstantUtil.TOUINOTETAGS);
	}

}
