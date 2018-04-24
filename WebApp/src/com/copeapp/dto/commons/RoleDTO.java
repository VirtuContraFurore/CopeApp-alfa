package com.copeapp.dto.commons;

import com.copeapp.entities.common.Role;

public class RoleDTO {
	
	private int roleId;
	private String role;
	private String description;
	
	public RoleDTO() {
		super();
	}
	public RoleDTO(Role role) {
		super();
		this.roleId = role.getRoleId();
		this.role = role.getRole();
		this.description = role.getDescription();
	}
	public RoleDTO(int roleId, String role, String description) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.description = description;
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
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
