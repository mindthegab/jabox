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
package org.jabox.webapp.pages;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.Server;
import org.jabox.webapp.modifiers.TooltipModifier;

public final class EditServerButton<T extends Server> extends ImageButton {

	private static final TooltipModifier TOOLTIP_MODIFIER = new TooltipModifier(
			"Edit Connector");

	private static final ResourceReference EDIT_IMG = new ResourceReference(
			EditEntityButton.class, "preferences-system.png");

	private static final long serialVersionUID = 1L;
	private final T _item;

	public EditServerButton(final String id, final T item) {
		super(id, EDIT_IMG);
		_item = item;
		add(TOOLTIP_MODIFIER);
	}

	public EditServerButton(final String id, final ListItem<T> item) {
		this(id, item.getModelObject());
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao _generalDao;

	@SpringBean
	private DeployersRegistry registry;

	/**
	 * Delete from persistent storage, commit transaction.
	 */
	@Override
	public void onSubmit() {
		IModel<T> model = new Model<T>(_item);

		Connector connector = registry.getEntry(model.getObject()
				.getDeployerConfig().pluginId);
		setResponsePage(new EditServerPage(new CompoundPropertyModel<Server>(
				model), connector.getClass()) {

			@Override
			protected void onCancel() {
				setResponsePage(ManageServers.class);
			}

			@Override
			protected void onSave(final Server server) {
				_generalDao.persist(server);
				setResponsePage(ManageServers.class);
			}
		});
	}
}
