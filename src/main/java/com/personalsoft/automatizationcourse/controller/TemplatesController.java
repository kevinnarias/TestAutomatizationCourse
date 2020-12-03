package com.personalsoft.automatizationcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller 
public class TemplatesController {	
	@GetMapping ("{name}")
	public String helloWorld(Model model, @PathVariable String name) {
		model.addAttribute("mensaje", "Hola "+name+", buenas noches" );
		return "index";
		
	}
	@GetMapping("/calculo")
	public String resultado(Model resultado,
					@RequestParam(value="valor1", required =false) double number1,
					@RequestParam(value="valor2", required =false) double number2){
				resultado.addAttribute("suma", "El resultado de la suma es " + (number1+number2));
				resultado.addAttribute("multiplicar", "El resultado de la multiplicacion es " + (number1*number2));
				resultado.addAttribute("dividir", "El resultado de la division es " + (number1/number2));
				return "result";
}
				
		
			
	}
		
	