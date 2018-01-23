package com.notebook.service;

import java.util.List;

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
}
