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

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.jabox.model.User;

/**
 * Authenticated session subclass
 */
public class JaboxAuthenticatedWebSession extends AuthenticatedWebSession {
	private static final long serialVersionUID = 1L;

	private User _user;

	/**
	 * Construct.
	 * 
	 * @param request
	 *            The current request object
	 */
	public JaboxAuthenticatedWebSession(final Request request) {
		super(request);
	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#authenticate(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean authenticate(final String username, final String password) {
		// Check username and password
		boolean authenticated = username.equals("admin")
				&& password.equals("admin");
		if (authenticated) {
			_user = new User();
			_user.setLogin("admin");
		}
		return authenticated;
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

	public void setUser(final User user) {
		_user = user;
	}

	public User getUser() {
		return _user;
	}
}
