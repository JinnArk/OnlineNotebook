package com.notebook.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.entities.SysNotice;
import com.notebook.model.common.NoticeModel;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks
 */
public interface NoticeService {
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks 获取-后台首页-公告model
	 */
	List<NoticeModel> getIndexNoticeModel() throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks 获取分页公告，根据条件，condition为条件
	 */
	Page<NoticeModel> getNoticeModelsByPageAndCondition(Page<NoticeModel> page, String conditon) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks	删除公告，根据ID
	 */
	int deleteNoticeById(int id) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks 获取公告总数，根据条件
	 */
	int getAllNoticeNumByCondition(String condition) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 获取公告实体，根据ID
	 */
	SysNotice getNoticeById(int id) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	保存公告实体
	 */
	int saveNotice(SysNotice notice) throws Exception;
}
