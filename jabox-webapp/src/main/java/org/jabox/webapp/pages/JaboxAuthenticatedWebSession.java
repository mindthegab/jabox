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