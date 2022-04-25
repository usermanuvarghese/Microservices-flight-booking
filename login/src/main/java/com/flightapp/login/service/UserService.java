package com.flightapp.login.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightapp.login.config.PasswordEncoderConfig;
import com.flightapp.login.entity.UserEntity;
import com.flightapp.login.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoderConfig passwordEncoderConfig;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByusername(username);
		if ("ADMIN".equals(user.getRole())) {

			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
			return new User(user.getUsername(), user.getPassword(), authorities);

		} else if ("USER".equals(user.getRole())) {

			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
			return new User(user.getUsername(), user.getPassword(), authorities);
			// return new User("user",
			// "$2a$10$Dwzu0xQK7eHkBMA67KjPpelsTuvajmYArw5ruHxWvSgboxmAjp9mu", authorities);
		}

		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}


	public void registerUser(UserEntity user) {
		user.setRole("USER");
		user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
		userRepo.save(user);
	}
	
	

}
	

