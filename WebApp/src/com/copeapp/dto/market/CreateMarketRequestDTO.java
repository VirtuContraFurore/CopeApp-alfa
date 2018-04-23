package com.copeapp.dto.market;

import java.util.Date;
import java.util.List;

import com.sun.istack.internal.NotNull;

import lombok.Data;
import lombok.Getter;

@Getter
public class CreateMarketRequestDTO {

	@NotNull private String name = null;
	private String description = null;
	@NotNull private Date openDate = null;
	@NotNull private Date expireDate = null;
	@NotNull private Data visibleDate = null;
	@NotNull private Data hiddenDate = null;
	@NotNull private Data creationDate = null;
	@NotNull private Integer creatorId = null;
	@NotNull private List<Integer> marketElementsIds = null;
}
