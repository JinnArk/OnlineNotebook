package com.notebook.entities;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

//mybatis使用通用mapper,不需要@Table注释，但一定要@Id注释
//换成mybatis-plus之后开启大小下划线写判断，db-column-underline(表)，capital-mode(数据库)，所以不需要TableName和TableField注释了
//但是由于roles是多对多外键，所以需要单独注释 exist=false(表示表中不存在)
public class UserInfo {
	
    private Integer id;

    private Integer uid;

    private String username;

    private String password;

    private String nickname;

    private String salt;

    private Integer state;

    private Date createDate;

    private Date loginDate;
    
    //定义角色属性
    @TableField(exist=false)
    private List<SysRole> roles;

    public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}