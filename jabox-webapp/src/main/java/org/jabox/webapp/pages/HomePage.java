package org.jabox.webapp.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.jabox.environment.Environment;
import org.jabox.webapp.borders.NavomaticBorder;

/**
 * Homepage
 */
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters) {
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);
		System.out.println("user.home: " + Environment.getBaseDir());
		navomaticBorder.add(new BookmarkablePageLink<WebPage>("login",
				ManageConfiguration.class, parameters));
	}
}
