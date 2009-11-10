/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
	public JaboxAuthenticatedWebSession(Request request) {
		super(request);
	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#authenticate(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean authenticate(final String username, final String password) {
		// Check username and password
		boolean authenticated = username.equals("wicket")
				&& password.equals("wicket");
		if (authenticated) {
			_user = new User();
			_user.setLogin("wicket");
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

	public void setUser(User user) {
		_user = user;
	}

	public User getUser() {
		return _user;
	}
}
