package com.notebook.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.notebook.dao.LetterMapper;
import com.notebook.entities.SysLetter;
import com.notebook.entities.UserInfo;
import com.notebook.model.common.LetterModel;
import com.notebook.service.LetterService;
import com.notebook.util.CommonUtil;
import com.notebook.util.StringUtil;

@Service
public class LetterServiceImpl implements LetterService{
	@Autowired
	LetterMapper letterMapper; 

	@Override
	public Page<LetterModel> getLetterModelsByPageAndConditionForRecipient(
			Page<LetterModel> page, int[] letterState, int recipientID,
			String createDate) throws Exception {

		StringBuffer letterStateSql = new StringBuffer("LETTER_STATE IN(");
		for(int i=0;i<letterState.length;i++){
			if(i==0){
				letterStateSql.append(letterState[i]);
			}else{
				letterStateSql.append(",").append(letterState[i]);
			}
		}
		letterStateSql.append(")");
		
		EntityWrapper<LetterModel> entityWrapper = new EntityWrapper<LetterModel>();
		//.and("l.LETTER_STATE="+letterState)
		entityWrapper.where("u.USER_ID=l.SENDER_ID")
			.and(letterStateSql.toString())
			.and("l.RECIPIENT_ID="+recipientID);
		if(!StringUtil.isEmpty(createDate)){
			entityWrapper.and("TO_DAYS(l.CREATE_DATE)=TO_DAYS('"+createDate+"')");
		}
		
		//System.out.println(entityWrapper.toString());
		
		return page.setRecords(letterMapper.selectLetterByPageAndCondition(page, entityWrapper));
	}

	@Override
	public int getAllLetterNumByConditionForRecipient(int[] letterState, int recipientID, String createDate) throws Exception {
		
		StringBuffer letterStateSql = new StringBuffer("LETTER_STATE IN(");
		for(int i=0;i<letterState.length;i++){
			if(i==0){
				letterStateSql.append(letterState[i]);
			}else{
				letterStateSql.append(",").append(letterState[i]);
			}
		}
		letterStateSql.append(")");
		
		EntityWrapper<SysLetter> entityWrapper = new EntityWrapper<SysLetter>();
		//entityWrapper.where("LETTER_STATE IN("+letterState+","+")")
		entityWrapper.where(letterStateSql.toString())
			.and("RECIPIENT_ID="+recipientID);
		if(!StringUtil.isEmpty(createDate)){
			entityWrapper.and("TO_DAYS(CREATE_DATE)=TO_DAYS('"+createDate+"')");
		}
		return letterMapper.selectCount(entityWrapper);
	}

	@Override
	public int saveLetter(UserInfo sendUser, UserInfo recipientUser,
			String recipientName, String letterTitle, String letterContent, String replyLetterID)
			throws Exception {
		
			SysLetter letter = new SysLetter();
			letter.setLetterId(CommonUtil.getNum());
			letter.setLetterState(0);
			letter.setLetterTitle(letterTitle);
			letter.setLetterContent(letterContent);
			letter.setCreateDate(new Date());
			letter.setSenderId(sendUser.getUserId());
			letter.setRecipientId(recipientUser.getUserId());
			
			if(!StringUtil.isEmpty(replyLetterID)){//如果是回信的话，改变被回信的状态，同时填充回信ID字段
				SysLetter reletter = letterMapper.selectById(replyLetterID);
				reletter.setLetterState(1);
				letterMapper.updateAllColumnById(reletter);
				
				letter.setReplyLetterId(Integer.valueOf(replyLetterID));
			}
			
			return letterMapper.insert(letter);
	}

	@Override
	public int updateOneLetterState(String letterID) throws Exception {
		SysLetter letter = letterMapper.selectById(letterID);
		letter.setLetterState(2);//0-未读，1-已读已回复，2-已读未回复
		return letterMapper.updateById(letter);
	}

	@Override
	public int updateLettersState(String[] letterIDs) throws Exception {
		return letterMapper.updateLettersStateTOnoReply(letterIDs);
	}

	@Override
	public Page<LetterModel> getLetterModelsByPageAndConditionForSended(
			Page<LetterModel> page, int[] letterState,  int sendedID,
			String createDate) throws Exception {
		StringBuffer letterStateSql = new StringBuffer("LETTER_STATE IN(");
		for(int i=0;i<letterState.length;i++){
			if(i==0){
				letterStateSql.append(letterState[i]);
			}else{
				letterStateSql.append(",").append(letterState[i]);
			}
		}
		letterStateSql.append(")");
		
		EntityWrapper<LetterModel> entityWrapper = new EntityWrapper<LetterModel>();
		entityWrapper.where("u.USER_ID=l.RECIPIENT_ID")
			.and(letterStateSql.toString())
			.and("l.SENDER_ID="+sendedID);
		if(!StringUtil.isEmpty(createDate)){
			entityWrapper.and("TO_DAYS(l.CREATE_DATE)=TO_DAYS('"+createDate+"')");
		}
		return page.setRecords(letterMapper.selectLetterByPageAndCondition(page, entityWrapper));
	}

	@Override
	public int getAllLetterNumByConditionForSended(int[] letterState,
			int sendedID, String createDate) throws Exception {
		StringBuffer letterStateSql = new StringBuffer("LETTER_STATE IN(");
		for(int i=0;i<letterState.length;i++){
			if(i==0){
				letterStateSql.append(letterState[i]);
			}else{
				letterStateSql.append(",").append(letterState[i]);
			}
		}
		letterStateSql.append(")");
		EntityWrapper<SysLetter> entityWrapper = new EntityWrapper<SysLetter>();
		entityWrapper.where(letterStateSql.toString())
			.and("SENDER_ID="+sendedID);
		if(!StringUtil.isEmpty(createDate)){
			entityWrapper.and("TO_DAYS(CREATE_DATE)=TO_DAYS('"+createDate+"')");
		}
		return letterMapper.selectCount(entityWrapper);
	}
	
}
