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
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.jabox.model.Project;
import org.jabox.webapp.menubuttons.DeleteEntityButton;
import org.jabox.webapp.pages.project.ManageProjects;

public class ProjectList extends PropertyListView<Project> {
	private static final long serialVersionUID = -2877438240039632971L;

	public ProjectList(final String id, final List<Project> projects) {
		super(id, projects);
	}

	@Override
	public void populateItem(final ListItem<Project> listItem) {
		final Project project = listItem.getModelObject();
		listItem.add(new Label("name", project.getName()));
		listItem.add(new Label("mavenArchetype", project.getMavenArchetype()
				.toString()));
		listItem
				.add(new MultiLineLabel("description", project.getDescription()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<Project>(listItem));
		listItem.add(attributeModifier);
		listItem.add(new DeleteEntityButton<Project>("delete", listItem,
				ManageProjects.class));
	}
}
