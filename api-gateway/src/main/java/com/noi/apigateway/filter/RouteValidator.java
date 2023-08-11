package com.noi.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	public static final List<String> openEndpoints = List.of("/api/auth/authenticate", "/api/auth/validate", "/eureka");
	
	public Predicate<ServerHttpRequest> isSecured = request -> openEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
