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
package org.jabox.scm.beanstalk;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.wicket.model.IModel;
import org.jabox.model.DeployerConfig;
import org.jabox.scm.svn.ISVNConnectorConfig;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Entity
@DiscriminatorValue(BeanstalkConnector.ID)
public class BeanstalkConnectorConfig extends DeployerConfig implements
		ISVNConnectorConfig {
	private static final String SVN_BEANSTALKAPP_COM = ".svn.beanstalkapp.com/";

	private static final String HTTP = "http://";

	private static final long serialVersionUID = -830757629457448866L;

	public BeanstalkConnectorConfig() {
		pluginId = BeanstalkConnector.ID;
	}

	public String username;

	public String password;

	public String projectName;

	public SVNURL getSvnDir() throws SVNException {
		return SVNURL.parseURIEncoded(getScmUrl());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAccountURL() {
		IModel<String> baModel = new BeanstalkAccountURLModel(getServer()
				.getUrl());
		return baModel.getObject();
	}

	public String getScmUrl() {
		String scmURL = HTTP + getAccountURL() + SVN_BEANSTALKAPP_COM
				+ getProjectName();
		return scmURL;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getScmMavenUrl() {
		return "scm:svn:" + getScmUrl();
	}
}
