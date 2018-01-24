package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.NoticeMapper;
import com.notebook.entities.SysNotice;
import com.notebook.model.common.NoticeModel;
import com.notebook.service.NoticeService;
import com.notebook.util.StringUtil;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;
		
	@Override
	public List<NoticeModel> getIndexNoticeModel() throws Exception{
		return noticeMapper.selectIndexNoticeModel();
	}

	@Override
	public Page<NoticeModel> getNoticeModelsByPageAndCondition(Page<NoticeModel> page,
		String conditon) throws Exception {
		if (!StringUtil.isEmpty(conditon)) {
			EntityWrapper<NoticeModel> entityWrapper = new EntityWrapper<NoticeModel>();
			entityWrapper.like("NOTICE_TITLE",conditon);
			page.setRecords(noticeMapper.selectNoticesByPageAndCondition(page, entityWrapper));
		}else{
			page.setRecords(noticeMapper.selectNoticesByPage(page));
		}
		return page;
	}

	@Override
	public int deleteNoticeById(String id) throws Exception{
		return noticeMapper.deleteById(id);
	}

	@Override
	public int getAllNoticeNumByCondition(String condition) throws Exception {
		EntityWrapper<SysNotice> entityWrapper = new EntityWrapper<SysNotice>();
		if(!StringUtil.isEmpty(condition)){
			entityWrapper.like("NOTICE_TITLE",condition);
		}
		return noticeMapper.selectCount(entityWrapper);
	}

}
