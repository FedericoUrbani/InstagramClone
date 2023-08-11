package com.noi.authservice.service;

import com.noi.authservice.entity.User;

public interface AuthService {
	
	boolean saveUser( User user);
	String generateToken( User user );
	boolean validateToken( String token, String username );
	

}
