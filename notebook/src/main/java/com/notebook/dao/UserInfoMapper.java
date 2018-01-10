package com.notebook.dao;

import java.util.List;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.UserInfo;
  

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
	 * @createTime 2018年1月8日
	 * @remarks 获取所有用户数据(不包括角色信息)
	 */
	List<UserInfo> selectAllUser();
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月9日
	 * @remarks 获取所有用户数据(包括角色信息)
	 */
	List<UserInfo> selectAllUserAndRole();
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月10日
	 * @remarks 获取用户数据，根据分页信息和条件
	 */
	List<UserInfo> selectUsersByPageAndCondition(Pagination page, int state);
}
