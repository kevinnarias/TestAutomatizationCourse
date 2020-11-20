package com.personalsoft.automatizationcourse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("{name}")
	public String helloWorld(@PathVariable String name, @RequestParam String lastName) {
		return "Hello, mi name is: " + name + " " + lastName + ".";
	}
}
