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
package org.jabox.apis.its;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.apis.Connector;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.Project;
import org.xml.sax.SAXException;

public interface ITSConnector<T extends ITSConnectorConfig> extends Connector {

	public boolean login(String username, String password, T config)
			throws MalformedURLException, IOException, SAXException;

	public boolean addProject(final Project project, T itsConnectorConfig)
			throws IOException, SAXException;

	public boolean addModule(final Project project, T itsConnectorConfig,
			final String module, final String description,
			final String initialOwner) throws SAXException, IOException;

	public boolean addVersion(Project project, T itsConnectorConfig,
			String version) throws IOException, SAXException;

	public void addRepository(Project project, T config,
			SCMConnectorConfig scmConfig, String username, String password)
			throws MalformedURLException, IOException, SAXException;
}
