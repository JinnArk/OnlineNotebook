package com.notebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notebook.dao.NoticeMapper;
import com.notebook.model.common.NoticeModel;
import com.notebook.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;
		
	@Override
	public List<NoticeModel> getIndexNoticeModel() throws Exception{
		return noticeMapper.selectIndexNoticeModel();
	}
	
}
