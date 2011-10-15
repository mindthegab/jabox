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
package org.jabox.webapp.pages.server;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.apache.wicket.persistence.provider.ServerXstreamDao;
import org.jabox.apis.Connector;
import org.jabox.model.DefaultConfiguration;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Server;

public class CreateServerLink extends Link<Void> {
	private static final long serialVersionUID = -6076134805074401259L;

	private Class<? extends Connector> _class1;

	public CreateServerLink(final String id) {
		super(id);
		_class1 = Connector.class;
	}

	public CreateServerLink(final String id,
			final Class<? extends Connector> class1) {
		this(id);
		_class1 = class1;
	}

	@Override
	public void onClick() {
		IModel<Server> model = new Model<Server>(new Server());
		setResponsePage(new EditServerPage(model, _class1) {

			@Override
			protected void onCancel() {
				setResponsePage(ManageServers.class);
			}

			@Override
			protected void onSave(final Server article) {
				ServerXstreamDao.persist(article.getDeployerConfig());

				// If this is the first Service of the kind, set it as default
				DefaultConfiguration dc = ConfigXstreamDao.getConfig();
				DeployerConfig config = article.getDeployerConfig();
				if (dc.getDefault(config) == null) {
					dc.switchDefault(config);
				}
				ConfigXstreamDao.persist(dc);

				setResponsePage(ManageServers.class);
			}
		});
	}
}
