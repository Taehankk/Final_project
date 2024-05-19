package com.project.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exercise.dao.AdminDao;
import com.project.exercise.dto.User;

@Service
public class AdminServiceImpl implements AdminService{
	private AdminDao adminDao;
	
	@Autowired
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<User> getUserList() {
		return adminDao.selectUsers();
	}
	
	@Override
	public int removeUser(String userId) {
		return adminDao.deleteUser(userId);
	}


}
