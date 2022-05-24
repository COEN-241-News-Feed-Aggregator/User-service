package com.newsfeed.user.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class UserTopics implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonSerialize
	public int userId;
	@JsonSerialize
    public List<Topic> userTopics;

    public UserTopics(int userId, List<Topic> userTopics) {
        this.userId = userId;
        this.userTopics = userTopics;
    }

	public int getUserId() {
		return userId;
	}

	public List<Topic> getUserTopics() {
		return userTopics;
	}

}
