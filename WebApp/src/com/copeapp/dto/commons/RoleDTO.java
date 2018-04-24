package com.copeapp.dto.commons;

import com.copeapp.entities.common.Role;

import lombok.Data;

@Data
public class RoleDTO {
	
	private Integer roleId = null;
	private String role = null;
	private String description = null;
	
	public RoleDTO() {}
	
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
}
