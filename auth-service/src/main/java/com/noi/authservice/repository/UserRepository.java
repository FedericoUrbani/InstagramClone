package com.noi.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noi.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername( String username );

}
