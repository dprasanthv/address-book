package com.cfm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cfm.model.Contact;

@Repository
public interface ContactRepository  extends CrudRepository<Contact, String> {

}
