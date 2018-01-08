package com.notebook.service;

import java.util.List;

import com.notebook.entities.UserInfo;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月8日
 * @remarks 用户信息Service
 */

public interface UserInfoService {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月8日
	 * @remarks 获取用户数据，根据ID
	 */
	 List<UserInfo> getUserInfoByID();
}
