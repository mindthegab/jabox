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
package org.jabox.webapp.pages.tabs;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.jabox.apis.Manager;
import org.jabox.model.DefaultConfiguration;
import org.jabox.webapp.pages.BasePage;
import org.jabox.webapp.panels.HeaderLinksPanel;

import com.google.inject.Inject;

/**
 * {@link CqmPage} is showing the current C.Q.M. inside an <code>iframe</code>.
 * TopMenu is visible in order to navigate from one server to another easily.
 */
public class CqmPage extends BasePage {

	public CqmPage() {
		final DefaultConfiguration dc = ConfigXstreamDao.getConfig();
		String url = dc.getCqm().getServer().getUrl();
		WebMarkupContainer wmc = new WebMarkupContainer("iframe");
		wmc.add(new AttributeModifier("src", new Model<String>(url)));
		add(wmc);

		add(new HeaderLinksPanel("headerLinks", HeaderLinksPanel.CQM));
	}
}
