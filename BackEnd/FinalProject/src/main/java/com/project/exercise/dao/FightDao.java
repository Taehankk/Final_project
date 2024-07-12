package com.project.exercise.dao;

import java.util.List;

import com.project.exercise.dto.Fight;

public interface FightDao {
	public List<Fight> selectFightList(int arenaId);
	
	public Fight selectFightOne(int fightId);
	
	public Boolean updateFight(Fight fight);
	
	public Boolean insertFight(Fight fight);
	
	public Boolean deleteFight(int fightId);
}
