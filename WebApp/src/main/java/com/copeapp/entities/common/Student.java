package com.copeapp.entities.common;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
@Table(name="students")
//@DiscriminatorValue(value = "student")
public class Student extends User {
	
	@NonNull
	@ManyToOne(targetEntity = Classe.class, fetch = FetchType.LAZY)
	private Classe classe;

}
