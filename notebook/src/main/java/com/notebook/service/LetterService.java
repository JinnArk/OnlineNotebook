package com.notebook.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.UserInfo;
import com.notebook.model.common.LetterModel;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月25日
 * @remarks 站内信service接口
 */
public interface LetterService {
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	获取分页站内信，根据条件，letterState新建状态0/1，recipientID接收者ID，createDate信件创建日期
	 */
	Page<LetterModel> getLetterModelsByPageAndConditionForRecipient(Page<LetterModel> page,
			int[] letterState, int recipientID, String createDate) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	获取分页站内信，根据条件，letterState新建状态0/1，sendedID发出者ID，createDate信件创建日期
	 */
	Page<LetterModel> getLetterModelsByPageAndConditionForSended(Page<LetterModel> page,
			int[] letterState, int sendedID, String createDate) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	获取站内信总数，根据条件(已收信)
	 */
	int getAllLetterNumByConditionForRecipient(int[] letterState, int recipientID, String createDate) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	获取站内信总数，根据条件(已发信)
	 */
	int getAllLetterNumByConditionForSended(int[] letterState, int sendedID, String createDate) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 保存站内信
	 */
	int saveLetter(UserInfo sendUser, UserInfo recipientUser, String recipientName
			, String letterTitle, String letterContent, String replyLetterID) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 更新站内信状态，根据ID
	 */
	int updateOneLetterState(String letterID) throws Exception;
	

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 更新站内信状态，根据数组
	 */
	int updateLettersState(String[] letterIDs) throws Exception;
}
