package com.newsfeed.user.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class UserLoginRequest {
	@JsonSerialize
	private String emailId;
	@JsonSerialize
	private String password;

	public UserLoginRequest() {

	}

	public UserLoginRequest(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmailId() {
		return this.emailId;
	}
}
