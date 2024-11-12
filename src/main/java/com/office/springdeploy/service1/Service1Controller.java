package com.office.springdeploy.service1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/service1")
public class Service1Controller {

	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		String nextPage = "service1/home";
		
		return nextPage;
		
	}
	
}
