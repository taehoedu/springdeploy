package com.office.springdeploy.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberService {

	final static public int ID_ALREADY_EXISTS 				= -2;
	final static public int DATABASE_COMMUNICATION_TROUBLE 	= -1;
	final static public int INSERT_FAIL_AT_DATABASE 		= 0;
	final static public int INSERT_SUCCESS_AT_DATABASE 		= 1;
	
	final private MemberMapper memberMapper;
	final private PasswordEncoder passwordEncoder;
	
	public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	public int createAccountConfirm(MemberDto memberDto) {
		log.info("createAccountConfirm()");
		
		boolean isMember = memberMapper.isMember(memberDto.getM_id());
		if (!isMember) {
			memberDto.setM_pw(passwordEncoder.encode(memberDto.getM_pw()));
			int result = memberMapper.insertMember(memberDto);
			
			switch (result) {
			case DATABASE_COMMUNICATION_TROUBLE:
				log.info("DATABASE COMMUNICATION TROUBLE");
				break;

			case INSERT_FAIL_AT_DATABASE:
				log.info("INSERT FAIL AT DATABASE");
				break;
				
			case INSERT_SUCCESS_AT_DATABASE:
				log.info("INSERT SUCCESS AT DATABASE");
				break;
			}
			
			return result;
			
		} else {
			return ID_ALREADY_EXISTS;
			
		}
		
	}
	
	public MemberDto memberModifyForm(String loginedMemberID) {
		log.info("memberModifyForm");
		
		return memberMapper.getLoginedMemberByMId(loginedMemberID);
		
	}
	
	public int memberModifyConfirm(MemberDto memberDto) {
		log.info("memberModifyConfirm()");
		
		return memberMapper.updateMemberForModify(memberDto);
		
	}
	
	public int memberDeleteConfirm(String loginedMemberID) {
		log.info("memberDeleteConfirm()");
		
		return memberMapper.deleteMemberByMId(loginedMemberID);
		
	}
	
}
