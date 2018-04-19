package com.copeapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rolesGenerator")
	@SequenceGenerator(name="rolesGenerator", sequenceName="roles_sequence")
	private int roleId;
	
	@NonNull
	private String role;
	
	@NonNull
	private String description;
}
