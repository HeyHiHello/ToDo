package com.todo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.Entity.User;
import com.todo.RequestBodyItems.EmailForm;
import com.todo.RequestBodyItems.PasswordForm;
import com.todo.RequestBodyItems.UserForm;
import com.todo.Service.UserService;
import com.todo.Service.Exception.NotFoundInDBException;
import com.todo.Service.Exception.WrongDataException;


@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	
	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/signin")
	public ResponseEntity<User> signIn(@RequestBody UserForm userForm) {
		User user;
		try {
			user = userService.getUserByUsername(userForm.getUsername());
		} catch (NotFoundInDBException e) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		
		boolean passwordCheck = passwordEncoder.matches(userForm.getPassword(), user.getPassword());
		if (!passwordCheck) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/signup") 
	public ResponseEntity<User> signUp(@RequestBody UserForm userForm) {
		User user = userService.createUser(userForm);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping("/changeemail")
	public ResponseEntity<User> changeEmail(@RequestBody EmailForm emailForm, @AuthenticationPrincipal User user) {
		user.setEmail(emailForm.getNewEmail());
		User changedUser = userService.changeEmail(user, emailForm.getNewEmail());
		return new ResponseEntity<User>(changedUser, HttpStatus.OK);
		
	}
	
	@PatchMapping("/changepassword")
	public ResponseEntity<User> changePassword(@RequestBody PasswordForm passwordForm, @AuthenticationPrincipal User user) throws WrongDataException {
		User changedUser = userService.changePassword(user, passwordForm);
		return new ResponseEntity<User>(changedUser, HttpStatus.OK);
		
	}
}

