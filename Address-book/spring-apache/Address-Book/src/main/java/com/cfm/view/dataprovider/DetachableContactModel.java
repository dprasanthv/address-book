package com.cfm.view.dataprovider;

import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;

public class DetachableContactModel extends LoadableDetachableModel<Contact> {

	@SpringBean
	private IContactService contactService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1353798986958940210L;
	private final String id;

	/**
	 * @param c
	 */
	public DetachableContactModel(Contact c) {
		this(c.getId());
	}

	/**
	 * @param id
	 */
	public DetachableContactModel(String id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
	}

	/**
	 * used for dataview with ReuseIfModelsEqualStrategy item reuse strategy
	 * 
	 * @see org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof DetachableContactModel) {
			DetachableContactModel other = (DetachableContactModel) obj;
			return other.id == id;
		}
		return false;
	}

	/**
	 * @see org.apache.wicket.model.LoadableDetachableModel#load()
	 */
	@Override
	protected Contact load() {
		// loads contact from the database
		return contactService.getContactById(id);
	}
}
