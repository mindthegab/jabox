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
package org.jabox.webapp.pages;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.persistence.provider.UserXstreamDao;
import org.apache.wicket.request.Request;
import org.jabox.model.User;
import org.jabox.webapp.application.WicketApplication;

/**
 * Authenticated session subclass
 */
public class JaboxAuthenticatedWebSession extends AuthenticatedWebSession {
	private static final long serialVersionUID = 1L;

	private String _username;

	/**
	 * Construct.
	 * 
	 * @param request
	 *            The current request object
	 */
	public JaboxAuthenticatedWebSession(final Request request) {
		super(request);
		((GuiceInjectorHolder) ((WicketApplication) WicketApplication.get())
				.getMetaData(GuiceInjectorHolder.INJECTOR_KEY)).getInjector()
				.injectMembers(this);
		// InjectorHolder.getInjector().inject(this);
	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#authenticate(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean authenticate(final String username, final String password) {
		if (username == null || password == null) {
			return false;
		}

		User user = UserXstreamDao.getUser(username);

		if (user == null) {
			return false;
		}

		if (username.equals(user.getLogin())
				&& password.equals(user.getPassword())) {
			_username = user.getLogin();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#getRoles()
	 */
	@Override
	public Roles getRoles() {
		if (isSignedIn()) {
			// If the user is signed in, they have these roles
			return new Roles(Roles.ADMIN);
		}
		return null;
	}

	/**
	 * @return the username of the authenticated user.
	 */
	public String getUsername() {
		return _username;
	}
}
