package com.example.app.code.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.app.code.Entety.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String Email); 
	UserEntity findByUserId(String userId);

}
