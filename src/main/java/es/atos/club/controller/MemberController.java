package es.atos.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.atos.club.dto.MemberDto;
import es.atos.club.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping(value = "/members")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	
	@Operation(summary="Devuelve todos los socios")
	@GetMapping
	public List<MemberDto> findAllMembers() {
		return service.findAllMembers();
	}
	
	@Operation(summary="Devuelve un socio")
	@GetMapping("/{id}")
	public ResponseEntity<?> findMemberById(@PathVariable int id) {
		return ResponseEntity.ok(service.findMemberById(id));
	}
	
	@Operation(summary="Añade un socio")
	@PostMapping
	public ResponseEntity<?> addMember(@RequestBody @Validated MemberDto memberDto) {
		try {
			MemberDto result = service.addMember(memberDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Operation(summary="Modifica un socio")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMember(@RequestBody @Validated MemberDto updatedMember,
			@PathVariable int id) {
		try {
			service.updateMember(id, updatedMember);
			return ResponseEntity.ok(service.findMemberById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Operation(summary="Elimina un socio")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBoat(@PathVariable int id) {
		service.deleteMember(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Socio con id " + id + " eliminado");
	}
	
	
}
