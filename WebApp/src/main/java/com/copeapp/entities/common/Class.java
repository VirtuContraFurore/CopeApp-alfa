package com.copeapp.entities.common;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="classes")
public class Class {
	
	@Id
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="classesGenerator")
	@SequenceGenerator(name="classesGenerator", sequenceName="classes_sequence", allocationSize = 1, initialValue = 50)
	private Integer classId;
	
	@NonNull
	private Integer classNumber;
	
	@NonNull
	private String classSection;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "teachers_classes",
				joinColumns = { @JoinColumn(name = "classId") },
				inverseJoinColumns = { @JoinColumn(name = "userId") } )
	private List<User> teachers;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "students_classes",
				joinColumns = { @JoinColumn(name = "classId") },
				inverseJoinColumns = { @JoinColumn(name = "userId") } )
	private List<User> students;

}
