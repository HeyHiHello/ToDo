CREATE DATABASE todo_db_dev;
USE todo_db_dev;
CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(10) NOT NULL UNIQUE,
	password CHAR(60) NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_authorities (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	authority VARCHAR(15) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE projects (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	title VARCHAR(15) NOT NULL,
	description VARCHAR(30),
	expire_at DATETIME,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE tasks (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	project_id INT NOT NULL,
	title VARCHAR(15) NOT NULL,
	description VARCHAR(30),
	expire_at DATETIME,
	priority INT NOT NULL,
	is_done BOOLEAN NOT NULL,
	FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

INSERT INTO users VALUES (NULL, 'test', '$2a$10$UE/4342IZDkMZ7zqX3U40.vK/ICEmG/8FAATIuh/u3PaK9fWzVcmC', 'test@test.com');

INSERT INTO projects VALUES (NULL, 1, 'Project1', 'My Project 1', null);
INSERT INTO projects VALUES (NULL, 1, 'Проект2', 'Мой проект 2', '2025-01-01T20:34');

INSERT INTO tasks VALUES (NULL, 1, 'Task1', 'My Task 1', '2024-03-25T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task1', 'My Task 1', '2024-03-30T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task2', 'My Task 2', '2024-04-15T20:34', 1, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task3', 'My Task 3', '2024-04-20T20:34', 2, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task4', 'My Task 4', '2024-04-25T20:34', 3, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task5', 'My Task 5', '2024-05-05T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task6', 'My Task 6', '2024-05-15T20:34', 1, true);
INSERT INTO tasks VALUES (NULL, 1, 'Task7', 'My Task 7', '2024-05-20T20:34', 2, false);
INSERT INTO tasks VALUES (NULL, 1, 'Task8', 'My Task 8', '2024-05-25T20:34', 3, true);

INSERT INTO tasks VALUES (NULL, 2, 'Task1', 'My Task 1', '2024-03-25T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task1', 'My Task 1', '2024-03-30T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task9', 'My Task 9', '2024-04-06T20:34', 4, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task10', 'My Task 10', '2024-04-16T20:34', 5, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task11', 'My Task 11', '2024-04-21T20:34', 0, true);
INSERT INTO tasks VALUES (NULL, 2, 'Task12', 'My Task 12', '2024-04-26T20:34', 1, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task13', 'My Task 13', '2024-05-06T20:34', 4, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task14', 'My Task 14', '2024-05-16T20:34', 5, true);
INSERT INTO tasks VALUES (NULL, 2, 'Task15', 'My Task 15', '2024-05-21T20:34', 0, false);
INSERT INTO tasks VALUES (NULL, 2, 'Task16', 'My Task 16', '2024-05-26T20:34', 1, false);