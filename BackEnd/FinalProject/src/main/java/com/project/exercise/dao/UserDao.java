package com.project.exercise.dao;

import java.util.Map;

import com.project.exercise.dto.User;

public interface UserDao {
	public String selectUser(User user);
	
	public void registUser(User user);
	
	public boolean checkUser(Map<String, String> check);
	
	public User selectUserByNick(String nickName);
}
