package com.cfm.view.config;

import org.springframework.stereotype.Component;

import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;

import de.agilecoders.wicket.core.Bootstrap;

@Component
public class WicketWebApplication extends WicketBootStandardWebApplication {
	@Override
	protected void init() {
		super.init();
		Bootstrap.install(this);
	}

}