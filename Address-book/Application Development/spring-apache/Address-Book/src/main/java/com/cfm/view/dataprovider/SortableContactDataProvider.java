package com.cfm.view.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.utility.ContactFilter;

public class SortableContactDataProvider extends SortableDataProvider<Contact, String>
		implements IFilterStateLocator<ContactFilter> {

	private static final long serialVersionUID = 909055803980473864L;

	private ContactFilter contactFilter = new ContactFilter();

	@SpringBean
	private IContactService contactService;

	/**
	 * constructor
	 */
	public SortableContactDataProvider() {
		// set default sort
		setSort("firstName", SortOrder.ASCENDING);
	}

	@Override
	public Iterator<Contact> iterator(long first, long count) {
		List<Contact> contactsFound = contactService.getAll();

		return filterContacts(contactsFound).iterator();
	}

	private List<Contact> filterContacts(List<Contact> contactsFound) {
		ArrayList<Contact> result = new ArrayList<>();
		
		String text = contactFilter.getText();

		for (Contact contact : contactsFound) {

			if (text != null && !contact.getFirstName().contains(text) && !contact.getLastName().contains(text)) {
				continue;
			}

			result.add(contact);
		}

		return result;
	}

	@Override
	public long size() {
		return 100;
	}

	@Override
	public IModel<Contact> model(Contact object) {
		return new DetachableContactModel(object);
	}

	@Override
	public ContactFilter getFilterState() {
		return contactFilter;
	}

	@Override
	public void setFilterState(ContactFilter state) {
		contactFilter = state;
	}
}