package es.atos.club.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.atos.club.dto.CaptainDto;
import es.atos.club.exception.ElementNotFoundException;
import es.atos.club.model.Boat;
import es.atos.club.model.Captain;
import es.atos.club.model.Member;
import es.atos.club.repository.BoatRepository;
import es.atos.club.repository.CaptainRepository;


@Service
public class CaptainService {
	
	@Autowired
	private CaptainRepository repository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	public CaptainDto addCaptain(CaptainDto boatDto) {
		repository.save(convertCaptainDtoToCaptainPost(boatDto));
		return boatDto;
	}
	
	public List<CaptainDto> findAllCaptains() {
		return convertCaptainListToCaptainDtoList(repository.findAll());
	}
	
	public CaptainDto findCaptainById(int id) {
		Captain boat = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		return convertCaptainToCaptainDto(boat);
	}
	
	public CaptainDto updateCaptain(int id, CaptainDto updatedCaptainDto) {
		Captain originalCaptain =  repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		Captain updatedCaptain = convertCaptainDtoToCaptain(updatedCaptainDto);
		
		if(!originalCaptain.getName().equals(updatedCaptain.getName())) {
			originalCaptain.setName(updatedCaptain.getName());
		}
		if(!originalCaptain.getEmail().equals(updatedCaptain.getEmail())) {
			originalCaptain.setEmail(updatedCaptain.getEmail());
		}
		if(!originalCaptain.getPhone().equals(updatedCaptain.getPhone())) {
			originalCaptain.setPhone(updatedCaptain.getPhone());
		}
		if(!originalCaptain.getDni().equals(updatedCaptain.getDni())) {
			originalCaptain.setDni(updatedCaptain.getDni());
		}
		if(!originalCaptain.getTitleNumber().equals(updatedCaptain.getTitleNumber())) {
			originalCaptain.setTitleNumber(updatedCaptain.getTitleNumber());
		}
		
		repository.save(originalCaptain);
		return convertCaptainToCaptainDto(originalCaptain);
		
	}
		
	
	public void deleteCaptain(int id) {
		Captain captain = repository.findById(id).orElse(null);
		if(captain==null) {
			throw new ElementNotFoundException(id);
		}else {
			repository.delete(captain);
		}	}
	
	
	
	private List<CaptainDto> convertCaptainListToCaptainDtoList(List<Captain> captainList) {
		List<CaptainDto> captainDtoList = new ArrayList<>();
		for(Captain captain : captainList) {
			CaptainDto captainDto = convertCaptainToCaptainDto(captain);
			captainDtoList.add(captainDto);
		}
		return captainDtoList;
	}
	
	private CaptainDto convertCaptainToCaptainDto(Captain captain) {
		if(captain.getBoat()!=null) {
			return new CaptainDto(captain.getIdNumber(), captain.getDni(), captain.getName(), captain.getEmail(), 
					captain.getPhone(), captain.getTitleNumber(), captain.getBoat().getId(), captain.getBoat().getPlate());
		}else {
			return new CaptainDto(captain.getIdNumber(), captain.getDni(), captain.getName(), captain.getEmail(), 
					captain.getPhone(), captain.getTitleNumber(), -1, null);
		}
		
	}
	
	private Captain convertCaptainDtoToCaptain(CaptainDto captainDto) {
		Boat boat = boatRepository.findById(captainDto.getBoatId()).orElse(null);
		return new Captain(captainDto.getIdNumber(), captainDto.getName(), captainDto.getEmail(), 
				captainDto.getPhone(), captainDto.getTitleNumber(), boat);
	}
	
	private Captain convertCaptainDtoToCaptainPost(CaptainDto captainDto) {
		Boat boat = boatRepository.findById(captainDto.getBoatId()).orElse(null);
		return new Captain(captainDto.getIdNumber(), captainDto.getName(), captainDto.getEmail(), 
				captainDto.getPhone(), captainDto.getTitleNumber(), boat);
	}
	
}
