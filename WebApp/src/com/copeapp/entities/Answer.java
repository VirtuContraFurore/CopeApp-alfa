package com.copeapp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="answersGenerator")
	@SequenceGenerator(name="answersGenerator", sequenceName="answers_sequence")
	private int answerId; 
	
	@NonNull
	private String answerContent;
	
	private int votesNumber;
	
	@NonNull
	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "answer"
	)
	private List<Vote> votes;
}
