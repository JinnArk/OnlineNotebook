package com.notebook.entities;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.notebook.util.DateUtil;

public class SysNotice {
	
	@TableId
    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date createDate;

    private Date modifyDate;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
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