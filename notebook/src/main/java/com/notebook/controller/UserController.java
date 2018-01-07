package com.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notebook.config.ConfigBean;

@RestController
public class UserController {
	
	@Autowired
	ConfigBean configbean;
	
	@RequestMapping("/")
	public String index(){
		return configbean.getUsername();
	}

}
