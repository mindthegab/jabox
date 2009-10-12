package org.jabox.webapp.pages;

import org.apache.wicket.authentication.pages.SignOutPage;
import org.jabox.webapp.borders.NavomaticBorder;

/**
 * 
 * @author Administrator
 * 
 */
public class Logout extends SignOutPage {
	public Logout() {
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);
	}
}