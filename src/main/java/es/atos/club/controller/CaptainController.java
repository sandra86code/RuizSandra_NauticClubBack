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

import es.atos.club.dto.CaptainDto;
import es.atos.club.service.CaptainService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping(value = "/captains")
public class CaptainController {
	
	@Autowired
	CaptainService service;
	
	@Operation(summary="Devuelve todos los patrones")
	@GetMapping
	public List<CaptainDto> findAllCaptains() {
		return service.findAllCaptains();
	}
	
	@Operation(summary="Devuelve un patrón")
	@GetMapping("/{id}")
	public ResponseEntity<?> findCaptainById(@PathVariable int id) {
		return ResponseEntity.ok(service.findCaptainById(id));
	}
	
	@Operation(summary="Añade un patrón")
	@PostMapping
	public ResponseEntity<?> addCaptain(@RequestBody @Validated CaptainDto captainDto) {
		try {
			CaptainDto result = service.addCaptain(captainDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Operation(summary="Modifica un patrón")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCaptain(@RequestBody @Validated CaptainDto updatedCaptain,
			@PathVariable int id) {
		try {
			service.updateCaptain(id, updatedCaptain);
			return ResponseEntity.ok(service.findCaptainById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Operation(summary="Elimina un patrón")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBoat(@PathVariable int id) {
		service.deleteCaptain(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Patrón con id " + id + " eliminado");
	}
	
	
}
