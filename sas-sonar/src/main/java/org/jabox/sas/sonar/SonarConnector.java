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

package org.jabox.sas.sonar;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.apis.cqm.CQMConnector;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.xml.sax.SAXException;

public class SonarConnector implements CQMConnector<SonarConnectorConfig>,
		Serializable {
	private static final long serialVersionUID = 8131183843391948936L;
	public static final String ID = "plugin.cqm.sonar";

	public String getName() {
		return "Sonar";
	}

	public String getId() {
		return ID;
	}

	@Override
	public String toString() {
		return getName();
	}

	public SonarConnector() {
	}

	public DeployerConfig newConfig() {
		return new SonarConnectorConfig();
	}

	public Component newEditor(final String id, final IModel<Server> model) {
		return new SonarConnectorEditor(id, model);
	}

	public void addRepository(Project project, SonarConnectorConfig config,
			SCMConnectorConfig scmConfig, String username, String password)
			throws MalformedURLException, IOException, SAXException {
	}
}
