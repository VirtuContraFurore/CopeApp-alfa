package com.copeapp.entities.survey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.copeapp.entities.common.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="votes")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="votesGenerator")
	@SequenceGenerator(name="votesGenerator", sequenceName="votes_sequence")
	private int voteId;
	
	@NonNull
	@ManyToOne
	private Answer answer;
	
	@NonNull
	@ManyToOne
	private User user;
	
}
