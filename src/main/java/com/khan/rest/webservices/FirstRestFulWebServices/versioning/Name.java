package com.khan.rest.webservices.FirstRestFulWebServices.versioning;

public class Name {
	private String firstName;
	private String lastName;
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString() {
		return "Personv2 [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
