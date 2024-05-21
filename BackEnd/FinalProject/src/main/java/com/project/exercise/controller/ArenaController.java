package com.project.exercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exercise.dto.Arena;
import com.project.exercise.service.ArenaService;

@RestController
@RequestMapping("/ansmoon")
public class ArenaController {
	private ArenaService arenaService;
	
	@Autowired
	public ArenaController(ArenaService arenaService) {
		this.arenaService = arenaService;
	}
	
	@GetMapping("/arena/list")
	public ResponseEntity<List<Arena>> getArenaList(){
		List<Arena> list = arenaService.getArenaList();
		
		return new ResponseEntity<List<Arena>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/arena/detail/{id}")
	public ResponseEntity<Arena> getArenaNow(@PathVariable("id") int arenaId){
		Arena arena = arenaService.getArenaNow(arenaId);
		
		return new ResponseEntity<Arena>(arena, HttpStatus.OK);
	}
	
	@PostMapping("/arena/regist")
	public ResponseEntity<Boolean> registArena(@RequestBody Arena arena){
		arenaService.registArena(arena);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/arena/delete")
	public ResponseEntity<Boolean> removeArena(@RequestParam("id") String arenaId){
		System.out.println(arenaId);
		arenaService.removeArena(Integer.parseInt(arenaId));
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/arena/update")
	public ResponseEntity<Boolean> modifyArena(@RequestBody Arena arena){
		System.out.println(arena);
		arenaService.modifyArena(arena);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/arena/interest")
	public ResponseEntity<Boolean> modifyInterest(@RequestParam("id") String arenaId, @RequestParam("cnt") String cnt){
		Map<String, Integer> map = new HashMap<>();
		map.put("arenaId", Integer.parseInt(arenaId));
		map.put("cnt", Integer.parseInt(cnt));
		arenaService.modifyInterest(map);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	
}
