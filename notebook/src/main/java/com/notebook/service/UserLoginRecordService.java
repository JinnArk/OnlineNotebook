package com.notebook.service;

import java.util.List;

import com.notebook.model.common.LoginRecordModel;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks
 */
public interface UserLoginRecordService {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks	获取-后台首页-登录记录model
	 */
	List<LoginRecordModel> getIndexLoginRecordModel(int userID) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月28日
	 * @remarks 新建一条登录记录
	 */
	int saveLoginRecord(String userID, String loginIP, String loginRemark) throws Exception;
}
