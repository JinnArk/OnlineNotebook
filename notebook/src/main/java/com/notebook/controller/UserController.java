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
@RequestMapping(value="/ui")
public class UserController {

	@Autowired
	UserInfoService userInfoService;

	
	@RequestMapping(value="/userAdd", method=RequestMethod.GET)
	public ModelAndView user(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	public ModelAndView user2(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//List<UserInfo> temp = userInfoService.getAllUser();
		//List<UserInfo> temp = userInfoService.getAllUserAndRole();

		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/user3", method=RequestMethod.GET)
	public ModelAndView user3(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//分页条件已改，不能使用了
//		Page<UserInfo> page=new Page<UserInfo>(1,1);//当前页，页面大小
//		page = userInfoService.getUsersByPageAndCondition(page,1);
//		
//		System.out.println(page.toString());
//		
//		UserInfo t1 = (UserInfo)page.getRecords().get(0);
//		System.out.println(t1.getId());
//		System.out.println(t1.getUsername());
//		System.out.println(t1.getPassword());
		
		return new ModelAndView("/ui/user");
	}

}
