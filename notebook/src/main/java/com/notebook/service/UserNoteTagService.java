package com.notebook.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserNotetag;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月26日
 * @remarks	用户标签Service
 */
public interface UserNoteTagService {
	 /**
	  * 
	  * @author 2ing
	  * @createTime 2018年1月26日
	  * @remarks 更新用户标签状态，根据标签ID(会连带修改相关事件状态)
	  */
	 void updateUserNoteTagState(String noteTagID, int state) throws Exception;;
		
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks	获取用户标签，userID用户ID，createDate创建日期
	 */
	Page<UserNotetag> getUserNoteTagByPageAndCondition(Page<UserNotetag> page,
			String userID, String createDate) throws Exception;
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks	获取用户标签总数，根据条件userID用户ID，createDate创建日期
	 */
	int getUserNoteTagNumByCondition(String userID, String createDate) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks 保存标签信息noteTag,saveType为保存种类，0新建1修改2删除/恢复
	 */
	int saveUserNoteTag(String noteTagID, String remark, String noteTagName,
			String userID, int state , int saveType) throws Exception;
}
