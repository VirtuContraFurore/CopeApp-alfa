package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usersGenerator")
	@SequenceGenerator(name="usersGenerator", sequenceName="users_sequence")
	private Integer userId;
	
	private String mail;
	
	@NonNull
	private String firstname;
	
	@NonNull
	private String lastname;
	
	@NonNull
	private String username;
	
	@NonNull
	private String classe;
	
	@NonNull
	private String sezione;
	
	@NonNull
	private String password;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "users_roles",
				joinColumns = { @JoinColumn(name = "userId") },
				inverseJoinColumns = { @JoinColumn(name = "roleId") } )
	private List<Role> roles;
	
	private String imageUrl;
	
	private String wallpaper;
	
	//Set firstEntry parameter as true to show FirstEntry page
	@NonNull
	private Boolean firstEntry;
	
}