package com.todo.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private long id;
	
	@Column(name="username")
	private String username;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "authority")
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private List<UserAuthority> userAuthorities;

	public User() {
		
	}
	
	public User(long id, String username, String password, String email, List<UserAuthority> userAuthorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userAuthorities = userAuthorities;
	}
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@JsonIgnore
	public List<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}



	public void setUserAuthorities(List<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userAuthorities
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.toString()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}
