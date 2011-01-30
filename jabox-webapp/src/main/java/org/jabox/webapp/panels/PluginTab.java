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
package org.jabox.webapp.panels;

import org.jabox.apis.Connector;
import org.jabox.webapp.pages.BasePage;

public class PluginTab extends Tab {

	private static final long serialVersionUID = 2961421967975301502L;

	public PluginTab(final Connector ci,
			final Class<? extends BasePage> pageClass, final boolean b) {
		super(getTitle(ci), pageClass, b);
	}

	private static String getTitle(final Connector ci) {
		return ci.getName();
	}
}
