package com.noi.apigateway.filter;

import java.io.ObjectInputFilter.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;
import com.noi.autclient.dto.AuthRequest;
import com.noi.autclient.dto.AuthResponse;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public static class Config{
		
	}
	
	public AuthenticationFilter() {
		super( Config.class );
	}

	
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if( routeValidator.isSecured.test( exchange.getRequest() ) ) {
				if( !exchange.getRequest().getHeaders().containsKey( HttpHeaders.AUTHORIZATION ) )
					throw new RuntimeException("HEADER NOT FOUND");
				
				String token = exchange.getRequest().getHeaders().get( HttpHeaders.AUTHORIZATION ).get(0);
				
				AuthRequest authRequest = new AuthRequest("peppino@pepp.pep", "", token);
				
				return webClientBuilder.build()
					.post()
					.uri("http://auth-service/api/auth/validate")
					.body( Mono.just(authRequest), AuthRequest.class )
					.accept( MediaType.APPLICATION_JSON )
					.retrieve()
					.bodyToMono(AuthResponse.class)
					.filter( r -> r.isTokenValid() )
					.switchIfEmpty( Mono.error( new RuntimeException("TOKEN NOT VALID") ) )
					.map( r -> exchange )
					.flatMap( chain::filter );		
			}
			
			return chain.filter(exchange);
		} ;
		
	}

}
