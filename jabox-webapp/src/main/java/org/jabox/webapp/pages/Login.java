package org.jabox.webapp.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.jabox.webapp.borders.MiddlePanel;

/**
 * @author Administrator
 */
public final class Login extends MiddlePanel {

	/**
	 * Constructor
	 */
	public Login() {
		add(new SignInPanel("signInPanel"));
	}

	/**
	 * Constructor
	 * 
	 * @param parameters
	 *            Parameters to page
	 */
	public Login(final PageParameters parameters) {
		add(new SignInPanel("signInPanel"));
	}
}