package com.notebook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notebook.dao.UserLoginRecordMapper;
import com.notebook.entities.UserLoginrecord;
import com.notebook.model.common.LoginRecordModel;
import com.notebook.service.UserLoginRecordService;
import com.notebook.util.CommonUtil;

@Service
public class UserLoginRecordServiceImpl implements UserLoginRecordService{

	@Autowired
	UserLoginRecordMapper userLoginRecordMappper;
	
	@Override
	public List<LoginRecordModel> getIndexLoginRecordModel(int userID) throws Exception {
		return userLoginRecordMappper.selectIndexLoginRecordModel(userID);
	}

	@Override
	public int saveLoginRecord(String userID, String loginIP, String loginRemark)
			throws Exception {
		UserLoginrecord loginRecord = new UserLoginrecord();
		loginRecord.setRecordId(CommonUtil.getNum());
		loginRecord.setUserId(Integer.valueOf(userID));
		loginRecord.setLoginIp(loginIP);
		loginRecord.setLoginRemark(loginRemark);
		loginRecord.setLoginDate(new Date());
		return userLoginRecordMappper.insert(loginRecord);
	}
	
}
