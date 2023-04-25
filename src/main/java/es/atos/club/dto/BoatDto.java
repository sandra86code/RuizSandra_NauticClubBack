package es.atos.club.dto;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BoatDto {
	
	private int id;
	@NotNull(message = "Matrícula no válida: Matrícula nula")
	@NotBlank(message = "Matrícula no válida: Matrícula vacía")
	private String plate;
	@NotNull(message = "Nombre no válido: Nombre nulo")
	@NotBlank(message = "Nombre no válido: Nombre vacío")
	private String name;
	@NotNull(message = "Amarre no válido: Amarre nulo")
	@NotBlank(message = "Amarre no válido: Nombre vacío")
	private String slipNumber;
	@Min(value = 0, message = "Tarifa no válida: debe ser mayor de 0")
    @Max(value = 10000, message = "Tarifa no válida: debe ser menor de 10000")
	private double fee;
	private int idOwner;
	private int idCaptain;
	

	
	public BoatDto() {
		super();
	}



	public BoatDto(int id, String plate, String name, String slipNumber, double fee, int idOwner,
			int idCaptain) {
		super();
		this.id = id;
		this.plate = plate;
		this.name = name;
		this.slipNumber = slipNumber;
		this.fee = fee;
		this.idOwner = idOwner;
		this.idCaptain = idCaptain;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPlate() {
		return plate;
	}



	public void setPlate(String plate) {
		this.plate = plate;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSlipNumber() {
		return slipNumber;
	}



	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}



	public double getFee() {
		return fee;
	}



	public void setFee(double fee) {
		this.fee = fee;
	}



	public int getIdOwner() {
		return idOwner;
	}



	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}



	public int getIdCaptain() {
		return idCaptain;
	}



	public void setIdCaptain(int idCaptain) {
		this.idCaptain = idCaptain;
	}



	@Override
	public int hashCode() {
		return Objects.hash(plate);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoatDto other = (BoatDto) obj;
		return Objects.equals(plate, other.plate);
	}
	
	
}
