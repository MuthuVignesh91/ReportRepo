package com.example.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	
	@Id
	private String id;
	
	private String name;
	private String teamName;
	
	public Users()
	{
		
	}
	
	public Users(String id, String name, String teamName) {
		super();
		this.id=id;
		this.name = name;
		this.teamName = teamName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	 @Override
	    public String toString() {
	        return "User{" +
	                ", name='" + name + '\'' +
	                ", teamName=" + teamName +
	                '}';
	    }
	
}
