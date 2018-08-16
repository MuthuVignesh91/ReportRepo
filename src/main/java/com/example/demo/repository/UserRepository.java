package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.document.Users;

public interface UserRepository extends MongoRepository<Users, String>{
	Users findByName(String name);
}
