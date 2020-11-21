package com.personalsoft.automatizationcourse.model;

public class Person {
	private String name;
	private String lastName;
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
