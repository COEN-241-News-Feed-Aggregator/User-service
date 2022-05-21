package com.newsfeed.user.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.newsfeed.user.managers.UserManager;
import com.newsfeed.user.models.User;
import com.newsfeed.user.models.UserLoginRequest;
import com.newsfeed.user.repo.UserRepo;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		User user = new User("P","K","pk@gamil.com","pk");
		UserRepo ur = new UserRepo();
		UserManager um = new UserManager(ur);
		UserLoginRequest ulr = new UserLoginRequest("test@gmail.com","test123");
		
//		System.out.println(um.createUser(user));
		System.out.println(um.loginUser(ulr));
	}

}
