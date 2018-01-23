package com.notebook.dao;

import java.util.List;

import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.SysNotice;
import com.notebook.model.common.NoticeModel;
  

/**
 * 
 * @author 2ing
 * @createTime 2018-01-22
 * @remarks 公告DAO
 */

public interface NoticeMapper extends SuperMapper<SysNotice>{
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月23日
	 * @remarks	获取-后台首页-公告model数据
	 */
	List<NoticeModel> selectIndexNoticeModel() throws Exception;
}
