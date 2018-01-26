package com.notebook.entities;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;

public class SysLetter {
	@TableId
    private Integer letterId;

    private Integer senderId;

    private Integer recipientId;

    private Integer replyLetterId;

    private String letterTitle;

    private Integer letterState;

    private Date createDate;

    private String letterContent;

    public Integer getLetterId() {
        return letterId;
    }

    public void setLetterId(Integer letterId) {
        this.letterId = letterId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getReplyLetterId() {
        return replyLetterId;
    }

    public void setReplyLetterId(Integer replyLetterId) {
        this.replyLetterId = replyLetterId;
    }

    public String getLetterTitle() {
        return letterTitle;
    }

    public void setLetterTitle(String letterTitle) {
        this.letterTitle = letterTitle == null ? null : letterTitle.trim();
    }

    public Integer getLetterState() {
        return letterState;
    }

    public void setLetterState(Integer letterState) {
        this.letterState = letterState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent == null ? null : letterContent.trim();
    }
}