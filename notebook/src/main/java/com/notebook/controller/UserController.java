package com.notebook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notebook.config.ConfigBean;

@Controller
public class UserController {
	
	@Autowired
	ConfigBean configbean;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map){
		map.put("v1", "2ing");
		return "hello";
	}

}
