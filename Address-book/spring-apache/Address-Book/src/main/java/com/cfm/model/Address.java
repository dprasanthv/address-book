package com.cfm.model;

import java.io.Serializable;

public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String streetAddress, String city, String state, String zipCode, String country) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

}
