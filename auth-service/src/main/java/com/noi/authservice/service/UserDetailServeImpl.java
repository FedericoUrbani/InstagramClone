package com.noi.authservice.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.noi.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServeImpl implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable( userRepository.findByUsername( username ))
				.orElseThrow( () -> new UsernameNotFoundException("EMAIL NOT FOUND"));
				
	}
	

}
