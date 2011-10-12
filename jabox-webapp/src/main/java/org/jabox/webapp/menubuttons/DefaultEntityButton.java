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
package org.jabox.webapp.menubuttons;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.jabox.apis.ConnectorConfig;
import org.jabox.model.DefaultConfiguration;
import org.jabox.webapp.modifiers.TooltipModifier;

public final class DefaultEntityButton<T extends ConnectorConfig> extends
		ImageButton {

	private static final TooltipModifier TOOLTIP_MODIFIER = new TooltipModifier(
			"Set as Default");

	private static final ResourceReference DEFAULT_IMG = new SharedResourceReference(
			DefaultEntityButton.class, "favorite.png");

	private static final ResourceReference NO_DEFAULT_IMG = new SharedResourceReference(
			DefaultEntityButton.class, "favorite-bw.png");

	private static final long serialVersionUID = 1L;
	private final T _item;

	public DefaultEntityButton(final String id, final T item,
			final Class<? extends Page> responsePage) {
		super(id, DEFAULT_IMG);
		_item = item;
		add(TOOLTIP_MODIFIER);
		if (isDefault(item)) {
			setImageResourceReference(DEFAULT_IMG);
		} else {
			setImageResourceReference(NO_DEFAULT_IMG);
		}
	}

	private boolean isDefault(final ConnectorConfig item) {
		DefaultConfiguration dc = ConfigXstreamDao.getConfig();
		if (DefaultConfiguration.TRUE.equals(dc.isDefault(item))) {
			return true;
		}
		return false;
	}

	public DefaultEntityButton(final String id, final ListItem<T> item,
			final Class<? extends Page> responsePage) {
		this(id, item.getModelObject(), responsePage);
	}

	/**
	 * Delete from persistent storage, commit transaction.
	 */
	@Override
	public void onSubmit() {
		DefaultConfiguration dc = ConfigXstreamDao.getConfig();
		dc.switchDefault(_item);
		ConfigXstreamDao.persist(dc);
	}
}
