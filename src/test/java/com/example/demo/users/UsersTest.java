package com.example.demo.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.document.Users;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTest {

	@Autowired
	UserRepository userRepo;
	
	@Test
	public void addUser()
	{
		Users user = new Users("muthu", "testMuthu", "iot");
		this.userRepo.save(user);
	}
}
