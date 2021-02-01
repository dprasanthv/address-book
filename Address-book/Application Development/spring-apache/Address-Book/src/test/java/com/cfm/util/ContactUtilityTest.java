package com.cfm.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.cfm.model.Contact;
import com.cfm.utility.ContactHelperUtil;

public class ContactUtilityTest {

	@Test
	public void testGetPhoneNumberWhenNoNumber() {
		Contact contact = new Contact();
		String phoneNumberText = ContactHelperUtil.getPhoneNumber(contact);
		assertTrue(phoneNumberText.equals(""));
	}

	@Test
	public void testGetPhoneNumberWhenCellPhoneNumber() {
		Contact contact = new Contact();
		contact.setCellPhone("1234567890");
		String phoneNumberText = ContactHelperUtil.getPhoneNumber(contact);
		assertTrue(phoneNumberText.equals("123-456-7890"));
	}

	@Test
	public void testGetPhoneNumberWhenHomePhoneNumberNotHomePhone() {
		Contact contact = new Contact();
		contact.setCellPhone("1234567890");
		contact.setHomePhone("9876543210");
		String phoneNumberText = ContactHelperUtil.getPhoneNumber(contact);
		assertFalse(phoneNumberText.equals("987-654-3210"));
	}

	@Test
	public void testGetPhoneNumberWhenHomePhoneNumberNotHomePhone2() {
		Contact contact = new Contact();
		contact.setCellPhone("1234567890");
		contact.setHomePhone("9876543210");
		String phoneNumberText = ContactHelperUtil.getPhoneNumber(contact);
		assertTrue(phoneNumberText.equals("123-456-7890"));
	}

	@Test
	public void testGetPhoneNumberWhenHomePhoneNumberButNoCellPhone() {
		Contact contact = new Contact();
		contact.setHomePhone("9876543210");
		String phoneNumberText = ContactHelperUtil.getPhoneNumber(contact);
		assertTrue(phoneNumberText.equals("987-654-3210"));
	}

	@Test
	public void testMaskPhoneNumber() {
		String phoneNumberText = ContactHelperUtil.maskPhoneNumber("9876543210");
		assertTrue(phoneNumberText.equals("987-654-3210"));
	}

	@Test
	public void testMaskPhoneNumberFailScenario() {
		String phoneNumberText = ContactHelperUtil.maskPhoneNumber("9876543210");
		assertFalse(phoneNumberText.equals("987-64-3210"));
	}

	@Test
	public void testgetContactFromStringWhenInvalidDate() {
		String[] array = new String[6];
		array[0] = "John";
		array[1] = "Doe";
		array[2] = "M3-02-1976";
		array[3] = "6825826549";
		array[4] = "7896542938";
		array[5] = "john.doe@yahh.com";
		assertThrows(ParseException.class, () -> {
			ContactHelperUtil.getContactFromString(array);
		});
	}

	@Test
	public void testgetContactFromStringWhenValidDate() {
		String[] array = new String[6];
		array[0] = "John";
		array[1] = "Doe";
		array[2] = "03-02-1976";
		array[3] = "6825826549";
		array[4] = "7896542938";
		array[5] = "john.doe@yahh.com";
		assertDoesNotThrow(() -> {
			ContactHelperUtil.getContactFromString(array);
		});
	}

}
