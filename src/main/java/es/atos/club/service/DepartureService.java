package es.atos.club.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.atos.club.dto.DepartureDto;
import es.atos.club.exception.ElementNotFoundException;
import es.atos.club.model.Boat;
import es.atos.club.model.Departure;
import es.atos.club.model.Member;
import es.atos.club.repository.BoatRepository;
import es.atos.club.repository.DepartureRepository;


@Service
public class DepartureService {
	
	@Autowired
	private DepartureRepository repository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	
	public DepartureDto addDeparture(DepartureDto departureDto) {
		repository.save(convertDepartureDtoToDeparturePost(departureDto));
		return departureDto;
	}
	
	
	public List<DepartureDto> findAllDepartures() {
	return convertDepartureListToDepartureDtoList(repository.findAll());
}
	
	public DepartureDto findDepartureById(long id) {
		Departure departure = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		return convertDepartureToDepartureDto(departure);
	}
	
	public DepartureDto updateDeparture(long id, DepartureDto updatedDepartureDto) {
		Departure originalDeparture = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		Departure updatedDeparture = convertDepartureDtoToDeparture(updatedDepartureDto);
		if(!originalDeparture.getDepartureDate().isEqual(updatedDeparture.getDepartureDate())) {
			originalDeparture.setDepartureDate(updatedDeparture.getDepartureDate());
		}
		if(!originalDeparture.getDestination().equals(updatedDeparture.getDestination())) {
			originalDeparture.setDestination(updatedDeparture.getDestination());
		}
		if(!originalDeparture.getBoat().equals(updatedDeparture.getBoat())) {
			originalDeparture.setBoat(updatedDeparture.getBoat());
		}
		repository.save(originalDeparture);
		return convertDepartureToDepartureDto(originalDeparture);
	}
	
	
	public void deleteDeparture(long id) {
		Departure departure = repository.findById(id).orElse(null);
		if(departure==null) {
			throw new ElementNotFoundException(id);
		}else {
			repository.delete(departure);
		}
	}
	
	
	private List<DepartureDto> convertDepartureListToDepartureDtoList(List<Departure> departureList) {
		List<DepartureDto> departureDtoList = new ArrayList<>();
		for(Departure departure : departureList) {
			DepartureDto departureDto = convertDepartureToDepartureDto(departure);
			departureDtoList.add(departureDto);
		}
		return departureDtoList;
	}
	
	
	private DepartureDto convertDepartureToDepartureDto(Departure departure) {
		return new DepartureDto(departure.getDepartureId(), departure.getDepartureDate(), 
				departure.getDestination(), departure.getBoat().getId(), departure.getBoat().getPlate());
	}
	
	private Departure convertDepartureDtoToDeparture(DepartureDto departureDto) {
		Boat boat = boatRepository.findById(departureDto.getBoatId()).orElse(null);
		return new Departure(departureDto.getId(), departureDto.getDepartureDate(),
				departureDto.getDestination(), boat);
	}
	
	
	private Departure convertDepartureDtoToDeparturePost(DepartureDto departureDto) {
		Boat boat = boatRepository.findById(departureDto.getBoatId()).orElse(null);
		return new Departure(departureDto.getDepartureDate(),
				departureDto.getDestination(), boat);
	}
}
