package es.atos.club.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;



@Entity
@Table(name="captain")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="person_id", referencedColumnName = "person_id")
public class Captain extends Person{
	

	@Column(name="title_number")
	private String titleNumber;
	
	@OneToOne(mappedBy = "captain")
	@JsonIgnore
	private Boat boat;
	
	
	public Captain() {
		super();
	}


	public Captain(int idNumber, String dni, String name, String email, String phone, String titleNumber, Boat boat) {
		super(idNumber, dni, name, email, phone);
		this.titleNumber = titleNumber;
		this.boat = boat;
	}
	
	public Captain(String dni,String name, String email, String phone, String titleNumber, Boat boat) {
		super(dni, name, email, phone);
		this.titleNumber = titleNumber;
		this.boat = boat;
	}


	public String getTitleNumber() {
		return titleNumber;
	}


	public void setTitleNumber(String titleNumber) {
		this.titleNumber = titleNumber;
	}


	public Boat getBoat() {
		return boat;
	}


	public void setBoat(Boat boat) {
		this.boat = boat;
	}




	@Override
	public String toString() {
		return "Captain [titleNumber=" + titleNumber + ", boat=" + boat.getId() + ", " + super.toString() + "]";
	}
}
