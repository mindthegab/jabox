package org.jabox.webapp.borders;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class NavomaticBorder extends Border {

	private static final long serialVersionUID = 3571282449732806300L;

	public NavomaticBorder(final String componentName) {
		super(componentName);
		List tabs = new ArrayList();
		tabs.add(new AbstractTab(new Model<String>("A.L.M. (Jabox)")) {
			public Panel getPanel(String panelId) {
				return new TabPanel1(panelId);
			}
		});
		tabs.add(new AbstractTab(new Model("R.M.S. (Nexus)")) {
			public Panel getPanel(String panelId) {
				return new TabPanel2(panelId);
			}
		});
		tabs.add(new AbstractTab(new Model("C.I.S. (Hudson)")) {
			public Panel getPanel(String panelId) {
				return new TabPanel3(panelId);
			}
		});
		add(new TabbedPanel("tabs", tabs));
	}

	/**
	 * Panel representing the content panel for the first tab
	 * 
	 * @author Igor Vaynberg (ivaynberg)
	 * 
	 */
	private static class TabPanel1 extends Panel {

		/**
		 * Constructor
		 * 
		 * @param id
		 *            component id
		 */
		public TabPanel1(String id) {
			super(id);
		}

	};

	/**
	 * Panel representing the content panel for the second tab
	 * 
	 * @author Igor Vaynberg (ivaynberg)
	 * 
	 */
	private static class TabPanel2 extends Panel {

		/**
		 * Constructor
		 * 
		 * @param id
		 *            component id
		 */
		public TabPanel2(String id) {
			super(id);
		}

	};

	/**
	 * Panel representing the content panel for the third tab
	 * 
	 * @author Igor Vaynberg (ivaynberg)
	 * 
	 */
	private static class TabPanel3 extends Panel {

		/**
		 * Constructor
		 * 
		 * @param id
		 *            component id
		 */
		public TabPanel3(String id) {
			super(id);
		}

	};

}