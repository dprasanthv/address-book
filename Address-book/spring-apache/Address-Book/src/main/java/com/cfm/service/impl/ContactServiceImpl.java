package com.cfm.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfm.dao.IContactDao;
import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.utility.ContactHelperUtil;

@Service
public class ContactServiceImpl implements IContactService {

	Comparator<Contact> compareByName = Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName);

	@Autowired
	private IContactDao contactDao;

	@Override
	public List<Contact> getAll() {
		List<Contact> contacts = (List<Contact>) contactDao.getAll();
		Collections.sort(contacts, compareByName);
		return contacts;
	}

	@Override
	public Contact getContactById(String id) {
		return contactDao.getContactById(id);
	}

	@Override
	public void saveContact(Contact contact) {
		contactDao.saveContact(ContactHelperUtil.addDatesWhenSaveContact(contact));
	}

	@Override
	public Contact updateContact(Contact contact) {
		return contactDao.updateContact(ContactHelperUtil.updateDatesWhenUpdateContact(contact));
	}

	@Override
	public void deleteContactById(String id) {
		contactDao.deleteContactById(id);
	}

	@Override
	public List<Contact> getContactsFilteredBySearch(String text) {
		List<Contact> contacts = (List<Contact>) contactDao.getAll();
		return contacts.stream().filter(contact -> {
			String lowercase = text.toLowerCase();
			return text == null || (contact.getFirstName().toLowerCase().contains(lowercase) || contact.getLastName().toLowerCase().contains(lowercase));
		}).collect(Collectors.toList());
	}

	@Override
	public void saveBulkContacts(List<Contact> contacts) {
		contactDao.saveBulkContacts(contacts);
	}

}
