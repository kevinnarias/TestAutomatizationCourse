package com.personalsoft.automatizationcourse.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
	@NotNull(message = "Name is mandatory")
	@Size(max =10)
	private String name;
	@NotBlank(message = "lastName is mandatory")
	private String lastName;
	
	@Min(value = 18, message = "There must be at least {value} in the test case")
	private int old;
	
	private String documentType;
		
	
	@Size(min = 8, max = 10, message = "Must be between {min} and {max} characters long")
	private String documentNumber;
	
		
	public String getName() {
		return name;
			
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getOld() {
		return old;
	}
	public void setOld(int old) {
		this.old = old;
			
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	public Person(String name, String lastName, int old, String documentType, String documentNumber) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.old = old;
		this.documentType= documentType;
		this.documentNumber= documentNumber;
		
		
		
	}
	public Person() {
		super();
	}
	
}
