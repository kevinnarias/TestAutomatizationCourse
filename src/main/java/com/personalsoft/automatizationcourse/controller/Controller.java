package com.personalsoft.automatizationcourse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public String helloWorldPost(@RequestBody @Valid Person person) {

		return "Hello, mi name is: " + person.getName() + " " +  person.getLastName() + " " + person.getOld() + ".";

	}
	
	@PutMapping
	public String helloWorldPut(@RequestBody Person person) {

		return "Hello, mi name is: " + person.getName() + " " +  person.getLastName() + " " + person.getOld() + ".";

	}
	
	@DeleteMapping
	public String helloWorldDelete(@RequestBody Person person) {

		return "Hello, mi name is: " + person.getName() + " " +  person.getLastName() + " " + person.getOld() + ".";

	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}

