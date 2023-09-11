package com.loginsystem.entities;

import lombok.Builder;

@Builder
public class JWTResponse {
	
	private String jwtToken;
	private String username;
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public JWTResponse(String jwtToken, String username) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
	}
	public JWTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JWTResponse [jwtToken=" + jwtToken + ", username=" + username + "]";
	}
	

}
