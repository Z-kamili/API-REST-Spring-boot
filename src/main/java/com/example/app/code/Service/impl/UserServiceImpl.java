package com.example.app.code.Service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.code.Entety.UserEntity;
import com.example.app.code.Repository.UserRepository;
import com.example.app.code.services.UserService;
import com.example.app.code.shared.dto.UserDto;

import antlr.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;// Inject de dependences

	@Autowired
	com.example.app.code.test.Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub

		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());

		if (checkUser != null)
			throw new RuntimeException("User Alrady Exists !");

		UserEntity userEntity = new UserEntity();

		BeanUtils.copyProperties(userDto, userEntity);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		userEntity.setUserId(utils.generateStringId(32));

		UserEntity newUser = userRepository.save(userEntity);

		UserDto userDto_2 = new UserDto();

		BeanUtils.copyProperties(newUser, userDto_2);

		return userDto_2;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		UserDto userDto_2 = new UserDto();
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity userEntity_2 = userRepository.save(userEntity);
		BeanUtils.copyProperties(userEntity_2, userDto_2);
		return userDto_2;
		
		
	}
	
	@Override
	public void deleteUser(String id) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		
		userRepository.delete(userEntity);
		
		
	}
	 
}
