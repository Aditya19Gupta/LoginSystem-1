package com.loginsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.loginsystem.entities.User;

public interface UserRepo extends MongoRepository<User, String> {
	
	public User findUserByEmail(String email);

}
