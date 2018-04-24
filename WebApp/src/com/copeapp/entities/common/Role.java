package com.copeapp.entities.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rolesGenerator")
	@SequenceGenerator(name="rolesGenerator", sequenceName="roles_sequence")
	private int roleId;
	
	@NotNull
	private String role;
	
	@NotNull
	private String description;
}
