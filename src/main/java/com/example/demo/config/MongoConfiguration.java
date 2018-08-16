package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.demo.document.Users;
import com.example.demo.repository.UserRepository;

//@EnableMongoRepositories(basePackageClasses = UserRepository.class)
//@Configuration
public class MongoConfiguration {

	/*CommandLineRunner commandLineRunner(UserRepository userRepository)
	{
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				userRepository.save(new Users("test", "muthu", "iot"));
				
			}
		};
	}*/
}
