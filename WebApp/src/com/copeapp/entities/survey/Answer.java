package com.copeapp.entities.survey;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="answers")
public class Answer {
//	TODO aggiungere url per immagine
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="answersGenerator")
	@SequenceGenerator(name="answersGenerator", sequenceName="answers_sequence")
	private Integer answerId; 
	
	@NonNull
	private String answerContent;
	
	@NonNull
	private Integer votesNumber;
	
	@NonNull
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "answer"
	)
	private List<Vote> votes;
}
