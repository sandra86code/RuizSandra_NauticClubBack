package es.atos.club.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="boat")
public class Boat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="boat_id")
	private int id;
	private String plate;
	private String name;
	@Column(name="slip_number")
	private String slipNumber;
	private double fee;
	
	@ManyToOne
	@JoinColumn(name="owner", referencedColumnName = "person_id")
	private Member member;
	
	@OneToOne
	@JoinColumn(name = "captain", referencedColumnName = "person_id")
	private Captain captain;
	
	@OneToMany(mappedBy="boat", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Departure> departures = new ArrayList<>();
	
	
	public Boat() {
		super();
	}

	
	public Boat(int id) {
		super();
		this.id = id;
	}


	public Boat(String plate) {
		super();
		this.plate = plate;
	}

	
	public Boat(int id, String plate, String name, String slipNumber, double fee, Member member, Captain captain,
			List<Departure> departures) {
		super();
		this.id = id;
		this.plate = plate;
		this.name = name;
		this.slipNumber = slipNumber;
		this.fee = fee;
		this.member = member;
		this.captain = captain;
		this.departures = departures;
	}


	public Boat(String plate, String name, String slipNumber, double fee, Member member, Captain captain,
			List<Departure> departures) {
		super();
		this.plate = plate;
		this.name = name;
		this.slipNumber = slipNumber;
		this.fee = fee;
		this.member = member;
		this.captain = captain;
		this.departures = departures;
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


	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public Captain getCaptain() {
		return captain;
	}


	public void setCaptain(Captain captain) {
		this.captain = captain;
	}


	public List<Departure> getDepartures() {
		return departures;
	}


	public void setDepartures(List<Departure> departures) {
		this.departures = departures;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boat other = (Boat) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Boat [id=" + id + ", plate=" + plate + ", name=" + name + ", slipNumber=" + slipNumber + ", fee=" + fee
				+ ", member=" + member.getIdNumber() + ", captain=" + captain.getIdNumber();
	}
}

