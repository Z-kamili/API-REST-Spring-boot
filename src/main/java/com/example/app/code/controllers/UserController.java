package com.example.app.code.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
	@GetMapping(path="/{id}")
	public UserResponse getUser(@PathVariable String id){
		
	UserDto userDto = 	userService.getUserByUserId(id);
	
	UserResponse userResponse = new UserResponse();
	
	BeanUtils.copyProperties(userDto, userResponse);
		
	return userResponse;
	
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
	
	@PutMapping(path="/{id}")
	public UserResponse updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		//chef d'orchestre
	    UserDto updateUser = userService.updateUser(id,userDto);
	    UserResponse userResponse = new UserResponse();
	    BeanUtils.copyProperties(updateUser,userResponse);
	    return userResponse;
		
	}
	
	
	@DeleteMapping(path="/{id}")
	public String deleteUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		return "";
	}
	
	
	
	
	

}
