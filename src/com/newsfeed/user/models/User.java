package com.newsfeed.user.models;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonSerialize
	private int userId;
	@JsonSerialize
	private String firstName;
	@JsonSerialize
	private String lastName;
	@JsonSerialize
	private String emailId;
	@JsonSerialize
	private String password;

	public User() {

	}

	public User(int userId, String firstName, String lastName, String emailId, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}

	public User(String firstName, String lastName, String emailId, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public String getPassword() {
		return this.password;
	}

}
