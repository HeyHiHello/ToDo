package com.todo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todo.Repository.TaskRepository.TaskSpecificatons;
import com.todo.RequestBodyItems.TaskForm;
import com.todo.Entity.Task;
import com.todo.Entity.User;
import com.todo.Entity.Project;
import com.todo.Repository.TaskRepository;
import com.todo.Service.Exception.NotFoundInDBException;

@Service
public class UserTaskService {
	private TaskRepository taskRepository;
	private ProjectService projectService;
	
	public UserTaskService(TaskRepository taskRepository, ProjectService projectService) {
		this.taskRepository = taskRepository;
		this.projectService = projectService;
	}
	
	public Task getById(long id, User user) throws NotFoundInDBException {
		Optional<Task> taskOpt = taskRepository.findOne(TaskSpecificatons.byId(id).and(TaskSpecificatons.forUser(user)));
		
		if (taskOpt.isEmpty()) {
			throw new NotFoundInDBException("Task " + id + " not found in DB");
		}
		
		return taskOpt.get();
	}
	
	public List<Task> getByProject(long projectId, User user) throws NotFoundInDBException {
		Project project = projectService.getById(projectId);
		return taskRepository.findAll(
				TaskSpecificatons.forUser(user).and(
						TaskSpecificatons.byProject(project)));
	}
	
	public List<Task> getAll(User user) {
		return taskRepository.findAll(TaskSpecificatons.forUser(user));
	}
	
	public List<Task> getForPeriod(LocalDateTime from, LocalDateTime to, User user) {
		return taskRepository.findAll(
				TaskSpecificatons.forUser(user).and(
						TaskSpecificatons.expireFrom(from).and(
								TaskSpecificatons.expireTo(to))));
	}
	
	public Task createTask(TaskForm taskForm, User user) throws NotFoundInDBException {
		Project project = projectService.getById(taskForm.getProjectId());
		
		Task task = new Task();
		task = fillTaskWithTaskForm(task, taskForm, project);
		task = taskRepository.save(task);
		
		return task;
	}
	
	public Task updateTask(TaskForm taskForm, long taskId, User user) throws NotFoundInDBException {
		Task task = getById(taskId, user);
		task = fillTaskWithTaskForm(task, taskForm, task.getProject());
		task = taskRepository.save(task);
		
		return task;
	}
	
	public void deleteTask(long taskId, User user) throws NotFoundInDBException {
		Task task = getById(taskId, user);
		taskRepository.delete(task);
	}
	
	private Task fillTaskWithTaskForm(Task task, TaskForm taskForm, Project project) {
		task.setTitle(taskForm.getTitle());
		task.setDescription(taskForm.getDescription());
		task.setExpireAt(taskForm.getExpireAt());
		task.setPriority(taskForm.getPriority());
		task.setDone(taskForm.getDone());
		task.setProject(project);
		
		return task;
	}
}
