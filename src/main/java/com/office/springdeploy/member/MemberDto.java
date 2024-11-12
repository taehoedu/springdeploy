package com.office.springdeploy.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_mail;
	private String m_phone;
	private String m_profile_thum;
	private String m_reg_date;
	private String m_mode_date;
	private String m_role;
    
}
