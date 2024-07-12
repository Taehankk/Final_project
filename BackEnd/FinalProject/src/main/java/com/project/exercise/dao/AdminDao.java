package com.project.exercise.dao;

import java.util.List;

import com.project.exercise.dto.User;

public interface AdminDao {
	public List<User> selectUsers();
	
	public int deleteUser(String userId);
}
