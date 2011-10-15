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

import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.apache.wicket.persistence.provider.ContainerXstreamDao;
import org.jabox.model.Container;
import org.jabox.webapp.menubuttons.InfoImage;
import org.jabox.webapp.pages.BasePage;
import org.jabox.webapp.utils.ContainerList;

/**
 * Homepage
 */
@AuthorizeInstantiation("ADMIN")
public class ManageContainers extends BasePage {

	private static final long serialVersionUID = 1L;

	public ManageContainers() {
		final List<Container> containers = ContainerXstreamDao.getContainers();
		add(new InfoImage("containerIcon", ""));

		Form<BaseEntity> form = new Form<BaseEntity>("deleteForm");
		form.add(new ContainerList("containers", containers));
		form.add(new CreateContainerLink("createContainer"));
		add(form);
	}
}
