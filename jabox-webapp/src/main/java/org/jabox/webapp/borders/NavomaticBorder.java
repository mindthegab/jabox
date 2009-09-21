package org.jabox.webapp.borders;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class NavomaticBorder extends Border {

	private static final long serialVersionUID = 3571282449732806300L;

	public NavomaticBorder(final String componentName) {
		super(componentName);
		List<ITab> tabs = new ArrayList<ITab>();
		tabs.add(new AbstractTab(new Model<String>("A.L.M. (Jabox)")) {
			private static final long serialVersionUID = 1L;
			public Panel getPanel(String panelId) {
				return new TabPanel1(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model<String>("B.T.S. (Redmine)")) {
			private static final long serialVersionUID = 1L;
			public Panel getPanel(String panelId) {
				return new TabPanel4(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model<String>("C.I.S. (Hudson)")) {
			private static final long serialVersionUID = 1L;
			public Panel getPanel(String panelId) {
				return new TabPanel3(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model<String>("R.M.S. (Nexus)")) {
			private static final long serialVersionUID = 1L;
			public Panel getPanel(String panelId) {
				return new TabPanel2(panelId);
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
		private static final long serialVersionUID = -2139796431114958720L;

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
		private static final long serialVersionUID = -4644495598258865618L;

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
		private static final long serialVersionUID = -117473298219614032L;

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

	/**
	 * Panel representing the content panel for the third tab
	 * 
	 * @author Igor Vaynberg (ivaynberg)
	 * 
	 */
	private static class TabPanel4 extends Panel {
		private static final long serialVersionUID = 2733190513603447531L;

		/**
		 * Constructor
		 * 
		 * @param id
		 *            component id
		 */
		public TabPanel4(String id) {
			super(id);
		}

	};
}