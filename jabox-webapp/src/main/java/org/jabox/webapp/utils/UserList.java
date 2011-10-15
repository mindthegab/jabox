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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.jabox.model.User;
import org.jabox.webapp.menubuttons.DeleteEntityButton;
import org.jabox.webapp.pages.EditEntityButton;
import org.jabox.webapp.pages.project.ManageProjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserList extends PropertyListView<User> {
	private static final Logger LOGGER = LoggerFactory
          .getLogger(UserList.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877438240039632971L;

	public UserList(final String id, final List<User> projects) {
		super(id, projects);
	}

	@Override
	public void populateItem(final ListItem<User> listItem) {
		final User user = listItem.getModelObject();
		LOGGER.debug("Populate User: " + user.getLogin());

		listItem.add(new Label("login", user.getLogin()));
		listItem.add(new Label("email", user.getEmail()));
		listItem.add(new Label("firstName", user.getFirstName()));
		listItem.add(new Label("lastName", user.getLastName()));
		listItem.add(new EditEntityButton<User>("edit", listItem
				.getModelObject()));
		listItem.add(new DeleteEntityButton<User>("delete", listItem,
				ManageProjects.class));
	}
}
