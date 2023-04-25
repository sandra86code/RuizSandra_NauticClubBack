package es.atos.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.atos.club.model.Member;


public interface MemberRepository extends JpaRepository<Member, Integer>{

}
