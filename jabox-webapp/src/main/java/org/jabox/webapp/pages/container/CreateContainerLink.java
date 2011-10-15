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

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.ContainerXstreamDao;
import org.jabox.model.Container;
import org.jabox.webapp.pages.user.ManageUsers;

public class CreateContainerLink extends Link<Void> {
	private static final long serialVersionUID = -6076134805074401259L;

	public CreateContainerLink(final String id) {
		super(id);
	}

	@Override
	public void onClick() {
		IModel<Container> model = new Model<Container>(new Container());
		setResponsePage(new EditContainerPage(model) {

			protected void onCancel() {
				setResponsePage(ManageUsers.class);
			}

			protected void onSave(final Container container) {
				ContainerXstreamDao.persist(container);
				setResponsePage(ManageContainers.class);
			}
		});
	}
}
