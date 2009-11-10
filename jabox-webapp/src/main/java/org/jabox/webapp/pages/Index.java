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
package org.jabox.webapp.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.webapp.borders.NavomaticBorder;
import org.jabox.webapp.borders.TabPanel1;
import org.jabox.webapp.borders.TabPanel2;
import org.jabox.webapp.borders.TabPanel3;
import org.jabox.webapp.borders.TabPanel4;

public class Index extends BasePage {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public Index() {
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
