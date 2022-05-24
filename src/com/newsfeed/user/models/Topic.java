package com.newsfeed.user.models;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonSerialize
	private int topicId;
	@JsonSerialize
	private String topicName;
	
	public Topic() {
		
	}
	
	public Topic(int topicId, String topicName) {
		this.topicId = topicId;
		this.topicName = topicName;
	}

	public int getTopicId() {
		return topicId;
	}

	public String getTopicName() {
		return topicName;
	}
	
}
