package com.notebook.model.common;

import java.util.Date;

import com.notebook.util.DateUtil;


/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks	公告model
 */
public class NoticeModel {
	private Integer noticeID;
	private String noticeTitle;
	private String noticeContent;
	private Date createDate;
	private Date modifyDate;
	public Integer getNoticeID() {
		return noticeID;
	}
	public void setNoticeID(Integer noticeID) {
		this.noticeID = noticeID;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getCreateDate() {
		return DateUtil.dateToString(createDate);
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return DateUtil.dateToString(modifyDate);
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
