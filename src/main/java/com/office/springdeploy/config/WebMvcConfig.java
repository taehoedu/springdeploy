package com.office.springdeploy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${server.name}")
	private String serverName;
	
	@Value("${server.resource.profile-img}")
	private String serverResourceProfileImg;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("addResourceHandlers");
		
		// Windows
//		registry.addResourceHandler("/profileImg/**")
//				.addResourceLocations("file:///c://member/profile/");
		
		// Ubuntu
//		registry.addResourceHandler("/profileImg/**")
//				.addResourceLocations("file:///member/profile/");
		
		/*
		if (serverName.equals(AppConfig.SERVER_NAME_LOCAL)) {
			registry.addResourceHandler("/profileImg/**")
					.addResourceLocations("file:///c://member/profile/");
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_DEV)) {
			registry.addResourceHandler("/profileImg/**")
					.addResourceLocations("file:///member/profile/");
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_PROD)) {
			registry.addResourceHandler("/profileImg/**")
			.addResourceLocations("file:///member/profile/");
	
		}
		*/
		
		registry.addResourceHandler("/profileImg/**")
		.addResourceLocations(serverResourceProfileImg);
		
	}
	
}
