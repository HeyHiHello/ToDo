package com.todo.Conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.todo.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(conf -> {
				conf.disable();
			})
			.authorizeHttpRequests(auth -> {
				auth
				.requestMatchers("/api/user/signin", "/api/user/signup").permitAll()
				.requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated();
			})
			.httpBasic(Customizer.withDefaults());
		return http.build();
    }

}
