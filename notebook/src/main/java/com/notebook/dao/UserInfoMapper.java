package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.UserInfo;
import com.notebook.model.admin.AdminIndexModel;
  

/**
 * 
 * @author 2ing
 * @createTime 2018年1月8日
 * @remarks 用户信息DAO
 */

public interface UserInfoMapper extends SuperMapper<UserInfo>{
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月12日
	 * @remarks 获取用户数据，根据用户帐号名称(包括角色信息)
	 */
	UserInfo selectUserInfoByUsername(String username) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月8日
	 * @remarks 获取所有用户数据(不包括角色信息)
	 */
	List<UserInfo> selectAllUser() throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月9日
	 * @remarks 获取所有用户数据(包括角色信息)
	 */
	List<UserInfo> selectAllUserAndRole() throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月10日
	 * @remarks 根据pageModel和condition获取用户数据
	 */
	List<UserInfo> selectUsersByPageAndCondition(RowBounds rowBounds, @Param("ew") Wrapper<UserInfo> wrapper) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks 获取-管理员首页model数据
	 */
	AdminIndexModel selectAdminIndexModel() throws Exception;
}
