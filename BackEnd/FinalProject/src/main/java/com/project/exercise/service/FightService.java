package com.project.exercise.service;

import java.util.List;

import com.project.exercise.dto.Fight;

public interface FightService {
	public List<Fight> getFightList(int arenaId);
	
	public Fight getFightOne(int fightId);
	
	public Boolean modifyFight(Fight fight);
	
	public Boolean createFight(Fight fight);
	
	public Boolean deleteFight(int fightId);
}
