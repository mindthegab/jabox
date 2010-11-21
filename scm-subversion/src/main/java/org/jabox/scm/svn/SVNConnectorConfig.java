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
package org.jabox.scm.svn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.model.DeployerConfig;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Entity
@DiscriminatorValue(SVNConnector.ID)
public class SVNConnectorConfig extends DeployerConfig implements
		ISVNConnectorConfig {
	private static final long serialVersionUID = 6542402958304063770L;

	public SVNConnectorConfig() {
		pluginId = SVNConnector.ID;
	}

	public String username;

	public String password;

	public SVNURL getSvnDir() throws SVNException {
		return SVNURL.parseURIEncoded(getScmUrl());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getScmUrl() {
		return getServer().getUrl();
	}

	public String getProjectScmUrl(String projectName) {
		return getScmUrl() + "/" + projectName + "/trunk/" + projectName;
	}

	@Override
	public String getScmMavenPrefix() {
		return "scm:svn:";
	}
}
