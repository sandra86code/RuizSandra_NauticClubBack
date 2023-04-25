package es.atos.club.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.atos.club.model.User;
import es.atos.club.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findById(username).orElse(null);
		if(user==null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return user;
	}
	
	


	
	
}
