package com.copeapp.dto.commons;

import com.copeapp.entities.common.Classe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends UserDTO {
	
	private Classe classe;

}
