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
package org.jabox.cis.hudson;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(HudsonConnector.ID)
public class HudsonConnectorConfig extends DeployerConfig implements
		CISConnectorConfig {
	private static final long serialVersionUID = -6696934779273872749L;

	public String username;

	public String password;

	public HudsonConnectorConfig() {
		pluginId = HudsonConnector.ID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
