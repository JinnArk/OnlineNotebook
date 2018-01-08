package com.notebook.dao;

import java.util.List;

import com.notebook.entities.UserInfo;
import org.apache.ibatis.annotations.Mapper;  

/**
 * 
 * @author 2ing
 * @createTime 2018年1月8日
 * @remarks 用户信息DAO
 */

@Mapper
public interface UserInfoMapper {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月8日
	 * @remarks 获取所有用户信息
	 */
	List<UserInfo> getAll();
}
