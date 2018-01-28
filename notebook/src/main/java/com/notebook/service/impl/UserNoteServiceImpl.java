package com.notebook.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.UserNoteMapper;
import com.notebook.entities.UserNote;
import com.notebook.service.UserNoteService;
import com.notebook.util.CommonUtil;
import com.notebook.util.StringUtil;

@Service
public class UserNoteServiceImpl implements UserNoteService{
	
	@Autowired
	private UserNoteMapper userNoteMapper;
	
	@Override
	public Page<UserNote> getUserNoteByPageAndCondition(Page<UserNote> page,
			String userID, String noteTagID, String createDate, String state) throws Exception {
		EntityWrapper<UserNote> entityWrapper = wrapperCreate(userID, noteTagID, createDate, state);
		return page.setRecords(userNoteMapper.selectNoteByPageAndCondition(page, entityWrapper));
	}

	@Override
	public int getUserNoteNumByCondition(String userID, String noteTagID, String createDate, String state)
			throws Exception {
		EntityWrapper<UserNote> entityWrapper = wrapperCreate(userID, noteTagID, createDate, state);
		return userNoteMapper.selectCount(entityWrapper);
	}
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks 自定义wrapper构造方法
	 */
	private static EntityWrapper<UserNote> wrapperCreate(String userID, String noteTagID
			, String createDate, String state) throws Exception{
		
		EntityWrapper<UserNote> entityWrapper = new EntityWrapper<UserNote>();
		StringBuffer dateSql = null;
		if(!StringUtil.isEmpty(createDate)){
			dateSql = new StringBuffer();
			dateSql.append("TO_DAYS(CREATE_DATE)=TO_DAYS('").append(createDate).append("')");
		}
		
		if(!StringUtil.isEmpty(userID)){
			entityWrapper.where("USER_ID="+userID);
			if(!StringUtil.isEmpty(noteTagID)){
				entityWrapper.and("NOTETAG_ID="+noteTagID);
			}
			if(dateSql!=null){
				entityWrapper.and(dateSql.toString());
			}
			if(!StringUtil.isEmpty(state)){
				entityWrapper.and("NOTE_STATE="+state);
			}
		}else{
			if(!StringUtil.isEmpty(noteTagID)){
				entityWrapper.where("NOTETAG_ID="+noteTagID);
			}
		}
		return entityWrapper;
	}

	@Override
	public int saveUserNote(String noteID, String noteTitle, String noteTagID,
			String userID, int state, String noteContent, int saveType) throws Exception {
		UserNote usernote = null;
		if(saveType==0){
			usernote = new UserNote();
			usernote.setNoteId(CommonUtil.getNum());
			usernote.setNoteTitle(noteTitle);
			usernote.setNoteContent(noteContent);
			usernote.setNoteState(1);
			usernote.setNotetagId(Integer.valueOf(noteTagID));
			usernote.setUserId(Integer.valueOf(userID));
			Date now = new Date();
			usernote.setCreateDate(now);
			usernote.setModifyDate(now);
			return userNoteMapper.insert(usernote);			
		}else if(saveType==1){
			usernote = userNoteMapper.selectById(noteID);
			usernote.setNoteTitle(noteTitle);
			usernote.setNoteContent(noteContent);
			Date now = new Date();
			usernote.setModifyDate(now);
			return userNoteMapper.updateById(usernote);	
		}else if(saveType==2){
			usernote = userNoteMapper.selectById(noteID);
			usernote.setNoteState(state);
			return userNoteMapper.updateById(usernote);
		}else{
			return 9527;//出现了其他数字
		}
	}

	@Override
	public UserNote getNoteByCondition(String noteID) throws Exception {
		return userNoteMapper.selectById(noteID);
	}

}
