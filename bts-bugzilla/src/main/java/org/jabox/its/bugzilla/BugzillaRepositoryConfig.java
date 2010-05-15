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
package org.jabox.its.bugzilla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Project;

@Entity
@DiscriminatorValue(BugzillaRepository.ID)
public class BugzillaRepositoryConfig extends DeployerConfig implements
		ITSConnectorConfig {
	private static final long serialVersionUID = -4830931405902901630L;

	public BugzillaRepositoryConfig() {
		pluginId = BugzillaRepository.ID;
	}

	public String getProjectUrl(Project project) {
		return getServer().getUrl();
	}

	public String getSystem() {
		return "Bugzilla";
	}
	
	public String username;

	public String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
