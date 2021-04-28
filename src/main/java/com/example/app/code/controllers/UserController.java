package com.example.app.code.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.code.Request.UserRequest;
import com.example.app.code.Respones.UserResponse;
import com.example.app.code.services.UserService;
import com.example.app.code.shared.dto.UserDto;

@RestController
@RequestMapping("/users") //localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping
	public String getUser() {
		
		return "Hello world";
		
	}
	
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		//chef d'orchestre
	    UserDto createUser = userService.createUser(userDto);
	    UserResponse userResponse = new UserResponse();
	    BeanUtils.copyProperties(createUser,userResponse);
	    return userResponse;
		
	}
	
	@PutMapping
	public String updateUser() {
		
		return "update user was called";
		
	}
	
	
	@DeleteMapping
	public String deleteUser() {
		
		return "delete user was called";
		
	}
	
	
	
	
	

}
