package com.project.exercise.service;

import java.util.List;
import java.util.Map;

import com.project.exercise.dto.Arena;

public interface ArenaService {
	public List<Arena> getArenaList();
	
	public Arena getArenaNow(int arenaId);
	
	public Boolean registArena(Arena arena);
	
	public Boolean removeArena(int arenaId);
	
	public Boolean modifyArena(Arena arena);
	
	public Boolean modifyInterest(Map<String, Integer> map);
}
