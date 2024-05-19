package com.project.exercise.service;

import java.util.List;

import com.project.exercise.dto.OrderCondition;
import com.project.exercise.dto.Problem;
import com.project.exercise.dto.UserData;

public interface ProblemService {

	public List<Problem> getListAll();
	
	public void saveUserData(UserData userData);
	
	public List<UserData> scoreListUp(OrderCondition orderCondition);
}