package com.cfm.view.home;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.resource.CssResourceReference;

import com.cfm.view.main.MainPanel;
import com.cfm.view.sidebar.SideNavPanel;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

@WicketHomePage
public class Home extends WebPage {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Component sideNavPanel;
	private Component mainPanel;

	public Home() {
		initGui();
	}

	private void initGui() {
		add(new Label("title", "Address Book"));
		add(sideNavPanel = new SideNavPanel("sideNavPanel"));
		add(mainPanel = new MainPanel("mainPanel"));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
		response.render(CssHeaderItem.forReference(new CssResourceReference(Home.class, "Styles.css")));
	}

	public Component getSideNavPanel() {
		return sideNavPanel;
	}

	public Component getMainPanel() {
		return mainPanel;
	}

}
