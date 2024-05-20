package com.project.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exercise.dto.User;
import com.project.exercise.service.AdminService;

@RestController
@RequestMapping("/ansmoon")
public class AdminController {
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/admin/list")
	public ResponseEntity<List<User>> getUsers(){
		List<User> list = adminService.getUserList();
		System.out.println(list);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
		adminService.removeUser(userId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}