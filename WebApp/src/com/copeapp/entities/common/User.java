package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usersGenerator")
	@SequenceGenerator(name="usersGenerator", sequenceName="users_sequence")
	private Integer userId = null;
	
	@Column(unique = true)
	private String mail = null;
	
	@NotNull
	private String firstname = null;
	
	@NotNull
	private String lastname = null;
	
	@NotNull
	@Column(unique = true)
	private String username = null;
	
	@NotNull
	private String classe = null;
	
	@NotNull
	private String sezione = null;
	
	@NotNull
	private String password = null;
	
	@NotNull
	@ManyToMany
	@JoinTable( name = "users_roles",
				joinColumns = { @JoinColumn(name = "userId") },
				inverseJoinColumns = { @JoinColumn(name = "roleId") } )
	private List<Role> roles = null;
	
	private String imageUrl = null;
	
	private String wallpaper = null;
	
	//Set firstEntry parameter as true to show FirstEntry page
	@NotNull
	private Boolean firstEntry = null;
	
}