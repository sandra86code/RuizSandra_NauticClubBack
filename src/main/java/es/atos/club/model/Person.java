package es.atos.club.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;



@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="person_id")
	private int idNumber;
	@Column(name="person_dni")
	private String dni;
	private String name;
	private String email;
	private String phone;
	
	
	protected Person() {
		super();
	}
	
	
	
	
	protected Person(int idNumber, String dni, String name, String email, String phone) {
		super();
		this.idNumber = idNumber;
		this.dni = dni;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	protected Person(String dni, String name, String email, String phone) {
		super();
		this.dni = dni;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}


	



	public int getIdNumber() {
		return idNumber;
	}




	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}




	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
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
		Person other = (Person) obj;
		return Objects.equals(idNumber, other.idNumber);
	}




	@Override
	public String toString() {
		return "idNumber=" + idNumber + ", name=" + name + ", email=" + email + ", phone=" + phone;
	}
	
}
