package com.notebook.entities;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.notebook.util.DateUtil;

public class UserNotetag {
	@TableId
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

    public String getNotetagRemark() {
        return notetagRemark;
    }

    public void setNotetagRemark(String notetagRemark) {
        this.notetagRemark = notetagRemark == null ? null : notetagRemark.trim();
    }
}