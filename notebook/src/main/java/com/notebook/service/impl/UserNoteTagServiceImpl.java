package com.notebook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.UserNoteMapper;
import com.notebook.dao.UserNoteTagMapper;
import com.notebook.entities.UserNote;
import com.notebook.entities.UserNotetag;
import com.notebook.service.UserNoteTagService;
import com.notebook.util.CommonUtil;
import com.notebook.util.StringUtil;

@Service
public class UserNoteTagServiceImpl implements UserNoteTagService{
	
	@Autowired
	private UserNoteMapper userNoteMapper;
	
	@Autowired
	private UserNoteTagMapper userNoteTagMapper;
	
	@Override
	public void updateUserNoteTagState(String noteTagID, int state) throws Exception {
		UserNotetag userNoteTag = userNoteTagMapper.selectById(noteTagID);
		userNoteTag.setNotetagState(state);
		userNoteTagMapper.updateById(userNoteTag);
		
		Map<String, Object> noteTagIDMap = new HashMap<String,Object>();
		noteTagIDMap.put("NOTETAG_ID", userNoteTag.getNotetagId());
		List<UserNote> userNotes = userNoteMapper.selectByMap(noteTagIDMap);
		if(userNotes != null && !userNotes.isEmpty()){
			List<String> noteIDs = new ArrayList<String>();
			for(UserNote note:userNotes){
				noteIDs.add(note.getNoteId().toString());
			}
			userNoteMapper.updateNotesState(String.valueOf(state), noteIDs);
		}
	}

	@Override
	public Page<UserNotetag> getUserNoteTagByPageAndCondition(Page<UserNotetag> page,
			String userID, String createDate) throws Exception {
		EntityWrapper<UserNotetag> entityWrapper = wrapperCreate(userID, createDate);
		return page.setRecords(userNoteTagMapper.selectNoteTagByPageAndCondition(page, entityWrapper));
	}

	@Override
	public int getUserNoteTagNumByCondition(String userID, String createDate)
			throws Exception {
		EntityWrapper<UserNotetag> entityWrapper = wrapperCreate(userID, createDate);
		return userNoteTagMapper.selectCount(entityWrapper);
	}

	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月26日
	 * @remarks 自定义wrapper构造方法
	 */
	private static EntityWrapper<UserNotetag> wrapperCreate(String userID, String createDate) throws Exception{
		EntityWrapper<UserNotetag> entityWrapper = new EntityWrapper<UserNotetag>();
		StringBuffer dateSql = null;
		if(!StringUtil.isEmpty(createDate)){
			dateSql = new StringBuffer();
			dateSql.append("TO_DAYS(CREATE_DATE)=TO_DAYS('").append(createDate).append("')");
		}
		if(!StringUtil.isEmpty(userID)){
			entityWrapper.where("USER_ID="+userID);
			if(dateSql!=null){
				entityWrapper.and(dateSql.toString());
			}
		}else{
			if(dateSql!=null){
				entityWrapper.where(dateSql.toString());
			}
		}
		return entityWrapper;
	}

	@Override
	public int saveUserNoteTag(String noteTagID, String remark,
			String noteTagName, String userID, int state, int saveType)
			throws Exception {
		UserNotetag noteTag = null;
		if(saveType==0){
			noteTag = new UserNotetag();
			noteTag.setNotetagId(CommonUtil.getNum());
			noteTag.setNotetagName(noteTagName);
			//noteTag.setNotetagRemark(remark);
			noteTag.setNotetagState(1);
			noteTag.setUserId(Integer.valueOf(userID));
			Date now = new Date();
			noteTag.setCreateDate(now);
			noteTag.setModifyDate(now);
			return userNoteTagMapper.insert(noteTag);			
		}else if(saveType==1){
			noteTag = userNoteTagMapper.selectById(noteTagID);
			noteTag.setNotetagName(noteTagName);
			//noteTag.setNotetagRemark(remark);
			Date now = new Date();
			noteTag.setModifyDate(now);
			return userNoteTagMapper.updateById(noteTag);	
		}else if(saveType==2){
			noteTag = userNoteTagMapper.selectById(noteTagID);
			noteTag.setNotetagState(state);
			return userNoteTagMapper.updateById(noteTag);
		}else{
			return 3;//出现了其他数字
		}
	}
}
