package com.project.exercise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exercise.dao.ProblemDao;
import com.project.exercise.dto.Problem;
import com.project.exercise.dto.SearchCondition;
import com.project.exercise.dto.UserData;

@Service
public class ProblemServiceImpl implements ProblemService {

	private final ProblemDao problemDao;

	@Autowired
	public ProblemServiceImpl(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	@Override
	public List<Problem> getListAll(String category) {
		List<Problem> allProblems = new ArrayList<>();
		allProblems.addAll(problemDao.selectCategory(category));
		return allProblems;
	}

	@Override
	public void saveUserData(UserData userData) {
		problemDao.save(userData);
	}

	@Override
	public List<UserData> searchList(SearchCondition searchCondition) {
		return problemDao.selectSearchList(searchCondition);
	}

}