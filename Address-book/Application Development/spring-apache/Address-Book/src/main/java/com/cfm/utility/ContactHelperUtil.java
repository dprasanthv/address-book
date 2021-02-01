package com.cfm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cfm.model.Address;
import com.cfm.model.Contact;

public class ContactHelperUtil {

	static String pattern = "MM-dd-yyyy";

	static SimpleDateFormat formatter = new SimpleDateFormat(pattern);

	public static String getPhoneNumber(Contact contact) {
		if (StringUtils.isAllBlank(contact.getCellPhone()) && StringUtils.isAllBlank(contact.getHomePhone())) {
			return "";
		} else if (contact.getCellPhone() == null) {
			return maskPhoneNumber(contact.getHomePhone());
		}
		return maskPhoneNumber(contact.getCellPhone());
	}

	public static String maskPhoneNumber(String phone) {
		return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
	}

	public static Contact getContactFromString(String[] contact) throws ParseException {
		String firstName = contact[0];
		String lastName = contact[1];
		Date dateOfBirth = formatter.parse(contact[2]);
		String homePhone = contact[3];
		String cellPhone = contact[4];
		String email = contact[5];
		Address mailingAddress = new Address();
		Address permanentAddress = new Address();
		Date createdDate = new Date();
		Date lastUpdatedDate = new Date();
		return new Contact(firstName, lastName, dateOfBirth, cellPhone, homePhone, email, mailingAddress,
				permanentAddress, createdDate, lastUpdatedDate);
	}

	public static String getCSVRepresentationOfContact(Contact contact) {
		return contact.getFirstName() + "," + contact.getLastName() + "," + formatter.format(contact.getDateOfBirth())
				+ "," + (StringUtils.isAllBlank(contact.getHomePhone()) ? "" : contact.getHomePhone()) + ","
				+ (StringUtils.isAllBlank(contact.getCellPhone()) ? "" : contact.getCellPhone()) + ","
				+ contact.getEmail();

	}

	public static Contact addDatesWhenSaveContact(Contact contact) {
		String firstName = contact.getFirstName();
		String lastName = contact.getLastName();
		Date dateOfBirth = contact.getDateOfBirth();
		String homePhone = contact.getHomePhone();
		String cellPhone = contact.getCellPhone();
		String email = contact.getEmail();
		Address mailingAddress = contact.getMailingAddress();
		Address permanentAddress = contact.getPermanentAddress();
		Date createdDate = new Date();
		Date lastUpdatedDate = new Date();
		return new Contact(firstName, lastName, dateOfBirth, cellPhone, homePhone, email, mailingAddress,
				permanentAddress, createdDate, lastUpdatedDate);
	}

	public static Contact updateDatesWhenUpdateContact(Contact contact) {
		String id = contact.getId();
		String firstName = contact.getFirstName();
		String lastName = contact.getLastName();
		Date dateOfBirth = contact.getDateOfBirth();
		String homePhone = contact.getHomePhone();
		String cellPhone = contact.getCellPhone();
		String email = contact.getEmail();
		Address mailingAddress = contact.getMailingAddress();
		Address permanentAddress = contact.getPermanentAddress();
		Date createdDate = contact.getCreatedDate();
		Date lastUpdatedDate = new Date();
		return new Contact(id, firstName, lastName, dateOfBirth, cellPhone, homePhone, email, mailingAddress,
				permanentAddress, createdDate, lastUpdatedDate);
	}
}
