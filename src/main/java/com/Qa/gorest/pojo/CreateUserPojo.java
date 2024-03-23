package com.Qa.gorest.pojo;

public class CreateUserPojo {
	private String email;
	private String name;
	private String gender;
	private String status;
	
	public CreateUserPojo(String email, String name, String gender, String status) {
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
