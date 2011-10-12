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

import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.jabox.apis.ConnectorConfig;

/**
 * Returns an Icon that represents the {@link ConnectorConfig} that is passed.
 * <p/>
 * The Icon should be located at the same path with the {@link ConnectorConfig}
 * with the name "icon.png".
 * <p/>
 * If the Icon does not exist, a default icon is returned.
 * 
 * @author Administrator
 * 
 */
public final class IconButton extends ImageButton {

	private static final String ICON_PNG = "icon.png";

	private static final long serialVersionUID = 1L;

	private static final ResourceReference PLUGIN = new SharedResourceReference(
			IconButton.class, "plugin.png");

	public IconButton(final String id, final ConnectorConfig item) {
		super(id, PLUGIN);
		ResourceReference rr = new SharedResourceReference(item.getClass(), ICON_PNG);

		if (existImage(rr)) {
			setImageResourceReference(rr);
		} else {
			setImageResourceReference(PLUGIN);
		}
	}

	private boolean existImage(final ResourceReference rr) {
		return rr.getScope().getResource(ICON_PNG) != null;
	}
}
