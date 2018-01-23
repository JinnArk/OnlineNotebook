package com.notebook.model.common;

import java.util.Date;

import com.notebook.util.DateUtil;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks 登录记录model
 */
public class LoginRecordModel {
	private String loginIP;
	private Date loginDate;
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLoginDate() {
		return DateUtil.dateToString(loginDate);
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
}
