package com.cfm.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.util.io.IClusterable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Contact")
public class Contact implements IClusterable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private String homePhone;

	private String cellPhone;

	private String email;

	private Address mailingAddress;

	private Address permanentAddress;

	private Date createdDate;

	private Date lastUpdatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", email=" + email
				+ ", mailingAddress=" + mailingAddress + ", permanentAddress=" + permanentAddress + "]";
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String firstName, String lastName, Date dateOfBirth, String homePhone, String cellPhone,
			String email, Address mailingAddress, Address permanentAddress, Date createdDate, Date lastUpdatedDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.email = email;
		this.mailingAddress = mailingAddress;
		this.permanentAddress = permanentAddress;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Contact(String id, String firstName, String lastName, Date dateOfBirth, String homePhone, String cellPhone,
			String email, Address mailingAddress, Address permanentAddress, Date createdDate, Date lastUpdatedDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.email = email;
		this.mailingAddress = mailingAddress;
		this.permanentAddress = permanentAddress;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	

}
