package com.newsfeed.user.managers;

import org.springframework.stereotype.Component;

import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;
import com.newsfeed.user.repo.UserRepo;

@Component
public class UserManager {
	private UserRepo _userRepo;
	public UserManager(UserRepo userRepo) {
		_userRepo = userRepo;
	}

	public boolean createUser(User user) {
		return _userRepo.createUser(user);
	}
	
	public Integer loginUser(UserLoginRequest loginRequest) {
		return _userRepo.loginUser(loginRequest);
	}

}
