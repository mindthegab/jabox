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
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.ConnectorConfig;
import org.jabox.model.Server;
import org.jabox.webapp.pages.DeleteEntityButton;
import org.jabox.webapp.pages.EditServerLink;
import org.jabox.webapp.pages.ManageServers;

public class SCMConnectorList extends PropertyListView<ConnectorConfig> {
	private static final long serialVersionUID = -2877438240039632971L;

	public SCMConnectorList(String id, List<? extends ConnectorConfig> projects) {
		super(id, projects);
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public void populateItem(final ListItem<ConnectorConfig> listItem) {
		final ConnectorConfig deployerConfig = listItem.getModelObject();
		listItem.add(new Label("clazz", deployerConfig.getServer().getName()));
		listItem.add(new Label("scmUrl", deployerConfig.getPluginId()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<ConnectorConfig>(listItem));
		listItem.add(attributeModifier);
		listItem.add(new EditServerLink("edit",
				new CompoundPropertyModel<Server>(listItem.getModelObject()
						.getServer())));
		listItem.add(new DeleteEntityButton<Server>("delete", listItem
				.getModelObject().getServer(), ManageServers.class));
	}
}
