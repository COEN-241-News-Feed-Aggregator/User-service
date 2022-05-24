package com.newsfeed.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
////		User user = new User("P","K","pk@gamil.com","pk");
//		UserRepo ur = new UserRepo();
////		UserManager um = new UserManager(ur);
////		UserLoginRequest ulr = new UserLoginRequest("test@gmail.com","test123");
////		
//////		System.out.println(um.createUser(user));
////		System.out.println(um.loginUser(ulr));
//		List<Topic> list = new ArrayList<>();
//		list.add(new Topic(4, "Technology"));
//		list.add(new Topic(3, ""));
//		list.add(new Topic(5, ""));
//		ur.followTopic(new UserTopics(21, list));
		
	}

}
