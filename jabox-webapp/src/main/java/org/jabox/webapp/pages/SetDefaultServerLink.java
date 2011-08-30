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

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.jabox.apis.ConnectorConfig;
import org.jabox.model.DefaultConfiguration;

public class SetDefaultServerLink extends Link<ConnectorConfig> {
	private static final long serialVersionUID = -2146843131170907255L;

	public SetDefaultServerLink(final String id,
			final IModel<ConnectorConfig> model) {
		super(id, model);
	}

	@Override
	public void onClick() {
		DefaultConfiguration dc = ConfigXstreamDao.getConfig();
		dc.switchDefault(getModelObject());
		ConfigXstreamDao.persist(dc);
	}

}
