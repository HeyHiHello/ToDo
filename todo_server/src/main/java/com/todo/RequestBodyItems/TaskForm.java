package com.todo.RequestBodyItems;

import java.time.LocalDateTime;

public class TaskForm {
	private final String title;
	private final String description;
	private final LocalDateTime expireAt;
	private final boolean isDone;
	private final int priority;
	private final int projectId;
	
	public TaskForm(String title, String description, LocalDateTime expireAt, boolean isDone, int priority, int projectId) {
		this.title = title;
		this.description = description;
		this.expireAt = expireAt;
		this.isDone = isDone;
		this.priority = priority;
		this.projectId = projectId;
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
	
	public boolean getDone() {
		return isDone;
	}
	public int getPriority() {
		return priority;
	}

	public int getProjectId() {
		return projectId;
	}
	
	
}
