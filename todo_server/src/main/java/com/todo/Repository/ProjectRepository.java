package com.todo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.Entity.Project;
import com.todo.Entity.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	List<Project> findAllByUser(User user);
}
