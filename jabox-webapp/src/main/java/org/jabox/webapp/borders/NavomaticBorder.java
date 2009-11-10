/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.webapp.borders;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
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
		add(new AjaxTabbedPanel("tabs", tabs));
	}
}
