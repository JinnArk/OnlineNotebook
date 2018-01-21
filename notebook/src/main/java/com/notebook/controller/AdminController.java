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

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView userIndex(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/admin/index");
	}

}
