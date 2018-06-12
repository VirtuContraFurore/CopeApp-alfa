package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.Entity;
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
//@DiscriminatorValue(value = "teacher")
public class Teacher extends User {
	
	//TODO in 2.0 collegamento all'orario del prof
	
	@NonNull
	private List<Subject> subjects;
	
	@NonNull
	private List<Classe> classi;
	
}
