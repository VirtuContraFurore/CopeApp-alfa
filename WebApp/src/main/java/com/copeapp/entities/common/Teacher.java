package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name = "teacherId", referencedColumnName = "userId")
@DiscriminatorValue("teacher")
public class Teacher extends User {
	
	public Teacher(String username, String password, String firstname, String lastname, List<Role> roles,
			Boolean firstEntry, Classe coordinatorOf, List<Subject> subjects, List<Classe> classi) {
		super(username, password, firstname, lastname, roles, firstEntry);
		this.coordinatorOf = coordinatorOf;
		this.subjects = subjects;
		this.classi = classi;
	}
	public Teacher(String username, String password, String firstname, String lastname, List<Role> roles,
			Boolean firstEntry, List<Subject> subjects, List<Classe> classi) {
		super(username, password, firstname, lastname, roles, firstEntry);
		this.subjects = subjects;
		this.classi = classi;
	}
	public Teacher(String username, String password, String firstname, String lastname, List<Role> roles,
			Boolean firstEntry) {
		super(username, password, firstname, lastname, roles, firstEntry);
	}
	
	//TODO in 2.0 collegamento all'orario del prof
	@JoinColumn(name="coordinatorOf")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Classe coordinatorOf;
	
	@NonNull
//	@ElementCollection(targetClass = Subject.class) //Guarda che in realtà non è un errore NON CANCELLARE
	@ManyToMany
	private List<Subject> subjects;
	
	@NonNull
	@ManyToMany
//	@ElementCollection(targetClass = Classe.class) //Guarda che in realtà non è un errore NON CANCELLARE
	private List<Classe> classi;
	
}
