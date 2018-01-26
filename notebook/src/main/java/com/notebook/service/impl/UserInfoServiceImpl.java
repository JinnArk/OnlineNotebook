package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.UserInfoMapper;
import com.notebook.entities.UserInfo;
import com.notebook.model.admin.AdminIndexModel;
import com.notebook.service.UserInfoService;
import com.notebook.util.StringUtil;


@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;

	
	@Override
	public UserInfo getUserInfoByID(int userID) throws Exception {
		return userInfoMapper.selectById(userID);
	}

	@Override
	public List<UserInfo> getAllUserAndRole() throws Exception {
		return userInfoMapper.selectAllUserAndRole();
	}

	@Override
	public List<UserInfo> getAllUser()  throws Exception{
		return userInfoMapper.selectAllUser();
	}

	@Override
	public Page<UserInfo> getUsersByPageAndCondition(Page<UserInfo> page, String username) throws Exception{
		EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<UserInfo>();
		if(!StringUtil.isEmpty(username)){
			entityWrapper.like("USERNAME",username);
		}
		return page.setRecords(userInfoMapper.selectUsersByPageAndCondition(page, entityWrapper));
	}

	@Override
	public int getAllUsersNumByCondition(String username) throws Exception {
		EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<UserInfo>();
		if(!StringUtil.isEmpty(username)){
			entityWrapper.like("USERNAME",username);
		}
		return userInfoMapper.selectCount(entityWrapper);
	}
	
	@Override
	public UserInfo getUserInfoByUsername(String username) throws Exception {
		return userInfoMapper.selectUserInfoByUsername(username);
	}

	@Override
	public AdminIndexModel getAdminIndexModel() throws Exception {
		
		return userInfoMapper.selectAdminIndexModel();
	}

	@Override
	public int saveUserInfo(UserInfo user) throws Exception {
		
		return userInfoMapper.updateById(user);
	}
}
