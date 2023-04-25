package es.atos.club.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import es.atos.club.model.Departure;


public interface DepartureRepository extends JpaRepository<Departure, Long> {

}
