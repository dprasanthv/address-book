package com.cfm.view.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.utility.ContactHelperUtil;
import com.cfm.view.main.actions.ActionPanel;

public class MainPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6059282248291679592L;

	@SpringBean
	private IContactService contactService;

	private String searchText;

	private Form<String> form;

	private TextField<String> searchField;

	ListDataProvider<Contact> listDataProvider;
	List<Contact> contacts;

	DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");

	public MainPanel(String id) {
		super(id);
		contacts = contactService.getAll();
		WebMarkupContainer myContainer = new WebMarkupContainer("myContainer");

		listDataProvider = new ListDataProvider<Contact>(contacts) {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Contact> getData() {
				return contacts;
			}
		};
		DataView<Contact> dataView = new DataView<Contact>("rows", listDataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Contact> item) {
				Contact contact = item.getModelObject();
				RepeatingView repeatingView = new RepeatingView("dataRow");

				repeatingView.add(new Label(repeatingView.newChildId(), contact.getFirstName()));
				repeatingView.add(new Label(repeatingView.newChildId(), contact.getLastName()));
				repeatingView.add(new Label(repeatingView.newChildId(), dateFormat.format(contact.getDateOfBirth())));
				repeatingView.add(new Label(repeatingView.newChildId(), contact.getEmail()));
				repeatingView.add(new Label(repeatingView.newChildId(), ContactHelperUtil.getPhoneNumber(contact)));
				repeatingView.add(new ActionPanel("actions", item.getModel()));
				item.add(repeatingView);
			}
		};
		Label labl = new Label("noData", "No Contacts are available.");
		myContainer.add(labl);
		if (contacts.size() == 0) {
			labl.setVisible(true);
			dataView.setVisible(false);
		} else {
			labl.setVisible(false);
			dataView.setVisible(true);
		}
		dataView.setOutputMarkupId(true);
		myContainer.setOutputMarkupId(true);

		myContainer.add(dataView);
		form = new Form<String>("form");
		searchField = new TextField<String>("searchText", new PropertyModel<String>(this, "searchText"));
		searchField.setOutputMarkupId(true);
		form.add(searchField);
		form.add(new AjaxButton("search") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				contacts = contactService.getContactsFilteredBySearch(searchText);
				target.add(myContainer);
			}
		});
		AjaxButton cancel = new AjaxButton("reset") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				searchText = null;
				contacts = contactService.getAll();
				target.add(myContainer);
			}
		};
		cancel.setOutputMarkupId(true);
		form.add(cancel);
		form.setOutputMarkupId(true);
		myContainer.add(form);
		add(myContainer);
	}

}
