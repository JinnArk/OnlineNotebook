package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.UserInfoMapper;
import com.notebook.entities.UserInfo;
import com.notebook.service.UserInfoService;

@Service
public class UserInfoSerViceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfoByID(int userID) {
		return userInfoMapper.selectById(userID);
	}

	@Override
	public List<UserInfo> getAllUserAndRole() {
		return userInfoMapper.selectAllUserAndRole();
	}

	@Override
	public List<UserInfo> getAllUser() {
		return userInfoMapper.selectAllUser();
	}

	@Override
	public Page<UserInfo> getUsersByPageAndCondition(Page<UserInfo> page, String condtion) {
		return page.setRecords(userInfoMapper.selectUsersByPageAndCondition(page, condtion));
	}

	@Override
	public UserInfo getUserInfoByUsername(String username) {
		return userInfoMapper.selectUserInfoByUsername(username);
	}
}
