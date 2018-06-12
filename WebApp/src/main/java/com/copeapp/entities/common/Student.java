package com.copeapp.entities.common;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.copeapp.entities.common.Class;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User {
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Class classe;

}
