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

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.border.Border;
import org.jabox.webapp.borders.MySiteBorder;

public abstract class BasePage extends WebPage {

	private Border border;

	public BasePage add(final Component child) {
		// add(new StyleSheetReference("pageCSS", getClass(),
		// "css/wicket.css"));

		// Add children of the page to the page's border component
//		if (border == null) {
			// Create border and add it to the page
			border = new MySiteBorder();
			super.add(border);
//		}
		border.add(child);
		return this;
	}

	public MarkupContainer removeAll() {
		border.removeAll();
		return this;
	}

	public MarkupContainer replace(final Component child) {
		return border.replace(child);
	}

	// public boolean autoAdd(Component component, MarkupStream markupStream) {
	// return border.autoAdd(component, markupStream);
	// }

}
