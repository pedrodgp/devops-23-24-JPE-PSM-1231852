/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private Integer jobYears;
	private String email;

	private Employee() {}

	public Employee(String firstName, String lastName, String description, Integer jobYears, String email) throws IllegalArgumentException {
		if (!isValidArguments(firstName, lastName, description, jobYears, email)) {
			throw new IllegalArgumentException("Invalid Employee arguments");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobYears = jobYears;
		this.email = email;
	}

	private boolean isValidArguments(String firstName, String lastName, String description, Integer jobYears, String email) {
		return isValidName(firstName) &&
				isValidName(lastName) &&
				isValidDescription(description) &&
				isValidJobYears(jobYears) &&
				isValidEmail(email);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobYears, employee.jobYears) &&
			Objects.equals(email, employee.email);
	}

	private boolean isValidName(String name) {
		return (name != null && !name.trim().isEmpty());
	}
	private boolean isValidDescription(String description) {
		return (description != null && !description.trim().isEmpty());
	}
	private boolean isValidJobYears(Integer jobYears) {
		return jobYears != null;
	}
	private boolean isValidEmail(String email) {
		return (email != null && !email.trim().isEmpty() && email.matches("^.+@.+.$"));
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobYears, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (!isValidName(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException {
		if (!isValidName(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws IllegalArgumentException {
		if (!isValidDescription(description)) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}

	public Integer getJobYears() {
		return jobYears;
	}

	public void setJobYears(Integer jobYears) throws IllegalArgumentException {
		if (!isValidJobYears(jobYears)) {
			throw new IllegalArgumentException("Invalid job years");
		}
		this.jobYears = jobYears;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws IllegalArgumentException {
		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobYears='" + jobYears + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
// end::code[]
