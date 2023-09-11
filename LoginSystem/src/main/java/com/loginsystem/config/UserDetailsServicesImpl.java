package com.loginsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.loginsystem.entities.User;
import com.loginsystem.repository.UserRepo;

public class UserDetailsServicesImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repo.findUserByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		UserCustomDetails userDetails = new UserCustomDetails(user);
		return userDetails;
	}

}
