package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notebook.dao.UserInfoMapper;
import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;

@Service
public class UserInfoSerViceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<UserInfo> getUserInfoByID() {
		return userInfoMapper.getAll();
	}

}
