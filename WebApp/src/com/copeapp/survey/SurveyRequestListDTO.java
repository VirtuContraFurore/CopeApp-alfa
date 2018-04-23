package com.copeapp.survey;

import lombok.Data;

@Data
public class SurveyRequestListDTO {
	
	private boolean tipo; //true=attivi false=chiusi
	
	private String keyword;
	
	private int idUtente;
	
	//possibile implementazione dei filtri
}
