package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name = "teacherId", referencedColumnName = "userId")
@DiscriminatorValue("teacher")
public class Teacher extends User {
	
	//TODO in 2.0 collegamento all'orario del prof
	@JoinColumn(name="coordinatorOf")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Classe coordinatorOf;
	
	@NonNull
	@ElementCollection(targetClass = Subject.class) //Guarda che in realtà non è un errore NON CANCELLARE
	private List<Subject> subjects;
	
	@NonNull
	@ElementCollection(targetClass = Classe.class) //Guarda che in realtà non è un errore NON CANCELLARE
	private List<Classe> classi;
	
}
