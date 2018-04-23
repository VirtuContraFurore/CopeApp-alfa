package com.copeapp.entities.market;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.copeapp.entities.common.User;
import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "markets")
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="marketGenerator")
	@SequenceGenerator(name="marketGenerator", sequenceName="market_sequence")
	private Integer marketId = null;
	
	@NotNull
	private String name = null;

	private String description = null;
	
	@NotNull
	private Date openDate = null;
	
	@NotNull
	private Date expireDate = null;
	
	@NotNull
	private Data visibleDate = null;
	
	@NotNull
	private Data hiddenDate = null;
	
	@NotNull
	private Data creationDate = null;
	
	private Data eliminationDate = null;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "creatorId")
	private User creator = null;
	
	@ManyToOne
	@JoinColumn(name = "eliminatorId")
	private User eliminator = null;
	
	@NotNull
	@ManyToMany
	@JoinTable( name = "markets_elements",
			joinColumns = { @JoinColumn(name = "marketId") },
			inverseJoinColumns = { @JoinColumn(name = "marketElementId") } )
	private List<MarketElement> marketElements = null;
	
	/*
	 * openDate: data dalla quale il market è aperto al pubblico
	 * expireDate: data dalla quale il market viene chiuso al pubblico
	 * visibleDate: data dalla quale il market è visibile nell'elenco dei market
	 * 				(tale data deve precedere o essere coincidente alla openDate)
	 * hiddenDate: data dalla quale il market non è più visibile nell'elenco dei market
	 * 			   (tale data deve succedere o essere coincidente alla expireDate)
	 * creationDate: data di creazione del market
	 * eliminationDate: data di eliminazione del market
	 * marketElements: lista degli elementi che il market offre
	 */
}
