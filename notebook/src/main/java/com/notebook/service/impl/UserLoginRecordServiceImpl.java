package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notebook.dao.UserLoginRecordMapper;
import com.notebook.model.common.LoginRecordModel;
import com.notebook.service.UserLoginRecordService;

@Service
public class UserLoginRecordServiceImpl implements UserLoginRecordService{

	@Autowired
	UserLoginRecordMapper userLoginRecordMappper;
	
	@Override
	public List<LoginRecordModel> getIndexLoginRecordModel(int userID) throws Exception {
		return userLoginRecordMappper.selectIndexLoginRecordModel(userID);
	}
	
}
