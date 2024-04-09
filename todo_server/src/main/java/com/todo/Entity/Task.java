package com.todo.Entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="tasks")
public class Task {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="expire_at")
	private LocalDateTime expireAt;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="is_done")
	private boolean isDone;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	public Task() {
		
	}

	public Task(long id, String title, String description, LocalDateTime expireAt, int priority, boolean isDone,
			Project project) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.expireAt = expireAt;
		this.priority = priority;
		this.isDone = isDone;
		this.project = project;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(LocalDateTime expireAt) {
		this.expireAt = expireAt;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
