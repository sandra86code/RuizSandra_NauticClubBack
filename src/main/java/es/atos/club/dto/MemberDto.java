package es.atos.club.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MemberDto {
	
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
	private int memberNumber;
	
	
	public MemberDto() {
		super();
	}


	public MemberDto(int id, String idNumber, String name, String email, String phone, int memberNumber) {
		super();
		this.id = id;
		this.idNumber = idNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.memberNumber = memberNumber;
	}

	public MemberDto(int id, String idNumber, String name, String email, String phone) {
		super();
		this.id = id;
		this.idNumber = idNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public MemberDto(String idNumber, String name, String email, String phone, int memberNumber) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.email = email;
		this.phone = phone;
		this.memberNumber = memberNumber;
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


	public int getMemberNumber() {
		return memberNumber;
	}


	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}


	public MemberDto(int id) {
		super();
		this.id = id;
	}


	
	
	
}
