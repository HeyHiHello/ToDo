package com.todo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todo.Entity.Project;
import com.todo.Entity.User;
import com.todo.Repository.ProjectRepository;
import com.todo.RequestBodyItems.ProjectForm;
import com.todo.Service.Exception.NotFoundInDBException;

@Service
public class ProjectService {
	private ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public Project getById(long projectId) throws NotFoundInDBException {
		Optional<Project> projectOpt = projectRepository.findById(projectId);
		if (projectOpt.isEmpty()) {
			throw new NotFoundInDBException("Project " + projectId + " not found in database");
		}
		return projectOpt.get();
	}
	
	public List<Project> getProjectByUser(User user) {
		return projectRepository.findAllByUser(user);
	}
	
	public Project createProject(ProjectForm projectForm, User user) {
		Project project = new Project();
		
		project.setTitle(projectForm.getTitle());
		project.setDescription(projectForm.getDescription());
		project.setExpireAt(projectForm.getExpireAt());
		project.setUser(user);
		
		return projectRepository.save(project);
	}
	
	public Project updateProject(long projectId, ProjectForm projectForm) throws NotFoundInDBException {
		Optional<Project> projectOpt = projectRepository.findById(projectId);
		if (projectOpt.isEmpty()) {
			throw new NotFoundInDBException("Project " + projectId + " not found in database");
		}
		
		Project project = projectOpt.get();
		project.setTitle(projectForm.getTitle());
		project.setDescription(projectForm.getDescription());
		project.setExpireAt(projectForm.getExpireAt());
		
		return projectRepository.save(project);
	}
	
	public void deleteProject(long projectId, User user) throws NotFoundInDBException {
		Project project = getById(projectId);
		if (project.getUser().getId() != user.getId()) {
			throw new NotFoundInDBException("Not found in DB");
		}
		projectRepository.delete(project);
	}
}
