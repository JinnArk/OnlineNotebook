package com.notebook.model.admin;

/**
 * 
 * @author 2ing
 * @createTime 2018年1月23日
 * @remarks 管理员-首页model
 */
public class AdminIndexModel {
	private Integer newEventNUM;
	private Integer activeUserNUM;
	private Integer newUserNUM;
	private Integer allUserNUM;
	
	public Integer getNewEventNUM() {
		return newEventNUM;
	}
	public void setNewEventNUM(Integer newEventNUM) {
		this.newEventNUM = newEventNUM;
	}
	public Integer getActiveUserNUM() {
		return activeUserNUM;
	}
	public void setActiveUserNUM(Integer activeUserNUM) {
		this.activeUserNUM = activeUserNUM;
	}
	public Integer getNewUserNUM() {
		return newUserNUM;
	}
	public void setNewUserNUM(Integer newUserNUM) {
		this.newUserNUM = newUserNUM;
	}
	public Integer getAllUserNUM() {
		return allUserNUM;
	}
	public void setAllUserNUM(Integer allUserNUM) {
		this.allUserNUM = allUserNUM;
	}
}
