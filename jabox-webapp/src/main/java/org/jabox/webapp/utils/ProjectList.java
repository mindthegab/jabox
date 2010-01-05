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
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.Project;
import org.jabox.webapp.pages.DeleteEntityButton;
import org.jabox.webapp.pages.ManageProjects;

public class ProjectList extends PropertyListView<Project> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877438240039632971L;

	public ProjectList(String id, List<Project> projects) {
		super(id, projects);
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public void populateItem(final ListItem<Project> listItem) {
		final Project project = (Project) listItem.getModelObject();
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
