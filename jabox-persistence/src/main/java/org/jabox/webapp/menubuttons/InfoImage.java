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

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.jabox.webapp.modifiers.TooltipModifier;

/**
 * Info icon that shows a tooltip when mouse is hovered from above.
 * 
 * @author Dimitris Kapanidis
 */
public final class InfoImage extends Image {

	private static final ResourceReference DEFAULT_IMG = new SharedResourceReference(
			InfoImage.class, "info.png");

	private static final long serialVersionUID = 1L;

	public InfoImage(final String id, final String tooltip) {
		super(id);
		add(new TooltipModifier(tooltip));
		setImageResourceReference(DEFAULT_IMG);
	}
}
