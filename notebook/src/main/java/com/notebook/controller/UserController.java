package com.notebook.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notebook.service.UserInfoService;

@Controller
@RequestMapping(value="/ui")
public class UserController {

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView userIndex(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//userInfoService.getUserInfoByID(9527);
		
		return new ModelAndView("/ui/index");
	}
	
	@RequestMapping(value="/userAdd", method=RequestMethod.GET)
	@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	@RequiresPermissions(value={"userAdd"},logical = Logical.AND)
	public ModelAndView userAdd(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	@RequiresPermissions(value={"userUpdate"},logical = Logical.AND)
	public ModelAndView userUpdate(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//List<UserInfo> temp = userInfoService.getAllUser();
		//List<UserInfo> temp = userInfoService.getAllUserAndRole();

		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/userDel", method=RequestMethod.GET)
	@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	@RequiresPermissions(value={"userDelete"},logical = Logical.AND)
	public ModelAndView userDel(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/ui/user");
	}

}
