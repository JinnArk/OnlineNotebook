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

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.SysRole;
import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;

@Controller
public class UserController {

	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map){
		map.put("v1", "2ing");
		return "hello";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView user(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		
		UserInfo ui1 = userInfoService.getUserInfoByID(1);
		System.out.println(ui1.getId());
		System.out.println(ui1.getUsername());
		System.out.println(ui1.getPassword());
		System.out.println(ui1.getNickname());
		
		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/user2", method=RequestMethod.GET)
	public ModelAndView user2(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		//List<UserInfo> temp = userInfoService.getAllUser();
		List<UserInfo> temp = userInfoService.getAllUserAndRole();
		
		for(UserInfo t1:temp){
			System.out.println(t1.getId());
			System.out.println(t1.getUsername());
			System.out.println(t1.getPassword());
			System.out.println(t1.getNickname());
			System.out.println(t1.getSalt());
			System.out.println(t1.getState());
			System.out.println(t1.getCreateDate());
			System.out.println(t1.getLoginDate());
			
			System.out.println("---------------------------------->>>");
			
			for(SysRole r1:t1.getRoles()){
				System.out.println(r1.getId());
				System.out.println(r1.getRole());
				System.out.println(r1.getState());
				System.out.println(r1.getDescription());
			}
		}
		
		
		return new ModelAndView("/ui/user");
	}
	
	@RequestMapping(value="/user3", method=RequestMethod.GET)
	public ModelAndView user3(final Model model, final HttpServletRequest request, HttpServletResponse response){
		
		Page<UserInfo> page=new Page<UserInfo>(1,1);//当前页，页面大小
		page = userInfoService.getUsersByPageAndCondition(page,1);
		
		System.out.println(page.toString());
		
		UserInfo t1 = (UserInfo)page.getRecords().get(0);
		System.out.println(t1.getId());
		System.out.println(t1.getUsername());
		System.out.println(t1.getPassword());
		
		return new ModelAndView("/ui/user");
	}

}
