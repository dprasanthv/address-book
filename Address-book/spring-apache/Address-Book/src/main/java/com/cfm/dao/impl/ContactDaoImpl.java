package com.cfm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfm.dao.IContactDao;
import com.cfm.model.Contact;
import com.cfm.repository.ContactRepository;
import com.cfm.utility.ContactHelperUtil;

@Repository("contactDaoImpl")
public class ContactDaoImpl implements IContactDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactDaoImpl.class);

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getAll() {
		return (List<Contact>) contactRepository.findAll();
	}

	@Override
	public Contact getContactById(String id) {
		Optional<Contact> contact = contactRepository.findById(id);
		if(contact.isPresent()) {
			return contact.get();
		}
		LOGGER.error("Contact with id: " + id + " cannot be found");
		return null;
	}

	@Override
	public void saveContact(Contact contact) {
		contactRepository.save(ContactHelperUtil.addDatesWhenSaveContact(contact));
	}

	@Override
	public void saveBulkContacts(List<Contact> contacts) {
		List<Contact> updatedContacts = new ArrayList<>();
		for (Contact contact : contacts) {
			updatedContacts.add(ContactHelperUtil.addDatesWhenSaveContact(contact));
		}
		contactRepository.saveAll(updatedContacts);

	}

	@Override
	public Contact updateContact(Contact contact) {
		if(getContactById(contact.getId()) != null) {
			return contactRepository.save(ContactHelperUtil.updateDatesWhenUpdateContact(contact));
		}
		LOGGER.error("Contact with id: " + contact.getId() + " cannot be found. So It could not be updated");
		return contact;
	}

	@Override
	public void deleteContactById(String id) {
		contactRepository.deleteById(id);
	}

}
