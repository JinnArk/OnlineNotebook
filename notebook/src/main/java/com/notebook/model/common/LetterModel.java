package com.notebook.model.common;

import java.util.Date;

import com.notebook.util.DateUtil;


/**
 * 
 * @author 2ing
 * @createTime 2018-01-25
 * @remarks	站内信model
 */
public class LetterModel {
	private Integer letterID;
	private Integer senderID;
	private Integer recipientID;
	private Integer replyLetterID;
	private String letterTitle;
	private String letterContent;
	private Integer letterState;
	private Date createDate;
	private String username;
	private Integer userID;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getLetterID() {
		return letterID;
	}
	public void setLetterID(Integer letterID) {
		this.letterID = letterID;
	}
	public Integer getSenderID() {
		return senderID;
	}
	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}
	public Integer getRecipientID() {
		return recipientID;
	}
	public void setRecipientID(Integer recipientID) {
		this.recipientID = recipientID;
	}
	public Integer getReplyLetterID() {
		return replyLetterID;
	}
	public void setReplyLetterID(Integer replyLetterID) {
		this.replyLetterID = replyLetterID;
	}
	public String getLetterTitle() {
		return letterTitle;
	}
	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}
	public String getLetterContent() {
		return letterContent;
	}
	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}
	public Integer getLetterState() {
		return letterState;
	}
	public void setLetterState(Integer letterState) {
		this.letterState = letterState;
	}
	public String getCreateDate() {
		return DateUtil.dateToString(createDate);
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
