package com.copeapp.entities;

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
@Table(name = "market_elements")
public class MarketElement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketElementGenerator")
	@SequenceGenerator(name="marketElementGenerator", sequenceName="market_elements_sequence")
	private int marketElementId;
	
	@Column(nullable = false)
	private String name = null;
	
	@Column(nullable = true)
	private String description = null;
	
	@Column(nullable = false)
	private int price = 0;
	
	@Column(nullable = false)
	private String image = null;
}
