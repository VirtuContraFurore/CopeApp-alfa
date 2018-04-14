package com.copeapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rolesGenerator")
	@SequenceGenerator(name="rolesGenerator", sequenceName="roles_sequence")
	private int roleId;
	
	private String role;
	private String description;
	
	public Role() {
		super();
	}
	public Role(int roleId, String role, String description) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.description = description;
	}
	public Role(String role, String description) {
		super();
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
