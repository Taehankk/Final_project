package com.project.exercise.dao;

import java.util.List;
import java.util.Map;

import com.project.exercise.dto.Arena;

public interface ArenaDao {
	public List<Arena> selectAllArena();
	
	public Arena selectNowArena(int arenaId);
	
	public Boolean insertArena(Arena arena);
	
	public Boolean deleteArena(int arenaId);
	
	public Boolean updateArena(Arena arena);
	
	public Boolean updateInterest(Map<String, Integer> map);
}
