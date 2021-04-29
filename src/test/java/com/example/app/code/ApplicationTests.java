package com.example.app.code;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.code.Entety.UserEntity;
import com.example.app.code.Respones.UserResponse;
import com.example.app.code.services.UserService;
import com.example.app.code.shared.dto.UserDto;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@SpringBootTest
class ApplicationTests {


	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	public void  CreateUser() {
		
		UserDto userdto_1 = new UserDto();
		
		
		userdto_1.setFirstName("Hamza");
		
		userdto_1.setLastName("Mohammed");
		
		userdto_1.setEmail("hamza@gmail.com");
		
		userdto_1.setPassword("123456");
		
		
		UserDto userdto = userService.createUser(userdto_1);
		
		UserResponse Uresponse = new UserResponse(); 
		
		BeanUtils.copyProperties(userdto,Uresponse);
		
	    System.out.println(userdto.toString());
		
		
	}
	
	

}
