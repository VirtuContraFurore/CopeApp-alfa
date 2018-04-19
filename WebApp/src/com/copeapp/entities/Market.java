package com.copeapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "markets")
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketGenerator")
	@SequenceGenerator(name="marketGenerator", sequenceName="market_sequence")
	private Integer marketId;
	
	@NonNull
	private String name;

	@NonNull
	private String description;
	
	@NonNull
	private Date openDate;
	
	@NonNull
	private Date expireDate;
	
	/*
	@Column(nullable = false)
	@ManyToOne
	@JoinColumn(name = "marketId")
	private User owner = null;
	*/
}
