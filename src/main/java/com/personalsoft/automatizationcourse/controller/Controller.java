package com.personalsoft.automatizationcourse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personalsoft.automatizationcourse.model.Person;

@RestController
@RequestMapping(value = "api")
public class Controller {

	@GetMapping("{name}")
	public String helloWorld(@PathVariable String name,
			@RequestParam(value = "apellido", required = false) String lastName) {
		if (lastName == null) {
			return "Hello, mi name is: " + name + ".";
		}
		return "Hello, mi name is: " + name + " " + lastName + ".";
	}

	@PostMapping
	public String helloWorldPost(@RequestBody Person person) {

		return "Hello, mi name is: " + person.getName() + " " +  person.getLastName() + " " + person.getOld() + ".";

	}
}

