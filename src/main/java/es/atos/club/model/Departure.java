package es.atos.club.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="departure")
public class Departure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="departure_id")
	private long departureId;
	@Column(name="departure_date")
	private LocalDateTime departureDate;
	private String destination;
	
	@ManyToOne
	@JoinColumn(name="boat")
	@JsonIgnore
	private Boat boat;
	
	

	public Departure() {
		super();
	}



	public Departure(long departureId, LocalDateTime departureDate, String destination, Boat boat) {
		super();
		this.departureId = departureId;
		this.departureDate = departureDate;
		this.destination = destination;
		this.boat = boat;
	}



	public Departure(LocalDateTime departureDate, String destination, Boat boat) {
		super();
		this.departureDate = departureDate;
		this.destination = destination;
		this.boat = boat;
	}



	public long getDepartureId() {
		return departureId;
	}



	public void setDepartureId(long departureId) {
		this.departureId = departureId;
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



	public Boat getBoat() {
		return boat;
	}



	public void setBoat(Boat boat) {
		this.boat = boat;
	}



	@Override
	public int hashCode() {
		return Objects.hash(departureId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departure other = (Departure) obj;
		return departureId == other.departureId;
	}



	@Override
	public String toString() {
		return "Departure [departureId=" + departureId + ", departureDate=" + departureDate + ", destination="
				+ destination + ", boat=" + boat.getId() + "]";
	}
	
}
