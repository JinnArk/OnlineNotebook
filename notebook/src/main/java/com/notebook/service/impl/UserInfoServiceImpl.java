package com.notebook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.UserInfoMapper;
import com.notebook.entities.UserInfo;
import com.notebook.model.admin.AdminIndexModel;
import com.notebook.service.UserInfoService;
import com.notebook.util.CommonUtil;
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
	public int saveUserInfo(String userID, String username, String password, String nickname,
			String salt, int state, int saveType) throws Exception {
		UserInfo user = null;
		if(saveType==0){
			user = new UserInfo();
			int id = CommonUtil.getNum();
			user.setUserId(id);
			user.setUuid(id);
			user.setUsername(username);
			user.setPassword(password);
			user.setSalt(salt);
			Date now = new Date();
			user.setCreateDate(now);
			user.setLoginDate(now);
			user.setNickname(nickname);
			//普通用户默认角色ID:1024 
			userInfoMapper.insert(user);
			return userInfoMapper.createConnectWithUserAndRole(CommonUtil.getNum(), id, 1024);
		}else if(saveType==1){
			user = userInfoMapper.selectById(userID);
			user.setNickname(nickname);
			return userInfoMapper.updateById(user);
		}else if(saveType==2){
			user = userInfoMapper.selectById(userID);
			user.setState(state);
			return userInfoMapper.updateById(user);
		}else if(saveType==3){
			user = userInfoMapper.selectById(userID);
			user.setSalt(salt);
			user.setPassword(password);
			return userInfoMapper.updateById(user);
		}else{
			return 9527;//出现了其他数字
		}
	}

	@Override
	public int saveUserInfo(UserInfo user) throws Exception {
		return userInfoMapper.updateById(user);
	}


}
