package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.WebPage;
import org.jabox.webapp.borders.NavomaticBorder;

/**
 * Homepage
 */
public class MainMenu extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

	public MainMenu() {
		add(new NavomaticBorder("navomaticBorder"));
	}
}
