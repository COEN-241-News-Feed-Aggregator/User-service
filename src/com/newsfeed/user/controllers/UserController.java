package com.newsfeed.user.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.newsfeed.user.managers.UserManager;
import com.newsfeed.user.models.Topic;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;
import com.newsfeed.user.models.UserTopics;
 
@RestController
@RequestMapping(value = "/user", produces = {"application/json", "text/xml"})
public class UserController {
	private UserManager _userManager;

	public UserController(UserManager userManager) {
		_userManager = userManager;
	}

	@PostMapping("/create")
	public ResponseEntity<Integer> createUser(@RequestBody User user) {
		Integer userId = _userManager.createUser(user);

		return new ResponseEntity<Integer>(userId
				, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> login(@RequestBody UserLoginRequest loginRequest) {
		Integer userId = _userManager.loginUser(loginRequest);

		return new ResponseEntity<Integer>(userId
				, HttpStatus.OK);
	}
	
	@GetMapping("/getAllTopics")
	public ResponseEntity<List<Topic>> getAllTopics() {
		List<Topic> topics = _userManager.getAllTopics();

		return new ResponseEntity<List<Topic>>(topics
				, HttpStatus.OK);
	}
	
	@GetMapping("/getUserTopics/{userId}")
	public ResponseEntity<List<Topic>> getUserTopics(@PathVariable int userId) {
		List<Topic> topics = _userManager.getUserTopics(userId);

		return new ResponseEntity<List<Topic>>(topics
				, HttpStatus.OK);
	}
	
	@PostMapping("/followTopic")
	public ResponseEntity<Boolean> followTopic(@RequestBody UserTopics userTopics) {
		boolean isFollowed = _userManager.followTopic(userTopics);

		return isFollowed?  new ResponseEntity<Boolean>(isFollowed
				, HttpStatus.OK) :  new ResponseEntity<Boolean>(isFollowed
						, HttpStatus.OK);
	}

}
