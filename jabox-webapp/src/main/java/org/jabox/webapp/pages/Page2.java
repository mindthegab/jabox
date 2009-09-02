package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.WebPage;
import org.jabox.webapp.borders.NavomaticBorder;

/**
 * Homepage
 */
public class Page2 extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here
	public Page2() {
		add(new NavomaticBorder("navomaticBorder"));
	}
}
