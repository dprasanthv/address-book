package com.cfm.service;

import java.util.List;

import com.cfm.model.Contact;

public interface IContactService {

	public List<Contact> getAll();
	
	public List<Contact> getContactsFilteredBySearch( String text);
	
	public Contact getContactById(String id);
	
	public void saveContact(Contact contact);
	
	public void saveBulkContacts(List<Contact> contacts);

	public Contact updateContact(Contact contact);
	
	public void deleteContactById(String id);
	
}
