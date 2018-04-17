package com.copeapp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "markets")
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketGenerator")
	@SequenceGenerator(name="marketGenerator", sequenceName="market_sequence")
	private int marketId;
	
	@Column(nullable = false)
	private String name = null;

	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false)
	private Date openDate = null;
	
	@Column(nullable = false)
	private Date expireDate = null;
	
	/*
	@Column(nullable = false)
	@ManyToOne
	@JoinColumn(name = "marketId")
	private User owner = null;
	*/
}
