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
package org.jabox.webapp.panels;

import java.io.Serializable;

public class Tab implements Serializable {
	private static final long serialVersionUID = 4781646077887785325L;

	private String _title;
	private String _url;
	private String _tooltip;

	private boolean _selected;

	public Tab(final String title, final String url) {
		_title = title;
		_url = url;
	}

	public Tab(final String title, final String url, final boolean selected) {
		_title = title;
		_url = url;
		setSelected(selected);
	}

	public Tab(final String title, final String url, final String tooltip,
			final boolean selected) {
		_title = title;
		_url = url;
		_tooltip = tooltip;
		setSelected(selected);
	}

	public void setUrl(final String url) {
		_url = url;
	}

	public String getUrl() {
		return _url;
	}

	public void setTitle(final String title) {
		_title = title;
	}

	public String getTitle() {
		return _title;
	}

	public void setSelected(final boolean selected) {
		_selected = selected;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setTooltip(final String tooltip) {
		_tooltip = tooltip;
	}

	public String getTooltip() {
		return _tooltip;
	}
}
