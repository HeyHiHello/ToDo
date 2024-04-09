# ToDO
## Description

Server client application that alow to track and plan projects.

## Stack

- Server side is written with Java Spring Web.
- ORM - Hibernate
- Database - MySql
- Client is written in Vue.js

## Deployment

The application is prepared to be run in docker.
There is Dockerfile in root of application that is to use to build an image from the source code.
To build it user should open terminal in root folder and use 
```sh
docker build -t todo .
```

To run this program user should create Schema in existing MySql instance.
Sql code example that creates the database is prepared in create.sql file that is in the root of the project.
Optionaly a user can create dedicated mysql user.
Start the programm using the following command
```sh
docker run --network test_net -d \
  -p 80:80 \
  -e VUE_APP_HOST=172.20.10.2 \ # Address of this application host
  -e DB_HOST=172.28.1.4 \ # Change to your MySql address
  -e DB_PORT=3306 \ # MySql port
  -e DB_NAME=todo_db_dev \ # MySql DB name
  -e DB_USER=todo_user \ # MySql user created for the app
  -e DB_PASSWORD=todo \ # MySql user's password
  todo
```

