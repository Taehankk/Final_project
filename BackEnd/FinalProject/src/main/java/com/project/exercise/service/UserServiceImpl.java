package com.project.exercise.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.exercise.dao.UserDao;
import com.project.exercise.dto.User;

@Service
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	UserServiceImpl(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Override
	public String checkUser(User user) {
		return userDao.selectUser(user);
	}

	@Override
	public void registUser(User user) {
		userDao.registUser(user);
	}

	@Override
	public Boolean checkUser(Map<String, String> check) {
		return userDao.checkUser(check);
	}

	@Override
	public User getUser(String nickName) {
		
		return userDao.selectUserByNick(nickName);
	}

	
}
