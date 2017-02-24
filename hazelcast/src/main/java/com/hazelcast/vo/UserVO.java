package com.hazelcast.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1827765369978649250L;
	private String name;
	private String phone;
	private String email;
	private String surname;

	public UserVO(String name, String email, String phone, String surname) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
