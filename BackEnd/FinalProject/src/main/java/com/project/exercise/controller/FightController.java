package com.project.exercise.controller;

import java.util.List;

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

import com.project.exercise.dto.Fight;
import com.project.exercise.service.FightService;

@RestController
@RequestMapping("/ansmoon")
public class FightController {
	private FightService fightService;
	
	@Autowired
	public FightController(FightService fightService) {
		this.fightService = fightService;
	}
	
	@GetMapping("/fight/list")
	public ResponseEntity<List<Fight>> getFightList(@RequestParam("id") String arenaId){
		List<Fight> list = fightService.getFightList(Integer.parseInt(arenaId));
		
		return new ResponseEntity<List<Fight>>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/fight/list/{fightId}")
	public ResponseEntity<Fight> getFightOne(@PathVariable("fightId") String fightId){
		Fight fight = fightService.getFightOne(Integer.parseInt(fightId));
		return new ResponseEntity<Fight>(fight, HttpStatus.OK);
	}
	
	@PutMapping("/fight/update")
	public ResponseEntity<Boolean> modifyFight(@RequestBody Fight fight){
		System.out.println(fight);
		fightService.modifyFight(fight);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/fight/create")
	public ResponseEntity<Boolean> createFight(@RequestBody Fight fight){
		System.out.println(fight);
		fightService.createFight(fight);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/fight/delete")
	public ResponseEntity<Boolean> deleteFight(@RequestParam("id") String fightId){
		fightService.deleteFight(Integer.parseInt(fightId));
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
}
