package com.copeapp.dto.commons;

import java.util.ArrayList;

public class UserDTO {

	private int userId;
	private String mail;
	private String firstname;
	private String lastname;
	private String username;
	private String classe;
	private String sezione;
	private String password;
	private ArrayList<RoleDTO> roles;
	private String imageUrl;
	private String wallpaper;
	private boolean firstEntry; // mettere true per mostrare la pagina di first entry
	
	public UserDTO(int userId, String mail, String firstname, String lastname, String username, String classe,
			String sezione, String password, ArrayList<RoleDTO> roles, String imageUrl, String wallpaper,
			boolean firstEntry) {
		super();
		this.userId = userId;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.classe = classe;
		this.sezione = sezione;
		this.password = password;
		this.roles = roles;
		this.imageUrl = imageUrl;
		this.wallpaper = wallpaper;
		this.firstEntry = firstEntry;
	}
	public UserDTO() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<RoleDTO> roles) {
		this.roles = roles;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getWallpaper() {
		return wallpaper;
	}
	public void setWallpaper(String wallpaper) {
		this.wallpaper = wallpaper;
	}
	public boolean isFirstEntry() {
		return firstEntry;
	}
	public void setFirstEntry(boolean firstEntry) {
		this.firstEntry = firstEntry;
	}

	
}