package com.copeapp.dto.login;

import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@NotNull String mail = null;
	@NotNull String password = null;
}
