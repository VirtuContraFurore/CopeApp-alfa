package com.copeapp.dto.commons;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	private int userId;
	private String username;
	private String mail;
	private String password;
	private String firstname;
	private String lastname;
	private String classe;
	private String section;
	private List<RoleDTO> roles;
	private String imageUrl;
	private String wallpaper;
	private boolean firstEntry; //Mettere true per mostrare la pagina di first entry
	
}