package com.copeapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="surveys")
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="surveysGenerator")
	@SequenceGenerator(name="surveysGenerator", sequenceName="surveys_sequence")
	private int surveyId; 
	
	@NonNull
	private String question;
	
	private int answersNumber;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeSurveyDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User deleteUser; //chi ha cancellato il sondaggio in data deleteDate
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User insertUser;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "surveyVoters_roles",
				joinColumns = { @JoinColumn(name = "surveyId") },
				inverseJoinColumns = { @JoinColumn(name = "roleId") } )
	private List<Role> surveyVotersRoles;
	
	@NonNull
	@ManyToMany
	@JoinTable( name = "surveyViewers_roles",
				joinColumns = { @JoinColumn(name = "surveyId") },
				inverseJoinColumns = { @JoinColumn(name = "roleId") } )
	private List<Role> surveyViewersRoles;
}
