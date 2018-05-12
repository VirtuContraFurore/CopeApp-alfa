package com.copeapp.entities.market;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.NonNull;

import lombok.Data;

@Data
@Entity
@Table(name = "market_elements")
public class MarketElement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketElementGenerator")
	@SequenceGenerator(name="marketElementGenerator", sequenceName="market_elements_sequence")
	private Integer marketElementId;
	
	@NonNull
	private String name = null;
	
	private String description = null;
	
	@NonNull
	private Double price = null;
	
	private String image = null;
	
}
