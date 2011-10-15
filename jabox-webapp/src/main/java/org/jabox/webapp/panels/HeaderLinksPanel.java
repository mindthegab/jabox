/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.webapp.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.jabox.apis.Connector;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.IManager;
import org.jabox.model.DefaultConfiguration;
import org.jabox.webapp.pages.server.ManageServers;
import org.jabox.webapp.pages.tabs.CisPage;
import org.jabox.webapp.pages.tabs.CqmPage;
import org.jabox.webapp.pages.tabs.ItsPage;
import org.jabox.webapp.pages.tabs.RmsPage;
import org.jabox.webapp.pages.tabs.ScmPage;

import com.google.inject.Inject;

public class HeaderLinksPanel extends Panel {
	private static final long serialVersionUID = 9038957597332426470L;

	public static final int ALM = 0;
	public static final int SCM = 1;
	public static final int ITS = 2;
	public static final int CIS = 3;
	public static final int RMS = 4;
	public static final int CQM = 5;

	@Inject
	protected IManager _manager;

	public HeaderLinksPanel() {
		this("headerLinks",0);
	}

	public HeaderLinksPanel(final String id, final int selected) {
		super(id);
		final DefaultConfiguration dc = ConfigXstreamDao.getConfig();

		List<Tab> tabs = new ArrayList<Tab>();
		tabs
				.add(new Tab("A.L.M. (Jabox)", ManageServers.class,
						selected == ALM));

		if (dc.getScm() != null) {
			tabs.add(new Tab(getTabName("S.C.M.", dc.getScm()), ScmPage.class,
					dc.getScm().getServer().getUrl(), selected == SCM));
		}

		if (dc.getIts() != null) {
			tabs.add(new Tab(getTabName("I.T.S.", dc.getIts()), ItsPage.class,
					dc.getIts().getServer().getUrl(), selected == ITS));
		}

		if (dc.getCis() != null) {
			tabs.add(new Tab(getTabName("C.I.S.", dc.getCis()), CisPage.class,
					dc.getCis().getServer().getUrl(), selected == CIS));
		}

		if (dc.getRms() != null) {
			tabs.add(new Tab(getTabName("R.M.S.", dc.getRms()), RmsPage.class,
					dc.getRms().getServer().getUrl(), selected == RMS));
		}

		if (dc.getCqm() != null) {
			tabs.add(new Tab(getTabName("C.Q.M.", dc.getCqm()), CqmPage.class,
					dc.getCqm().getServer().getUrl(), selected == CQM));
		}

		add(new TabsList("tabs", tabs));
	}

	/**
	 * 
	 * @param prefix
	 *            The prefix of the name
	 * @param cc
	 *            The ConnectorConfig
	 * @return
	 */
	private String getTabName(final String prefix, final ConnectorConfig cc) {
		Connector ci = _manager.getConnectorInstance(cc);
		return prefix + "(" + ci.getName() + ")";
	}

}
