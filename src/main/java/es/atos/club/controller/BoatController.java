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

import es.atos.club.dto.BoatDto;
import es.atos.club.service.BoatService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(value = "/boats")
public class BoatController {
	
	@Autowired
	BoatService service;
	
	@Operation(summary="Devuelve todos los barcos")
	@GetMapping
	public List<BoatDto> findAllBoats() {
		return service.findAllBoats();
	}
	
	@Operation(summary="Devuelve un barco por su id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findBoatById(@PathVariable int id) {
		return ResponseEntity.ok(service.findBoatById(id));
	}
	
	@Operation(summary="Añade un barco")
	@PostMapping
	public ResponseEntity<?> addBoat(@RequestBody @Validated BoatDto boatDto) {
		try {
			BoatDto result = service.addBoat(boatDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Operation(summary="Modifica un barco")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBoat(@RequestBody @Validated BoatDto updatedBoat,
			@PathVariable int id) {
		try {
			service.updateBoat(id, updatedBoat);
			return ResponseEntity.ok(service.findBoatById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Operation(summary="Elimina un barco")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBoat(@PathVariable int id) {
		service.deleteBoat(id);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Barco con id " + id + " eliminado");

	}
	
	
}
