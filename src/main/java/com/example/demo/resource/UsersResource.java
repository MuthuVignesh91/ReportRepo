package com.example.demo.resource;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.Users;
import com.example.demo.document.trend.Trend;
import com.example.demo.document.trend.TrendId;
import com.example.demo.repository.TrendRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class UsersResource {

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/users/all")
	public List<Users> getAllUsers()
	{
		return this.userRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/add")
	public void putUsers(@RequestBody Users user)
	{
		
		this.userRepo.save(user);
		HashMap<String, HashMap<String, Object>> values = new HashMap<>();
	}
}
