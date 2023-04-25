package es.atos.club.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.atos.club.dto.MemberDto;
import es.atos.club.exception.ElementNotFoundException;
import es.atos.club.model.Member;
import es.atos.club.repository.MemberRepository;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	
	public MemberDto addMember(MemberDto memberDto) {
		repository.save(convertMemberDtoToMemberPost(memberDto));
		return memberDto;
	}
	
	public List<MemberDto> findAllMembers() {
		return convertMemberListToMemberDtoList(repository.findAll());
	}
	
	public MemberDto findMemberById(int id) {
		Member boat = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		return convertMemberToMemberDto(boat);
	}
	
	public MemberDto updateMember(int id, MemberDto updatedMemberDto) {
		Member originalMember =  repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
		Member updatedMember = convertMemberDtoToMember(updatedMemberDto);
		
		if(!originalMember.getName().equals(updatedMember.getName())) {
			originalMember.setName(updatedMember.getName());
		}
		if(!originalMember.getEmail().equals(updatedMember.getEmail())) {
			originalMember.setEmail(updatedMember.getEmail());
		}
		if(!originalMember.getPhone().equals(updatedMember.getPhone())) {
			originalMember.setPhone(updatedMember.getPhone());
		}
		if(!originalMember.getDni().equals(updatedMember.getDni())) {
			originalMember.setDni(updatedMember.getDni());
		}
		repository.save(originalMember);
		return convertMemberToMemberDto(originalMember);
		
	}
		
	
	public void deleteMember(int id) {
		Member member = repository.findById(id).orElse(null);
		if(member==null) {
			throw new ElementNotFoundException(id);
		}else {
			repository.delete(member);
		}
	}
	
	
	
	private List<MemberDto> convertMemberListToMemberDtoList(List<Member> memberList) {
		List<MemberDto> memberDtoList = new ArrayList<>();
		for(Member member : memberList) {
			MemberDto memberDto = convertMemberToMemberDto(member);
			memberDtoList.add(memberDto);
		}
		return memberDtoList;
	}
	
	private MemberDto convertMemberToMemberDto(Member member) {
		return new MemberDto(member.getIdNumber(), member.getDni(), member.getName(), member.getEmail(), 
				member.getPhone(), member.getMemberNumber());
	}
	
	private Member convertMemberDtoToMember(MemberDto memberDto) {
		return new Member(memberDto.getId(), memberDto.getIdNumber(), memberDto.getName(), memberDto.getEmail(), 
				memberDto.getPhone(), null);
	}
	
	private Member convertMemberDtoToMemberPost(MemberDto memberDto) {
		return new Member(memberDto.getIdNumber(), memberDto.getName(), memberDto.getEmail(), 
				memberDto.getPhone(), null);
	}

}
