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
}