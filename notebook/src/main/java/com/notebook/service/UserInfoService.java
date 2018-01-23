package com.notebook.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserInfo;
import com.notebook.model.admin.AdminIndexModel;

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
	 * @remarks 获取用户信息，根据ID
	 */
	UserInfo getUserInfoByID(int userID);
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 获取用户信息，根据用户帐号名称(包括角色)
	 */
	UserInfo getUserInfoByUsername(String Username);
	 
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
	 Page<UserInfo> getUsersByPageAndCondition(Page<UserInfo> page, String condition);
	 
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月23日
	  * @remarks 获取-管理员首页model
	  */
	 AdminIndexModel getAdminIndexModel() throws Exception;
	 
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月23日
	  * @remarks 保存用户信息
	  */
	 int saveUserInfo(UserInfo user) throws Exception;
}
