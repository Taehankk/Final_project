package com.project.exercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exercise.dao.FightDao;
import com.project.exercise.dto.Fight;

@Service
public class FightServiceImpl implements FightService {

	private FightDao fightDao;
	
	public FightServiceImpl(FightDao fightDao) {
		this.fightDao = fightDao;
	}
	
	@Override
	public List<Fight> getFightList(int arenaId) {
		return fightDao.selectFightList(arenaId);
	}
	
	@Override
	public Fight getFightOne(int fightId) {
		return fightDao.selectFightOne(fightId);
	}

	@Override
	public Boolean modifyFight(Fight fight) {
		return fightDao.updateFight(fight);
	}

	@Override
	public Boolean createFight(Fight fight) {
		return fightDao.insertFight(fight);
	}

	@Override
	public Boolean deleteFight(int fightId) {
		return fightDao.deleteFight(fightId);
	}


}
