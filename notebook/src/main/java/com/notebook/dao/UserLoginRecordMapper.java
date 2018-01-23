package com.notebook.dao;

import java.util.List;

import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.UserLoginrecord;
import com.notebook.model.common.LoginRecordModel;
  

/**
 * 
 * @author 2ing
 * @createTime 2018-01-22
 * @remarks 用户登录记录DAO
 */

public interface UserLoginRecordMapper extends SuperMapper<UserLoginrecord>{
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks	获取-后台首页-登录记录model数据
	 */
	List<LoginRecordModel> selectIndexLoginRecordModel(int userID) throws Exception;
}
