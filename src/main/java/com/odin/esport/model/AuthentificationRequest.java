package com.odin.esport.model;

public class AuthentificationRequest {
	private String email;
	private String password;
	
	public AuthentificationRequest() {
		super();
	}
	public AuthentificationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
