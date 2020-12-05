package com.personalsoft.automatizationcourse.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class Person {
	
	@NotBlank(message = "Name is mandatory")
	@Size(max = 10)
	@Pattern(regexp = "[A-Za-z0-9]+$", message = "Not valid special characters")
	private String name;
	
	@NotBlank(message = "lastName is mandatory")
	private String lastName;
	
	@Email(message = "The field must be valid email")
	private String email;
	
	@Min(value = 18, message = "There must be at least {value} in the test case")
	private int age;
	
	@Size(min = 2, max = 2, message = "Must be 2 characters long")
	private String documentType;
	
	@Size(min = 8, max = 10, message = "Must be between {min} and {max} characters long")
	private String documentNumber;

	public Person(String name, String lastName, int age, String documentType, String documentNumber) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.documentType = documentType;
		this.documentNumber = documentNumber;

	}

	public Person() {
		super();
	}

}
