package com.notebook.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
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
	UserInfo getUserInfoByID(int userID);
	 
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月9日
	  * @remarks 获取所有用户信息(不包括角色)
	  */
	 List<UserInfo> getAllUser();
	 
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月9日
	  * @remarks 获取所有用户信息(包括角色)
	  */
	 List<UserInfo> getAllUserAndRole();
	 
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月10日
	  * @remarks 获取用户信息，根据分页信息和条件
	  */
	 Page<UserInfo> getUsersByPageAndCondition(Page<UserInfo> page, int state);
}
