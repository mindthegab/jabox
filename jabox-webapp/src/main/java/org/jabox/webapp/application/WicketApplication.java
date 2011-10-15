/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.webapp.application;

import java.util.Locale;
import java.util.ServiceLoader;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.mapper.PackageMapper;
import org.apache.wicket.util.lang.PackageName;
import org.jabox.applicationcontext.InitializeDatabase;
import org.jabox.webapp.pages.HomePage;
import org.jabox.webapp.pages.JaboxAuthenticatedWebApplication;
import org.jabox.webapp.pages.ManageServers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends JaboxAuthenticatedWebApplication {

	/**
	 * Application logger instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WicketApplication.class);

	/**
	 * Constructor
	 */
	public WicketApplication() {
		IRequestMapper mapper = new PackageMapper(PackageName
				.forClass(HomePage.class));
		// mount("web", PackageName.forClass(HomePage.class));
		mount(mapper);
	}

	@Override
	public void init() {
		super.init();
		guiceInjection();
		new InitializeDatabase().init();
	}

	private void guiceInjection() {
		Injector inj = Guice.createInjector(ServiceLoader.load(Module.class));
		GuiceComponentInjector listener = new GuiceComponentInjector(this, inj);
		getComponentInstantiationListeners().add(listener);
	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		LOGGER.debug("Locale: " + Locale.getDefault());
		return HomePage.class;
	}
}
