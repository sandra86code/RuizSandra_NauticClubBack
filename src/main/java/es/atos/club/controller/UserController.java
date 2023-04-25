package es.atos.club.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.atos.club.model.User;
import es.atos.club.security.JwtUtils;
import es.atos.club.security.LoginCredential;
import es.atos.club.service.UserService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {
	

	@Autowired
	UserService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Operation(summary="Login de la aplicación")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginCredential loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
		loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User)authentication.getPrincipal();
		ResponseEntity<Object> jwt = JwtUtils.generateToken(loginRequest.getUsername(), user.getEmail(),
		user.getRole());
		User userDetails = (User) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item ->
			item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(jwt);
	}

}
