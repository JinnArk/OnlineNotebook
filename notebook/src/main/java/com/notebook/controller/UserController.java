package com.notebook.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notebook.config.ConfigBean;
import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;

@Controller
public class UserController {
	
	@Autowired
	ConfigBean configbean;
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map){
		map.put("v1", "2ing");
		return "hello";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView user(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		List<UserInfo> temp = userInfoService.getUserInfoByID();
		
		for(UserInfo t1:temp){
			System.out.println(t1.getId());
			System.out.println(t1.getUsername());
			System.out.println(t1.getPassword());
		}
		
		return new ModelAndView("/ui/user");
	}

}
