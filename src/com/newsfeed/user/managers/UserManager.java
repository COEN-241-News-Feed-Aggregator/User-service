package com.newsfeed.user.managers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newsfeed.user.models.Topic;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;
import com.newsfeed.user.models.UserTopics;
import com.newsfeed.user.repo.UserRepo;

@Component
public class UserManager {
	private UserRepo _userRepo;
	public UserManager(UserRepo userRepo) {
		_userRepo = userRepo;
	}

	public int createUser(User user) {
		return _userRepo.createUser(user);
	}
	
	public Integer loginUser(UserLoginRequest loginRequest) {
		return _userRepo.loginUser(loginRequest);
	}
	
	public List<Topic> getAllTopics() {
		return _userRepo.getAllTopics();
	}
	
	public List<Topic> getUserTopics(int userId) {
		return _userRepo.getUserTopics(userId);
	}
	
	public boolean followTopic(UserTopics userTopics) {
		return _userRepo.followTopic(userTopics);
	}

}
