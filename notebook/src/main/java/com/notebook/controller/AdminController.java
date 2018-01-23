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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.notebook.entities.UserInfo;
import com.notebook.model.admin.AdminIndexModel;
import com.notebook.model.common.LoginRecordModel;
import com.notebook.model.common.NoticeModel;
import com.notebook.service.NoticeService;
import com.notebook.service.UserInfoService;
import com.notebook.service.UserLoginRecordService;
import com.notebook.util.ConstantUtil;
import com.notebook.util.PasswordUtil;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserLoginRecordService userLoginRecordService;

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
			model.addAttribute(ConstantUtil.NOTICES, notices);
			model.addAttribute(ConstantUtil.LOGINRECORDS, loginRecords);
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
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.SYSTEMMAIL);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 管理员密码修改页面
	 */
	@RequestMapping(value="/password", method=RequestMethod.GET)
	public ModelAndView password(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.PASSWORD);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	@RequestMapping(value = "/newpassword", method = RequestMethod.POST)
    @ResponseBody
    public String newpassword(String newpassword) {
		//System.out.println(newpassword);
		//System.out.println(PasswordUtil.encryptPassword(newpassword));
		
		try {
			String salt = PasswordUtil.createSalt();
			//获取当前用户
	 		UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
	 		user.setSalt(salt);
	 		user.setPassword(PasswordUtil.encryptPassword(newpassword,salt));
	 		
	 		//userInfoService.getUserInfoByID(user.getUserId());
	 		
	 		//影响行数
			int result = userInfoService.saveUserInfo(user);
			
			//System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ConstantUtil.AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 用户管理
	 */
	@RequestMapping(value="/userInfo", method=RequestMethod.GET)
	public ModelAndView userInfo(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.USERINFOS);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 记事本管理
	 */
	@RequestMapping(value="/notes", method=RequestMethod.GET)
	public ModelAndView note(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTES);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 记事本标签管理
	 */
	@RequestMapping(value="/noteTags", method=RequestMethod.GET)
	public ModelAndView noteTag(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.NOTETAGS);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}

}
