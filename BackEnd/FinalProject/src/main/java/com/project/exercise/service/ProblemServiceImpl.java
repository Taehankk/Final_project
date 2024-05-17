package com.project.exercise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exercise.dao.ProblemDao;
import com.project.exercise.dto.Problem;
import com.project.exercise.dto.UserData;

@Service
public class ProblemServiceImpl implements ProblemService {

	private final ProblemDao problemDao;

	@Autowired
	public ProblemServiceImpl(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	@Override
	public List<Problem> getListAll() {
		List<Problem> allProblems = new ArrayList<>();
		
		allProblems.addAll(problemDao.selectCountry());

		allProblems.addAll(problemDao.selectEtc());

		allProblems.addAll(problemDao.selectExercise());

		allProblems.addAll(problemDao.selectNumber());

		allProblems.addAll(problemDao.selectPerson());

		return allProblems;
	}

    @Override
    public void saveUserData(UserData userData) {
        problemDao.save(userData);
    }

}