package com.copeapp.dto.survey;

import lombok.Data;

@Data
public class SurveyRequestListDTO {
	
	private boolean isActive; //true=attivi false=chiusi
	
	private String keyword;
	
	private int idUtente;
	
	private int lastSurveyNumber;
	
	private int numberToRetrieve;
	
	//possibile implementazione dei filtri
}
