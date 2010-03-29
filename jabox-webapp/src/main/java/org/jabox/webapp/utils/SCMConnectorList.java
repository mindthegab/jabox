/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.webapp.utils;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.Manager;
import org.jabox.model.Server;
import org.jabox.webapp.menubuttons.DefaultEntityButton;
import org.jabox.webapp.menubuttons.DeleteEntityButton;
import org.jabox.webapp.menubuttons.EditEntityButton;
import org.jabox.webapp.menubuttons.IconButton;
import org.jabox.webapp.pages.CreateServerLink;
import org.jabox.webapp.pages.ManageServers;

public class SCMConnectorList extends PropertyListView<ConnectorConfig> {
	private static final long serialVersionUID = -2877438240039632971L;

	public SCMConnectorList(final String id,
			final List<? extends ConnectorConfig> projects) {
		super(id, projects);
		add(new CreateServerLink("create" + id));
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	@Override
	public void populateItem(final ListItem<ConnectorConfig> item) {
		final ConnectorConfig deployerConfig = item.getModelObject();
		item.add(new Label("clazz", deployerConfig.getServer().getName()));
		item.add(new ExternalLink("server.url", deployerConfig.getServer()
				.getUrl(), deployerConfig.getServer().getUrl()));
		item.add(new DefaultEntityButton<ConnectorConfig>("default", item,
				ManageServers.class));
		item.add(new IconButton("connectorImage", deployerConfig));

		Connector ci = _manager.getConnectorInstance(deployerConfig);
		// item.add(new Label("scmUrl", ci.getName()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<ConnectorConfig>(item));
		item.add(attributeModifier);
		item.add(new EditEntityButton<Server>("edit", item.getModelObject()
				.getServer()));
		item.add(new DeleteEntityButton<Server>("delete", item.getModelObject()
				.getServer(), ManageServers.class));
	}
}
