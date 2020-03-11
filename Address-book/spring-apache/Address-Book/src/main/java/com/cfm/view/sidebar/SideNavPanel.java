package com.cfm.view.sidebar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.AjaxDownloadBehavior;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.time.Duration;

import com.cfm.model.Contact;
import com.cfm.service.IContactService;
import com.cfm.utility.ContactHelperUtil;
import com.cfm.view.dialog.contact.ContactDialog;
import com.cfm.view.dialog.upload.UploadDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;

public class SideNavPanel extends Panel {

	private static final long serialVersionUID = -6962185281915749544L;

	@SpringBean
	private IContactService contactService;

	@SuppressWarnings("serial")
	public SideNavPanel(String id) {
		super(id);

		final ContactDialog dialog = new ContactDialog("addDialog", "User details") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, DialogButton button) {
				Contact contact = this.getModelObject();
				contactService.saveContact(contact);
				setResponsePage(getPage().getClass());
			}
		};

		add(dialog);

		add(new AjaxLink<Void>("showModal") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dialog.setTitle(target, "Create New Contact");
				dialog.setModelObject(new Contact());
				dialog.open(target);
			}
		});

		final UploadDialog uploadDialog = new UploadDialog("uploadDialog", "Upload file") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, DialogButton button) {
				super.onSubmit(target, button);
				setResponsePage(getPage().getClass());
			}
		};

		this.add(dialog);

		this.add(uploadDialog);

		add(new AjaxLink<Void>("upload") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				uploadDialog.open(target);
			}
		});

		IResource resource = new FileDownloadService(contactService.getAll())
				.setContentDisposition(ContentDisposition.ATTACHMENT);

		final AjaxDownloadBehavior download = new AjaxDownloadBehavior(resource);
        download.setLocation(AjaxDownloadBehavior.Location.SameWindow);
		add(download);
		add(new AjaxLink<Void>("download") {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(AjaxRequestTarget target) {
				download.initiate(target);
			}
		});

	}

	private class FileDownloadService extends ResourceStreamResource {
		private static final long serialVersionUID = 1L;

		private List<Contact> contacts;

		public FileDownloadService(List<Contact> contacts) {
			this.contacts = contacts;
			setFileName("download.csv");
			setCacheDuration(Duration.NONE);
		}

		@Override
		protected IResourceStream getResourceStream(Attributes attributes) {
			File file = new File("download.csv");

			BufferedWriter writer = null;
			try {
				writer = Files.newBufferedWriter(Paths.get(file.getName()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				writer.write("First Name, Last Name, Date Of Birth, Home Phone, Cell Phone, Email"
						+ System.getProperty("line.separator"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (Contact s : contacts) {
				try {
					writer.write(
							ContactHelperUtil.getCSVRepresentationOfContact(s) + System.getProperty("line.separator"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return new FileResourceStream(new org.apache.wicket.util.file.File(file));
		};

	}

}
