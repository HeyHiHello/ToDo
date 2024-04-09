package com.todo.RequestBodyItems;

import java.time.LocalDateTime;

public class ProjectForm {
	private final String title;
	private final String description;
	private final LocalDateTime expireAt;
	
	
	public ProjectForm(String title, String description, LocalDateTime expireAt) {
		super();
		this.title = title;
		this.description = description;
		this.expireAt = expireAt;
	}


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public LocalDateTime getExpireAt() {
		return expireAt;
	}
	
	
}
