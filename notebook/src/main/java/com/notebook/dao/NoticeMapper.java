package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
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

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月24日
	 * @remarks 根据pageModel和condition获取公告数据
	 */
	//List<NoticeModel> selectNoticesByPageAndCondition(Pagination page, String condition);
	
	List<NoticeModel> selectNoticesByPageAndCondition(RowBounds rowBounds, @Param("ew") Wrapper<NoticeModel> wrapper) throws Exception;
	List<NoticeModel> selectNoticesByPage(Pagination page) throws Exception;
}
