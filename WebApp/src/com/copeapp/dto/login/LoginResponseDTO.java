package com.copeapp.dto.login;

import com.copeapp.dto.commons.UserDTO;

import lombok.Data;

@Data
public class LoginResponseDTO {

	private UserDTO user = null;
	
	public LoginResponseDTO (UserDTO user) {
		this.user = user;
	}
}
