package org.jabox.webapp.borders;

import org.apache.wicket.markup.html.border.Border;
import org.jabox.apis.IManager;
import org.jabox.webapp.panels.HeaderLinksPanel;

import com.google.inject.Inject;

public class MySiteBorder extends Border {
	private static final long serialVersionUID = -6762014978866138140L;
	private HeaderLinksPanel headers;

	@Inject
	public MySiteBorder(IManager manager) {
		super("border");
		headers = new HeaderLinksPanel();
		addToBorder(headers);
	}
}
