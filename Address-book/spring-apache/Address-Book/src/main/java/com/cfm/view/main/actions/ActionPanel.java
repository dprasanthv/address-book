package com.cfm.view.main.actions;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.view.dialog.contact.ContactDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButtons;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogIcon;
import com.googlecode.wicket.jquery.ui.widget.dialog.MessageDialog;

public class ActionPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private IContactService contactService;

	private Contact contactToBeDeleted;

	public ActionPanel(String id, IModel<Contact> model) {
		super(id, model);

		final ContactDialog updateDialog = new ContactDialog("updateDialog", "User details") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, DialogButton button) {
				Contact contact = this.getModelObject();
				contactService.updateContact(contact);
				setResponsePage(getPage().getClass());
			}

			@Override
			public void onClose(IPartialPageRequestHandler handler, DialogButton button) {
//					handler.add(form);
			}
		};

		add(updateDialog);

		final MessageDialog deleteDialog = new MessageDialog("deleteDialog", "Deleting Contact",
				"Are you sure you want to delete this contact?", DialogButtons.YES_NO, DialogIcon.WARN) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClose(IPartialPageRequestHandler handler, DialogButton button) {
				if (button.getName().equals("YES")) {
					contactService.deleteContactById(contactToBeDeleted.getId());
					contactToBeDeleted = null;
					setResponsePage(getPage().getClass());
				}
			}
		};

		add(deleteDialog);

		add(new AjaxLink<Void>("update") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Contact contact = (Contact) getParent().getDefaultModelObject();
				updateDialog.setTitle(target, "Update Existing Contact");
				updateDialog.setModelObject(contact); // Provides a new model object to the dialog
				updateDialog.open(target);
			}
		});
		add(new AjaxLink<Void>("delete") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				contactToBeDeleted = (Contact) getParent().getDefaultModelObject();
				deleteDialog.setDefaultModelObject(
						"Are you sure you want to delete " + contactToBeDeleted.getFirstName() + " " + contactToBeDeleted.getLastName() + " ?");
				deleteDialog.open(target);
			}
		});
	}
}
