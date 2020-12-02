package com.personalsoft.automatizationcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller 
public class TemplatesController {	
	@GetMapping ("{name}")
	public String helloWorld(Model model, @PathVariable String name) {
		model.addAttribute("mensaje", "Hola "+name+", buenas noches" );
		return "index";
		
		
			
	}
	
	
	

}
