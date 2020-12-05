package com.personalsoft.automatizationcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.personalsoft.automatizationcourse.model.Person;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FormController {

	@GetMapping(value = "/showForm")
	public String showForm(Model model) {
	  model.addAttribute("person", new Person());
	  return "concatForm";
	}

	@PostMapping(value = "/processForm")
	public String processForm(Model model, @ModelAttribute(value="person") Person person) {
		log.info("Persona: " + person);
		model.addAttribute("person",person);
		model.addAttribute("result", "Su nombre completo es: " + person.getName() + " " + person.getLastName());
		return "concatResult";
	}
}
