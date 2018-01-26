package com.notebook.entities;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.notebook.util.DateUtil;

public class UserNote {
	@TableId
    private Integer noteId;

    private Integer notetagId;

    private Integer userId;

    private String noteTitle;

    private Integer noteState;

    private Date createDate;

    private Date modifyDate;

    private String noteContent;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getNotetagId() {
        return notetagId;
    }

    public void setNotetagId(Integer notetagId) {
        this.notetagId = notetagId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle == null ? null : noteTitle.trim();
    }

    public Integer getNoteState() {
        return noteState;
    }

    public void setNoteState(Integer noteState) {
        this.noteState = noteState;
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

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
    }
}