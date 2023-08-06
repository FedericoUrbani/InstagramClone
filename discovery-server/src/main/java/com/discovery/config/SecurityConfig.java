package com.discovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	// imposto Username, password e ruolo per poter collegare i vari 
	// progetti
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
			
		UserDetails user = User.withDefaultPasswordEncoder()
				               .username("testing")
				               .password("1234").roles("USER")
				               .build();
							

		
		return new InMemoryUserDetailsManager(user);
		
	}
	
	// Filtro le varie richieste in modo che
	// Solo alcune possono essere visualizzate, in questo caso entrano tutte
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		return http.build();
	}

}
