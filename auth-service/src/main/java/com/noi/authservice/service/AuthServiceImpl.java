package com.noi.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.noi.authservice.entity.User;
import com.noi.authservice.repository.UserRepository;
import com.noi.authservice.util.JWTUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JWTUtils jwtUtils;
	

	@Override
	public boolean saveUser(User user) {
		
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		
		userRepository.save(user);
		log.info("user {} added", user.getUsername());				
		return true;
	}

	@Override
	public String generateToken(User user) {
		return jwtUtils.generateToken(user);
	}

	@Override
	public boolean validateToken(String token, String username) {
		// TODO Auto-generated method stub
		return jwtUtils.validateToken(token, username);
	}

}
