package es.atos.club.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class DepartureDto {
	
	private long id;
	@NotNull(message = "Fecha no válida: Fecha nula")
	private LocalDateTime departureDate;
	@NotNull(message = "Destino no válido: Destino nulo")
	@NotBlank(message = "Destino no válido: Destino vacío")
	private String destination;
	private int boatId;
	private String boatPlate;
	
	
	public DepartureDto() {
		super();
	}


	public DepartureDto(long id, LocalDateTime departureDate, String destination, int boatId, String boatPlate) {
		super();
		this.id = id;
		this.departureDate = departureDate;
		this.destination = destination;
		this.boatId = boatId;
		this.boatPlate = boatPlate;
	}


	public DepartureDto(LocalDateTime departureDate, String destination, int boatId, String boatPlate) {
		super();
		this.departureDate = departureDate;
		this.destination = destination;
		this.boatId = boatId;
		this.boatPlate = boatPlate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public LocalDateTime getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public int getBoatId() {
		return boatId;
	}


	public void setBoatId(int boatId) {
		this.boatId = boatId;
	}

	
	public String getBoatPlate() {
		return boatPlate;
	}


	public void setBoatPlate(String boatPlate) {
		this.boatPlate = boatPlate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(boatId, departureDate, destination);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartureDto other = (DepartureDto) obj;
		return boatId == other.boatId && Objects.equals(departureDate, other.departureDate)
				&& Objects.equals(destination, other.destination);
	}
	
	
}
