package com.copeapp.entities.survey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name="answerscontents")
public class AnswerContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="answersContentsGenerator")
	@SequenceGenerator(name="answersContentsGenerator", sequenceName="answersContents_sequence")
	private Integer answerContentId;
	
	@NonNull
	private String answerText;
	
	private String answerImage;

}
