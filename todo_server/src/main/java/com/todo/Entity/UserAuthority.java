package com.todo.Entity;

import jakarta.persistence.Table;

@Table(name = "user_authorities")
public enum UserAuthority {
	ADMIN, USER;
}
