package com.project.exercise.service;

import java.util.List;

import com.project.exercise.dto.User;

public interface AdminService {
	public List<User> getUserList();
	
	public int removeUser(String userId);
	
}
