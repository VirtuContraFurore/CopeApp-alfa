package com.copeapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "market_elements")
public class MarketElement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketElementGenerator")
	@SequenceGenerator(name="marketElementGenerator", sequenceName="market_elements_sequence")
	private int marketElementId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private String image;
	
	public MarketElement () {}
	
	public MarketElement (String name, String description, int price, String image) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}
}
