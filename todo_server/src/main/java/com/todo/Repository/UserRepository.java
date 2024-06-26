package com.todo.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findUserByUsername(String username);
}
