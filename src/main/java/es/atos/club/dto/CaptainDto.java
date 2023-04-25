package es.atos.club.dto;


import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CaptainDto {
	
	private int id;
	@NotNull(message = "DNI no válido: DNI nulo")
	@NotBlank(message = "DNI no válido: DNI vacío")
	private String idNumber;
	@NotNull(message = "Nombre no válido: Nombre nulo")
	@NotBlank(message = "Nombre no válido: Nombre vacío")
	private String name;
	@NotNull(message = "Email no válido: Email nulo")
	@Email(message = "Email no válido: No es un email")
	private String email;
	@NotNull(message = "Teléfono no válido: Teléfono nulo")
	@NotBlank(message = "Teléfono no válido: Teléfono vacío")
	private String phone;
	@NotNull(message = "Título del capitán no válido: Título nulo")
	@NotBlank(message = "Título del capitán no válido: Título vacío")
	private String titleNumber;
	private int boatId;
	private String boatPlate;
	
	
	public CaptainDto() {
		super();
	}


	public CaptainDto(int id, String idNumber, String name, String email, String phone, String titleNumber, 
			int boatId, String boatPlate) {
		super();
		this.id = id;
		this.idNumber = idNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.titleNumber = titleNumber;
		this.boatId = boatId;
		this.boatPlate = boatPlate;
	}


	public CaptainDto(String idNumber, String name, String email, String phone, String titleNumber, int boat_id,
			String boatPlate) {
		super();
		this.idNumber = idNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.titleNumber = titleNumber;
		this.boatId = boat_id;
		this.boatPlate = boatPlate;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTitleNumber() {
		return titleNumber;
	}


	public void setTitleNumber(String titleNumber) {
		this.titleNumber = titleNumber;
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
		return Objects.hash(idNumber);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaptainDto other = (CaptainDto) obj;
		return Objects.equals(idNumber, other.idNumber);
	}
	
	
}
