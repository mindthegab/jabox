package org.jabox.webapp.pages;

import org.apache.wicket.authentication.pages.SignOutPage;

/**
 * 
 * @author Administrator
 * 
 */
public class Logout extends SignOutPage {
	public Logout() {
		setResponsePage(HomePage.class);
	}
}