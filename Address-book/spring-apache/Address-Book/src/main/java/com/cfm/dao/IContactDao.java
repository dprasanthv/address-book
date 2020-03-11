package com.cfm.dao;

import java.util.List;

import com.cfm.model.Contact;

public interface IContactDao {

	public List<Contact> getAll();
		
	public Contact getContactById(String id);
	
	public void saveContact(Contact contact);
	
	public void saveBulkContacts(List<Contact> contacts);

	public Contact updateContact(Contact contact);
	
	public void deleteContactById(String id);
}
