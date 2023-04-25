package es.atos.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.atos.club.model.Boat;


public interface BoatRepository extends JpaRepository<Boat, Integer>{

}
