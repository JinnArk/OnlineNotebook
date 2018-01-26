package com.notebook.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserNote;

public interface UserNoteService {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks	获取用户事件，userID用户ID，noteTagID标签ID
	 */
	Page<UserNote> getUserNoteByPageAndCondition(Page<UserNote> page,
			String userID, String noteTagID) throws Exception;
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018-01-26
	 * @remarks	获取用户事件总数，根据条件userID用户ID，noteTagID标签ID
	 */
	int getUserNoteNumByCondition(String userID, String noteTagID) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks 保存事件信息note,saveType为保存种类，0新建1修改2删除/恢复
	 */
	int saveUserNote(String noteID, String noteTitle, String noteTagID,
			String userID, int state , String noteContent, int saveType) throws Exception;
}
