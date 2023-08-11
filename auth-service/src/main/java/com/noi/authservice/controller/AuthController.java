package com.noi.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.noi.autclient.dto.AuthRequest;
import com.noi.autclient.dto.AuthResponse;
import com.noi.authservice.entity.User;
import com.noi.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	@ResponseStatus( HttpStatus.OK)
	public AuthResponse authenticate(@RequestBody AuthRequest authRequest ) throws Exception {
		authenticate( authRequest.getUsername(), authRequest.getPassword() );
		
		User userLogged= (User) userDetailsService.loadUserByUsername( authRequest.getUsername() );
		
		return new AuthResponse( authService.generateToken(userLogged), true); 		
	}
	
	@PostMapping("/validate")
	@ResponseStatus( HttpStatus.OK)
	public AuthResponse validate( @RequestBody AuthRequest authRequest ) throws Exception {
		
		try {
			if( authService.validateToken( authRequest.getToken(), authRequest.getUsername() ) )
				return new AuthResponse( authRequest.getToken(), true );
		} catch (Exception e) {			
		}
		
		return new AuthResponse( authRequest.getToken(), false );
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("BAD_CREDENTIAL", e);
	  }
   }	
}
