package com.project.exercise.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.exercise.dao.ArenaDao;
import com.project.exercise.dto.Arena;

@Service
public class ArenaServiceImpl implements ArenaService{
	private ArenaDao arenaDao;
	
	public ArenaServiceImpl(ArenaDao arenaDao) {
		this.arenaDao = arenaDao;
	}
	
	@Override
	public List<Arena> getArenaList() {
		return arenaDao.selectAllArena();
	}

	@Override
	public Arena getArenaNow(int arenaId) {
		return arenaDao.selectNowArena(arenaId);
	}

	@Override
	public Boolean registArena(Arena arena) {
		return arenaDao.insertArena(arena);
	}

	@Override
	public Boolean removeArena(int arenaId) {
		return arenaDao.deleteArena(arenaId);
	}

	@Override
	public Boolean modifyArena(Arena arena) {
		return arenaDao.updateArena(arena);
	}

	@Override
	public Boolean modifyInterest(Map<String, Integer> map) {
		return arenaDao.updateInterest(map);
	}

}
