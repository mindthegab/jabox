package org.jabox.webapp.borders;

import org.apache.wicket.markup.html.border.Border;
import org.jabox.webapp.panels.HeaderLinksPanel;

public class MySiteBorder extends Border {
	private static final long serialVersionUID = -6762014978866138140L;
	private HeaderLinksPanel headers;

	public MySiteBorder() {
		super("border");
		headers = new HeaderLinksPanel();
		addToBorder(headers);
	}
}
