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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "teacherId")
@Entity
@Table(name="teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="teachersGenerator")
	@SequenceGenerator(name="teachersGenerator", sequenceName="teachers_sequence", allocationSize = 1, initialValue = 50)
	private Integer teacherId;

	@NonNull
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String mail;

	@NonNull
	private String password;
	
	@NonNull
	private String firstname;
	
	@NonNull
	private String lastname;
	
	@NonNull
	//TODO joinnare le classi
	private List<Class> classe;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "teachers_roles",
				joinColumns = { @JoinColumn(name = "teacherId") },
				inverseJoinColumns = { @JoinColumn(name = "roleId") } )
	private List<Role> roles;
	
	private String imageUrl;
	
	private String wallpaper;
	
	//Set firstEntry parameter as true to show FirstEntry page
	@NonNull
	private Boolean firstEntry;
	
}