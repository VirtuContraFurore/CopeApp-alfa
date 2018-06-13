package com.copeapp.entities.common;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
@Table(name="students")
@PrimaryKeyJoinColumn(name = "studentId", referencedColumnName = "userId")
@DiscriminatorValue("student")
public class Student extends User {
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Classe classe;
	
	//TODO collegare voti dello studente

}
