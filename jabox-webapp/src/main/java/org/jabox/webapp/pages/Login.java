package org.jabox.webapp.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.jabox.webapp.borders.NavomaticBorder;

/**
 * @author Administrator
 */
public final class Login extends BasePage {

	/**
	 * Constructor
	 */
	public Login() {
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);
		navomaticBorder.add(new SignInPanel("signInPanel"));
	}

	/**
	 * Constructor
	 * 
	 * @param parameters
	 *            Parameters to page
	 */
	public Login(final PageParameters parameters) {
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);
		navomaticBorder.add(new SignInPanel("signInPanel"));
	}
}