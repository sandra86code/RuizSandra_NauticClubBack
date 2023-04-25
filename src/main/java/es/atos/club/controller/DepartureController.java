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


import es.atos.club.dto.DepartureDto;
import es.atos.club.service.DepartureService;
import io.swagger.v3.oas.annotations.Operation;


@RequestMapping(value = "/departures")
@RestController
public class DepartureController {
	
	@Autowired
	DepartureService service;
	
	
	@Operation(summary="Devuelve todas las salidas")
	@GetMapping
	public List<DepartureDto> findAllDepartures() {
		return service.findAllDepartures();
	}
	
	@Operation(summary="Devuelve una salida")
	@GetMapping("/{id}")
	public ResponseEntity<?> findDepartureById(@PathVariable long id) {
		return ResponseEntity.ok(service.findDepartureById(id));
	}
	
	@Operation(summary="Añade una salida")
	@PostMapping
	public ResponseEntity<?> addDeparture(@RequestBody 
			@Validated DepartureDto departureDto) {
		try {
			DepartureDto result = service.addDeparture(departureDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Operation(summary="Modifica una salida")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDeparture(@RequestBody 
			@Validated DepartureDto updatedDeparture,
			@PathVariable long id) {
		try {
			service.updateDeparture(id, updatedDeparture);
			return ResponseEntity.ok(service.findDepartureById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Operation(summary="Elimina una salida")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDeparture(@PathVariable long id) {
		service.deleteDeparture(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Salida con id " + id + " eliminado");
	}
}
