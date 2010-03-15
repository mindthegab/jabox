package org.jabox.webapp.panels;

import org.jabox.apis.Connector;

public class PluginTab extends Tab {

	private static final long serialVersionUID = 2961421967975301502L;

	public PluginTab(Connector ci, String url, boolean b) {
		super(getTitle(ci), url, b);
	}

	private static String getTitle(Connector ci) {
		return ci.getName();
	}
}
