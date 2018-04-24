package com.copeapp.dto.commons;

import java.util.ArrayList;
import java.util.List;

import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;

import lombok.Data;

@Data
public class UserDTO {

	private int userId;
	private String mail;
	private String firstname;
	private String lastname;
	private String username;
	private String classe;
	private String sezione;
	private String password;
	private List<RoleDTO> roles;
	private String imageUrl;
	private String wallpaper;
	private boolean firstEntry; //Mettere true per mostrare la pagina di first entry
	
	public UserDTO(User user) {
		super();
		userId = user.getUserId();
		mail = user.getMail();
		firstname = user.getFirstname();
		lastname = user.getLastname();
		username = user.getUsername();
		classe = user.getClasse();
		sezione = user.getSezione();
		password = user.getPassword();
		roles = new ArrayList<>();
		for (Role role : user.getRoles()) { roles.add(new RoleDTO(role)); }
		imageUrl = user.getImageUrl();
		wallpaper = user.getWallpaper();
		firstEntry = user.getFirstEntry();
	}
}