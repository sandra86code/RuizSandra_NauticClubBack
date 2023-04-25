package es.atos.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.atos.club.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	public List<User> findByEmail(String email);
}
