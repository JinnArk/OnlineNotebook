package com.notebook.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notebook.service.UserInfoService;
import com.notebook.util.ConstantUtil;

@Controller
@RequestMapping(value={"/commons"})
public class LetterController {

	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 未读站内信
	 */
	@RequestMapping(value="/letterUnread", method=RequestMethod.GET)
	public ModelAndView letterUnread(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_UNREAD);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月22日
	 * @remarks 新建/搜索站内信
	 */
	@RequestMapping(value="/letterNew", method=RequestMethod.GET)
	public ModelAndView newLetter(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_NEW);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
	
	@RequestMapping(value="/letterSerach", method=RequestMethod.GET)
	public ModelAndView letterSerach(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		model.addAttribute(ConstantUtil.CONTENT, ConstantUtil.LETTER_SERACH);
		return new ModelAndView(ConstantUtil.ADMINMAIN);
	}
}
