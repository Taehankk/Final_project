package com.project.exercise.dao;

import java.util.List;

import com.project.exercise.dto.Problem;
import com.project.exercise.dto.SearchCondition;
import com.project.exercise.dto.UserData;

public interface ProblemDao {

	// 각 테이블 별로 문제 추출
	public List<Problem> selectCountry();

	public List<Problem> selectEtc();

	public List<Problem> selectExercise();

	public List<Problem> selectNumber();

	public List<Problem> selectPerson();
	
	public List<Problem> selectCategory(String category);

	public void save(UserData userData);
	
	public List<UserData> selectSearchList(SearchCondition searchCondition);
}