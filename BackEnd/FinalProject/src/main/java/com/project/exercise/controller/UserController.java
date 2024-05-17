package com.project.exercise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exercise.dto.User;
import com.project.exercise.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ansmoon")
public class UserController {

	private UserService userService;
	
	@Autowired
	UserController(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session){
		System.out.println(user);
		String nickName = userService.checkUser(user);
		if(nickName != null) {
			session.setAttribute("nickName", nickName);
			return new ResponseEntity<String>(nickName, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/user/regist")
	public ResponseEntity<User> signUp(@RequestBody User user){
		userService.registUser(user);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/check")
	public ResponseEntity<Boolean> checkParam(@RequestParam(value="name") String name, @RequestParam(value="value") String value){
		Map<String, String> check = new HashMap<>();
		check.put("name", name);
		check.put("value", value);
		if(userService.checkUser(check)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);			
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/session")
	public ResponseEntity<User> getUser(@RequestParam(value="nickName") String nickName){
		User user = userService.getUser(nickName);
		
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}
