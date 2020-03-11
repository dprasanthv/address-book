package com.cfm.view.dialog.contact;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.cfm.model.Contact;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.JQueryIcon;
import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.dialog.AbstractFormDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;

public abstract class ContactDialog extends AbstractFormDialog<Contact> {
	private static final long serialVersionUID = 1L;
	protected final DialogButton btnSubmit = new DialogButton(SUBMIT, "Save", JQueryIcon.CHECK);
	protected final DialogButton btnCancel = new DialogButton(CANCEL, LBL_CANCEL, JQueryIcon.CANCEL);

	private Form<?> form;

	private FeedbackPanel feedback;

	public ContactDialog(String id, String title) {
		super(id, title, true);

		this.form = new Form<Contact>("form", new CompoundPropertyModel<Contact>(this.getModel()));
		this.add(this.form);

		this.form.add(new RequiredTextField<String>("firstName").add(StringValidator.maximumLength(30)));
		this.form.add(new EmailTextField("email").setRequired(true));
		this.form.add(new RequiredTextField<String>("lastName").add(StringValidator.maximumLength(30)));
		this.form.add(new TextField<String>("cellPhone").add(new PatternValidator("^\\d{10}+$")));
		this.form.add(
				new DatePicker("dateOfBirth", "MM.dd.yyyy", new Options("dateFormat", "'dd.mm.yy'")).setRequired(true));
		this.form.add(new TextField<String>("homePhone").add(new PatternValidator("^\\d{10}+$")));
		this.form.add(new TextField<String>("mailingAddress.streetAddress"));
		this.form.add(new TextField<String>("mailingAddress.city"));
		this.form.add(new TextField<String>("mailingAddress.state"));
		this.form.add(new TextField<String>("mailingAddress.country"));
		this.form.add(new TextField<String>("mailingAddress.zipCode").add(StringValidator.maximumLength(10)));
		this.form.add(new TextField<String>("permanentAddress.streetAddress"));
		this.form.add(new TextField<String>("permanentAddress.city"));
		this.form.add(new TextField<String>("permanentAddress.state"));
		this.form.add(new TextField<String>("permanentAddress.country"));
		this.form.add(new TextField<String>("permanentAddress.zipCode").add(StringValidator.maximumLength(10)));
		this.feedback = new JQueryFeedbackPanel("feedback");
		this.form.add(this.feedback);
	}

	@Override
	protected IModel<?> initModel() {
		return new Model<Contact>();
	}

	@Override
	protected List<DialogButton> getButtons() {
		return Arrays.asList(this.btnSubmit, this.btnCancel);
	}

	@Override
	public DialogButton getSubmitButton() {
		return this.btnSubmit;
	}

	@Override
	public Form<?> getForm() {
		return this.form;
	}

	@Override
	protected void onOpen(IPartialPageRequestHandler handler) {
		handler.add(this.form);
	}

	@Override
	public void onError(AjaxRequestTarget target, DialogButton button) {
		target.add(this.feedback);
	}
}
