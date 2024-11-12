package com.office.springdeploy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.office.springdeploy.member.MemberAccessDeniedHandler;
import com.office.springdeploy.member.MemberAuthenticationEntryPoint;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean PasswordEncoder passwordEncoder() {
		log.info("passwordEncoder()");
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.cors(cors -> cors.disable())
		.csrf(csrf -> csrf.disable());
		
		http
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/css/**",
						"/img/**",
						"/js/**",
						"/",
//						"/favicon.ico",
//						"/error",
						"/authentication_entry_point",
						"/member/create_account_form",
						"/member/create_account_confirm",
						"/member/create_account_ok",
						"/member/create_account_ng",
						"/member/member_login_form",
						"/member/member_login_ng"
						).permitAll()
				.requestMatchers(
						"/service1/**",
						"/service2/**").hasAnyRole("USER")
				.anyRequest().authenticated());
		
		http
		.exceptionHandling(exceptionConfig -> exceptionConfig
				.authenticationEntryPoint(new MemberAuthenticationEntryPoint())
				.accessDeniedHandler(new MemberAccessDeniedHandler()));
		
		http
		.formLogin(login -> login
				.loginPage("/member/member_login_form")
				.loginProcessingUrl("/member/member_login_confirm")
				.usernameParameter("m_id")
				.passwordParameter("m_pw")
				.successHandler((request, response, authentication) -> {
					log.info("[MEMBER LOGIN SUCCESS HANDER]");
					
					String targetURI = "/member/member_login_ok";
					response.sendRedirect(targetURI);
					
				})
				.failureHandler((request, response, exception) -> {
					log.info("[MEMBER LOGIN FAIL HANDER]");
					
					String targetURI = "/member/member_login_ng";
					response.sendRedirect(targetURI);
					
				}));
		
		http
		.logout(logout -> logout
				.logoutUrl("/member/member_logout_confirm")
				.logoutSuccessHandler((request, response, authentication) -> {
					log.info("[MEMBER LOGOUT SUCCESS HANDER]");
					
					response.sendRedirect("/");
					
				}));
		
		
		return http.build();
		
	}
	
}
