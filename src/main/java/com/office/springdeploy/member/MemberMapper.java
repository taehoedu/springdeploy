package com.office.springdeploy.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	boolean isMember(String m_id);

	int insertMember(MemberDto memberDto);

	MemberDto selectMemberByMId(String username);
	
	MemberDto getLoginedMemberByMId(String loginedMemberID);

	int updateMemberForModify(MemberDto memberDto);

	int deleteMemberByMId(String loginedMemberID);

	

}
