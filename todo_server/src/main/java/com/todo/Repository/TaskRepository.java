package com.todo.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.Entity.Project;
import com.todo.Entity.Task;
import com.todo.Entity.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>, JpaSpecificationExecutor<Task> {
	
	static class TaskSpecificatons {
		public static Specification<Task> byId(long id) {
			return (root, query, builder) -> {
				return builder.equal(root.get("id").as(Long.class), id);
			};
		}
		
		public static Specification<Task> forUser(User user) {
			return (root, query, builder) -> {
				return builder.equal(root.get("project").get("user"), user);
			};
		}
		
		public static Specification<Task> byProject(Project project) {
			return (root, query, builder) -> {
				return builder.equal(root.get("project"), project);
			};
		}
		
		public static Specification<Task> expireFrom(LocalDateTime from) {
			return (root, query, builder) -> {
				return builder.greaterThan(root.get("expireAt").as(LocalDateTime.class), from);
			};
		}
		
		public static Specification<Task> expireTo(LocalDateTime to) {
			return (root, query, builder) -> {
				return builder.lessThan(root.get("expireAt"), to);
			};
		}
	}
}
