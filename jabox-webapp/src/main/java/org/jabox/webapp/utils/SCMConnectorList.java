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
package org.jabox.webapp.utils;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.jabox.apis.ConnectorConfig;
import org.jabox.model.DefaultConfiguration;
import org.jabox.model.Server;
import org.jabox.webapp.menubuttons.DefaultEntityButton;
import org.jabox.webapp.menubuttons.DeleteEntityButton;
import org.jabox.webapp.menubuttons.IconButton;
import org.jabox.webapp.pages.server.CreateServerLink;
import org.jabox.webapp.pages.server.EditServerButton;
import org.jabox.webapp.pages.server.ManageServers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SCMConnectorList extends PropertyListView<ConnectorConfig> {
        private static final Logger LOGGER = LoggerFactory
          .getLogger(SCMConnectorList.class);

	private static final long serialVersionUID = -2877438240039632971L;

	public SCMConnectorList(final String id,
			final List<? extends ConnectorConfig> projects) {
		super(id, projects);
		add(new CreateServerLink("create" + id));
	}

	@Override
	public void populateItem(final ListItem<ConnectorConfig> item) {
		final ConnectorConfig deployerConfig = item.getModelObject();
                LOGGER.debug("Populate SCM Connector: " + deployerConfig.getServer().getName());
                
		item.add(new Label("clazz", deployerConfig.getServer().getName()));
		item.add(new ExternalLink("server.url", deployerConfig.getServer()
				.getUrl(), deployerConfig.getServer().getUrl()));
		item.add(new DefaultEntityButton<ConnectorConfig>("default", item,
				ManageServers.class));
		item.add(new IconButton("connectorImage", deployerConfig));

		// Connector ci = _manager.getConnectorInstance(deployerConfig);
		// item.add(new Label("scmUrl", ci.getName()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<ConnectorConfig>(item));
		item.add(attributeModifier);
		item.add(new EditServerButton<Server>("edit", item.getModelObject()
				.getServer()));
		item.add(new DeleteEntityButton<Server>("delete", item.getModelObject()
				.getServer(), ManageServers.class) {
			private static final long serialVersionUID = -8085737767377869654L;

			@Override
			public void onSubmit() {
				// If item is default, disable it first.
				DefaultConfiguration dc = ConfigXstreamDao.getConfig();
				if (DefaultConfiguration.TRUE.equals(dc.isDefault(item
						.getModelObject()))) {
					dc.switchDefault(item.getModelObject());
					ConfigXstreamDao.persist(dc);
				}
				super.onSubmit();
			}
		});
	}
}
