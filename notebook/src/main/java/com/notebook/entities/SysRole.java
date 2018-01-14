package com.notebook.entities;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

public class SysRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private int roleId;

    private String role;

    private String description;

    private int roleState;
    
    @TableField(exist=false)
    private List<SysPermission> permissions;

    public List<SysPermission> getPermissions() {
    	return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
    	this.permissions = permissions;
    }
    
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public int getRoleState() {
        return roleState;
    }

    public void setRoleState(int roleState) {
        this.roleState = roleState;
    }
}