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
package org.jabox.webapp.pages.server;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.apache.wicket.persistence.provider.ServerXstreamDao;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.apis.cqm.CQMConnector;
import org.jabox.apis.cqm.CQMConnectorConfig;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.apis.rms.RMSConnector;
import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.webapp.menubuttons.InfoImage;
import org.jabox.webapp.pages.BasePage;
import org.jabox.webapp.utils.SCMConnectorList;

/**
 * Homepage
 */
@AuthorizeInstantiation("ADMIN")
public class ManageServers extends BasePage {

	private static final long serialVersionUID = 1L;

	public ManageServers() {
		Form<BaseEntity> form = new Form<BaseEntity>("deleteForm");

		form.add(createList("SCMs", SCMConnectorConfig.class));
		form.add(new CreateServerLink("createSCMs", SCMConnector.class));
		form.add(new InfoImage("scmIcon", ""));

		form.add(createList("RMSs", RMSConnectorConfig.class));
		form.add(new CreateServerLink("createRMSs", RMSConnector.class));
		form.add(new InfoImage("rmsIcon", ""));

		form.add(createList("CISs", CISConnectorConfig.class));
		form.add(new CreateServerLink("createCISs", CISConnector.class));
		form.add(new InfoImage("cisIcon", ""));

		form.add(createList("ITSs", ITSConnectorConfig.class));
		form.add(new CreateServerLink("createITSs", ITSConnector.class));
		form.add(new InfoImage("itsIcon", ""));

		form.add(createList("CQMs", CQMConnectorConfig.class));
		form.add(new CreateServerLink("createCQMs", CQMConnector.class));
		form.add(new InfoImage("cqmIcon", ""));

		add(form);
	}

	private SCMConnectorList createList(final String key,
			final Class<? extends ConnectorConfig> clas) {
		return new SCMConnectorList(key, ServerXstreamDao.getServers(clas));
	}
}
