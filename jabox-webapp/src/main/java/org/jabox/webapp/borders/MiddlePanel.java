package org.jabox.webapp.borders;

import org.apache.wicket.markup.html.panel.Panel;
/**
 * Panel representing the content panel for the first tab
 * 
 * @author Igor Vaynberg (ivaynberg)
 * 
 */
public class MiddlePanel  extends Panel {
	private static final long serialVersionUID = -2139796431114958720L;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 */
	public MiddlePanel(String id) {
		super(id);
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);
	}
};
