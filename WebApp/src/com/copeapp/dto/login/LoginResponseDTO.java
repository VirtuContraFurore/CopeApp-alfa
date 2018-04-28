package com.copeapp.dto.login;

import com.copeapp.dto.commons.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

	@NonNull
	private UserDTO user = null;
	
}
