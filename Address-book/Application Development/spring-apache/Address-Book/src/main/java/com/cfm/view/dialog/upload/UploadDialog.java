package com.cfm.view.dialog.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.utility.ContactHelperUtil;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.dialog.AbstractFormDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;

public abstract class UploadDialog extends AbstractFormDialog<FileUpload> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private IContactService contactService;

	protected final DialogButton btnUpload = new DialogButton(SUBMIT, Model.of("Upload!")) {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isIndicating() {
			return true;
		}
	};

	protected final DialogButton btnCancel = new DialogButton(CANCEL, LBL_CANCEL);

	private Form<?> form;
	private FeedbackPanel feedback;
	private FileUploadField field;

	public UploadDialog(String id, String title) {
		super(id, title, new Model<FileUpload>(), true);

		this.form = new Form<Integer>("form");
		this.add(this.form);

		this.field = new FileUploadField("file");
		this.form.add(this.field);

		this.feedback = new JQueryFeedbackPanel("feedback");
		this.form.add(this.feedback);
	}

	@Override
	protected List<DialogButton> getButtons() {
		return Arrays.asList(this.btnUpload, this.btnCancel);
	}

	@Override
	public DialogButton getSubmitButton() {
		return this.btnUpload;
	}

	@Override
	public Form<?> getForm() {
		return this.form;
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, DialogButton button) {
		final FileUpload uploadedFile = this.field.getFileUpload();
		if (uploadedFile != null) {

			File newFile = new File(uploadedFile.getClientFileName());

			if (newFile.exists()) {
				newFile.delete();
			}

			try {
				newFile.createNewFile();
				uploadedFile.writeTo(newFile);
				String line = "";
				List<Contact> contacts = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(new FileReader(newFile))) {
					boolean header = false;
					while ((line = br.readLine()) != null) {
						if (header) {
							String[] contact = line.split(",");
							contacts.add(ContactHelperUtil.getContactFromString(contact));
						} else {
							header = true;
						}
					}
					contactService.saveBulkContacts(contacts);
					newFile.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				throw new IllegalStateException("Error" + e);
			}
		}
	}

	@Override
	protected void onOpen(IPartialPageRequestHandler handler) {
		handler.add(this.feedback);
	}

	@Override
	public void onError(AjaxRequestTarget target, DialogButton button) {
		target.add(this.feedback);
	}
}
