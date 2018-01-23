package com.notebook.entities;

import java.util.Date;

public class UserNotetag {
    private Integer notetagId;

    private Integer userId;

    private String notetagName;

    private Integer notetagState;

    private Date createDate;

    private Date modifyDate;

    private String notetagRemark;

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

    public String getNotetagName() {
        return notetagName;
    }

    public void setNotetagName(String notetagName) {
        this.notetagName = notetagName == null ? null : notetagName.trim();
    }

    public Integer getNotetagState() {
        return notetagState;
    }

    public void setNotetagState(Integer notetagState) {
        this.notetagState = notetagState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getNotetagRemark() {
        return notetagRemark;
    }

    public void setNotetagRemark(String notetagRemark) {
        this.notetagRemark = notetagRemark == null ? null : notetagRemark.trim();
    }
}