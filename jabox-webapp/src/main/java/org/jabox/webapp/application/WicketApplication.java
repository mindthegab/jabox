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
import org.apache.wicket.protocol.http.SecondLevelCacheSessionStore;
import org.apache.wicket.protocol.http.pagestore.DiskPageStore;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.lang.PackageName;
import org.jabox.applicationcontext.InitializeDatabase;
import org.jabox.its.bugzilla.BugzillaModule;
import org.jabox.its.jtrac.JtracModule;
import org.jabox.modules.JaboxWebappModule;
import org.jabox.persistence.modules.JaboxPersistenceModule;
import org.jabox.webapp.pages.HomePage;
import org.jabox.webapp.pages.JaboxAuthenticatedWebApplication;
import org.jabox.webapp.pages.ManageServers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
@Component
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
		mount("web", PackageName.forClass(HomePage.class));
	}

	@Override
	protected ISessionStore newSessionStore() {
		return new SecondLevelCacheSessionStore(this, new DiskPageStore()) {

			@Override
			protected void onUnbind(final String sessionId) {
				// this code is called when wicket call httpSession.invalidate()
				System.out.println("Session unbinded");
			}

		};
	}

	@Override
	public void init() {
		super.init();
		springInjection();
		guiceInjection();
		new InitializeDatabase().init();
	}

	private void guiceInjection() {
		addComponentInstantiationListener(new GuiceComponentInjector(this,
				getGuiceInjector()));
		// new BugzillaModule(), new JaboxPersistenceModule(),
		// new JaboxWebappModule(), new JtracModule()));
	}

	private Injector getGuiceInjector() {
		ServiceLoader<com.google.inject.Module> modules = ServiceLoader
				.load(com.google.inject.Module.class);
		return Guice.createInjector(modules);
	}

	protected void springInjection() {
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		LOGGER.debug("Locale: " + Locale.getDefault());
		return ManageServers.class;
	}

}
