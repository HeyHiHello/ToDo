package com.todo.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.todo.Entity.Project;
import com.todo.Entity.User;
import com.todo.RequestBodyItems.ProjectForm;
import com.todo.Service.ProjectService;
import com.todo.Service.Exception.NotFoundInDBException;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
	
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable long id) {
		Project project = null;
		try {
			project= projectService.getById(id);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping
	public List<Project> getAllForCurrentUser(@AuthenticationPrincipal User user) {
		List<Project> projects = projectService.getProjectByUser(user);
		return projects;
	}
	
	@PostMapping
	public Project postProject(@RequestBody ProjectForm projectForm, @AuthenticationPrincipal User user) {
		
		return projectService.createProject(projectForm, user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> putProject(@PathVariable long id, @RequestBody ProjectForm projectForm) {
		Project updatedProject = null;
		try {
			updatedProject = projectService.updateProject(id, projectForm);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Project>(updatedProject, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable(name = "id") long projectId, @AuthenticationPrincipal User user) {
		try {
			projectService.deleteProject(projectId, user);
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Project>(HttpStatus.OK);
	}
}
