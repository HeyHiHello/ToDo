package com.todo.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="projects")
public class Project {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name= "expireAt")
	private LocalDateTime expireAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	User user;
	
	@OneToMany(mappedBy="project")
	@JsonIgnore
	private List<Task> tasks;

	public Project() {
		
	}
	
	public Project(long id, String title, String description, LocalDateTime expireAt, User user, List<Task> tasks) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.expireAt = expireAt;
		this.user = user;
		this.tasks = tasks;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
}
