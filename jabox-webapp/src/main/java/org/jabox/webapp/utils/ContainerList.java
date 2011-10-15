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
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.jabox.model.Container;
import org.jabox.webapp.menubuttons.DeleteEntityButton;
import org.jabox.webapp.menubuttons.StartContainerButton;
import org.jabox.webapp.pages.EditEntityButton;
import org.jabox.webapp.pages.container.ManageContainers;

public class ContainerList extends PropertyListView<Container> {

	private static final long serialVersionUID = -2877438240039632971L;

	public ContainerList(final String id, final List<Container> containers) {
		super(id, containers);
	}

	@Override
	public void populateItem(final ListItem<Container> listItem) {
		final Container container = listItem.getModelObject();
		listItem.add(new Label("name", container.getName()));
		listItem.add(new Label("port", container.getPort().toString()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<Container>(listItem));
		listItem.add(attributeModifier);
		listItem.add(new StartContainerButton("start", listItem,
				ManageContainers.class));
		listItem.add(new DeleteEntityButton<Container>("delete", listItem,
				ManageContainers.class));
	}
}
