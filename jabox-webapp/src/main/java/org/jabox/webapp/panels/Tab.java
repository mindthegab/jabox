package org.jabox.webapp.panels;

import java.io.Serializable;

public class Tab implements Serializable {
	private static final long serialVersionUID = 4781646077887785325L;

	private String _title;
	private String _url;
	private String _tooltip;

	private boolean _selected;

	public Tab(String title, String url) {
		_title = title;
		_url = url;
	}

	public Tab(String title, String url, boolean selected) {
		_title = title;
		_url = url;
		setSelected(selected);
	}

	public Tab(String title, String url, String tooltip, boolean selected) {
		_title = title;
		_url = url;
		_tooltip = tooltip;
		setSelected(selected);
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getUrl() {
		return _url;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getTitle() {
		return _title;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setTooltip(String tooltip) {
		_tooltip = tooltip;
	}

	public String getTooltip() {
		return _tooltip;
	}
}
