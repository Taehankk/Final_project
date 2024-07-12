package com.project.exercise.service;

import java.util.Map;

import com.project.exercise.dto.User;

public interface UserService {
	public String checkUser(User user);
	
	public void registUser(User user);
	
	public Boolean checkUser(Map<String, String> check);

	public User getUser(String nickName);
}
