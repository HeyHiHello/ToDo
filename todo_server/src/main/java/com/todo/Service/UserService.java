package com.todo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.Entity.User;
import com.todo.Entity.UserAuthority;
import com.todo.Repository.UserRepository;
import com.todo.RequestBodyItems.PasswordForm;
import com.todo.RequestBodyItems.UserForm;
import com.todo.Service.Exception.NotFoundInDBException;
import com.todo.Service.Exception.WrongDataException;

@Service
public class UserService implements UserDetailsService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User getUserByUsername(String username) throws NotFoundInDBException {
		Optional<User> userOpt = userRepository.findUserByUsername(username);
		if (userOpt.isEmpty()) {
			throw new NotFoundInDBException("User " + username + " not found in DB");
		}
		return userOpt.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = this.getUserByUsername(username);
		} catch (NotFoundInDBException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		return user;
	}
	
	public User createUser(UserForm userForm) {
		User user = fillUserWithForm(userForm);
		user = userRepository.save(user);
		return user;
	}
	
	public User changeEmail(User user, String newEmail) {
		user.setEmail(newEmail);
		return userRepository.save(user);
	}
	
	public User changePassword(User user, PasswordForm passwordForm) throws WrongDataException {
		boolean passwordsMathes = passwordEncoder.matches(passwordForm.getOldPassword(), user.getPassword());
		if (passwordsMathes) {
			user.setPassword(passwordEncoder.encode(passwordForm.newPassword));
			user = userRepository.save(user);
			return user;
		}
		throw new WrongDataException("Password doesn't match");
	}

	private User fillUserWithForm(UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setEmail(userForm.getEmail());
		user.setUserAuthorities(List.of(UserAuthority.USER));
		return user;
	}
}
