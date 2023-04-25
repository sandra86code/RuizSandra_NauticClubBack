package es.atos.club.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.atos.club.dto.BoatDto;
import es.atos.club.exception.ElementNotFoundException;
import es.atos.club.model.Boat;
import es.atos.club.model.Captain;
import es.atos.club.model.Member;
import es.atos.club.repository.BoatRepository;
import es.atos.club.repository.CaptainRepository;
import es.atos.club.repository.MemberRepository;

@Service
public class BoatService {
	
	@Autowired
	private BoatRepository repository;
	
	@Autowired
	private CaptainRepository captainRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	public BoatDto addBoat(BoatDto boatDto) {
		repository.save(convertBoatDtoToBoatPost(boatDto));
		return boatDto;
	}
	
	public List<BoatDto> findAllBoats() {
		return convertBoatListToBoatDtoList(repository.findAll());
	}
	
	public BoatDto findBoatById(int id) {
		Boat boat = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		return convertBoatToBoatDto(boat);
	}
	
	public BoatDto updateBoat(int id, BoatDto updatedBoatDto) {
		Boat originalBoat =  repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		Boat updatedBoat = convertBoatDtoToBoat(updatedBoatDto);
		
		if(!originalBoat.getName().equals(updatedBoat.getName())) {
			originalBoat.setName(updatedBoat.getName());
		}
		if(!originalBoat.getPlate().equals(updatedBoat.getPlate())) {
			originalBoat.setPlate(updatedBoat.getPlate());
		}
		if(!originalBoat.getSlipNumber().equals(updatedBoat.getSlipNumber())) {
			originalBoat.setSlipNumber(updatedBoat.getSlipNumber());
		}
		if(originalBoat.getFee()!=updatedBoat.getFee()) {
			originalBoat.setFee(updatedBoat.getFee());
		}
		if(!originalBoat.getMember().equals(updatedBoat.getMember())) {
			originalBoat.setMember(updatedBoat.getMember());
		}
		if(!originalBoat.getCaptain().equals(updatedBoat.getCaptain())) {
			originalBoat.setCaptain(updatedBoat.getCaptain());
		}
		repository.save(originalBoat);
		return convertBoatToBoatDto(originalBoat);
		
	}
	
	
	public void deleteBoat(int id) {
		Boat boat = repository.findById(id).orElse(null);
		if(boat==null) {
			throw new ElementNotFoundException(id);
		}else {
			repository.delete(boat);
		}
	}
	
	private List<BoatDto> convertBoatListToBoatDtoList(List<Boat> boatList) {
		List<BoatDto> boatDtoList = new ArrayList<>();
		for(Boat boat : boatList) {
			BoatDto boatDto = convertBoatToBoatDto(boat);
			boatDtoList.add(boatDto);
		}
		return boatDtoList;
	}
	
	private BoatDto convertBoatToBoatDto(Boat boat) {
		return new BoatDto(boat.getId(), boat.getPlate(), boat.getName(), 
				boat.getSlipNumber(), boat.getFee(), boat.getMember().getIdNumber(), 
				boat.getCaptain().getIdNumber());
	}
	
	private Boat convertBoatDtoToBoat(BoatDto boatDto) {
		Member member = memberRepository.findById(boatDto.getIdOwner()).orElse(null);
		Captain captain = captainRepository.findById(boatDto.getIdCaptain()).orElse(null);
		return new Boat(boatDto.getId(), boatDto.getPlate(), boatDto.getName(), boatDto.getSlipNumber(),
				boatDto.getFee(), member, captain, null);
	}
	
	private Boat convertBoatDtoToBoatPost(BoatDto boatDto) {
		Member member = memberRepository.findById(boatDto.getIdOwner()).orElse(null);
		Captain captain = captainRepository.findById(boatDto.getIdCaptain()).orElse(null);
		return new Boat(boatDto.getPlate(), boatDto.getName(), boatDto.getSlipNumber(),
				boatDto.getFee(), member, captain, null);
	}
}
