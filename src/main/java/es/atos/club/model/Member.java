package es.atos.club.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;

import jakarta.persistence.OneToMany;

import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

@Entity
@Table(name="member")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="person_id", referencedColumnName = "person_id")
public class Member extends Person {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_number")
	private int memberNumber;
	

	@OneToMany(mappedBy="member", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Boat> boats = new ArrayList<>();
	
	
	public Member() {
		super();
	}

	
	public Member(int idNumber, String dni, String name, String email, String phone, List<Boat> boats) {
		super(idNumber, dni, name, email, phone);
		this.boats = boats;
	}
	
	

	public Member(int idNumber, String dni, String name, String email, String phone, int memberNumber, List<Boat> boats) {
		super(idNumber, dni, name, email, phone);
		this.memberNumber = memberNumber;
		this.boats = boats;
	}


	public Member(String dni, String name, String email, String phone, List<Boat> boats) {
		super(dni, name, email, phone);
		this.boats = boats;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}



	public List<Boat> getBoats() {
		return boats;
	}



	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}



	@Override
	public String toString() {
		return "Member [memberNumber=" + memberNumber + ", " + super.toString() + "]";
	}
	
	
	
	

	
	
}
