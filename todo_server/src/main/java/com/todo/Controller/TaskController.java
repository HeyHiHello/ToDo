package com.todo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.Entity.Task;
import com.todo.Entity.User;
import com.todo.RequestBodyItems.TaskForm;
import com.todo.Service.UserTaskService;
import com.todo.Service.Exception.NotFoundInDBException;

@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {
	private UserTaskService taskService;
	
	public TaskController(UserTaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") long taskId, @AuthenticationPrincipal User user) {
		Task task = null;
		try {
			task = taskService.getById(taskId, user);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
		
	}
	
	@GetMapping
	public List<Task> getAll(@AuthenticationPrincipal User user) {
		return taskService.getAll(user);
	}
	
	@GetMapping("/period")
	public List<Task> getAllForPeriod(
			@AuthenticationPrincipal User user, 
			@RequestParam LocalDateTime from, 
			@RequestParam LocalDateTime to) 
	{
		return taskService.getForPeriod(from, to, user);
	}
	
	@PostMapping
	public ResponseEntity<Task> postTask(@RequestBody TaskForm taskForm, @AuthenticationPrincipal User user) {
		Task newTask = null;
		try {
			newTask = taskService.createTask(taskForm, user);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(newTask, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> putTask(@PathVariable(name = "id") long taskId, @RequestBody TaskForm taskForm, @AuthenticationPrincipal User user) {
		Task updatedTask = null;
		try {
			updatedTask = taskService.updateTask(taskForm, taskId, user);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable(name = "id") long taskId, @AuthenticationPrincipal User user) {
		try {
			taskService.deleteTask(taskId, user);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(HttpStatus.OK);
	}

}
