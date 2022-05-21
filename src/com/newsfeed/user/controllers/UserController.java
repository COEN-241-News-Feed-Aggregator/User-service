package com.newsfeed.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newsfeed.user.managers.UserManager;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;

@RestController
@RequestMapping(value = "/user", produces = {"application/json", "text/xml"})
public class UserController {
	private UserManager _userManager;

	public UserController(UserManager userManager) {
		_userManager = userManager;
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> createUser(@RequestBody User user) {
		boolean isUserCreated = _userManager.createUser(user);

		return isUserCreated?  new ResponseEntity<Boolean>(isUserCreated
				, HttpStatus.OK) :  new ResponseEntity<Boolean>(isUserCreated
						, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> login(@RequestBody UserLoginRequest loginRequest) {
		Integer userId = _userManager.loginUser(loginRequest);

		return userId==-1?  new ResponseEntity<Integer>(userId
				, HttpStatus.OK) : new ResponseEntity<Integer>(userId
				, HttpStatus.OK);
	}

}
