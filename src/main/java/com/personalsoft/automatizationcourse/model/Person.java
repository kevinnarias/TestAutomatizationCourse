package com.personalsoft.automatizationcourse.model;

import javax.validation.constraints.Max;
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
	@Min(18)
	@Max(70)
	private int old;
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
	public Person(String name, String lastName, int old) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.old = old;
	}
	public Person() {
		super();
	}
	
}
