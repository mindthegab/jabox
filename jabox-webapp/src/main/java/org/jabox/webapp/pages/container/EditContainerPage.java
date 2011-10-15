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
package org.jabox.webapp.pages.container;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Container;
import org.jabox.webapp.pages.BasePage;

public abstract class EditContainerPage extends BasePage {

	public EditContainerPage(final IModel<Container> user) {
		add(new FeedbackPanel("feedback"));
		Form<Container> form = new Form<Container>("form",
				new CompoundPropertyModel<Container>(user.getObject())) {
			private static final long serialVersionUID = -8262391690702864764L;

			@Override
			protected void onSubmit() {
				onSave(getModelObject());
			}
		};

		add(form);

		form.add(new RequiredTextField<Container>("name"));
		form.add(new RequiredTextField<Container>("port"));
		form.add(new RequiredTextField<Container>("rmiPort"));
		form.add(new RequiredTextField<Container>("ajpPort"));
		form.add(new RequiredTextField<Container>("jvmArgs"));

		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = -6975617962156076540L;

			@Override
			public void onClick() {
				onCancel();
			}
		});
	}

	protected abstract void onSave(Container container);

	protected abstract void onCancel();
}
