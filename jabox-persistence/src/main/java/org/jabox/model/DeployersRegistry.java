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
package org.jabox.model;

import java.util.List;
import java.util.Set;

import org.jabox.apis.Connector;

import com.google.inject.Inject;

public class DeployersRegistry implements IDeployersRegistry {

	private final Set<Connector> _connectors;

	@Inject
	public DeployersRegistry(Set<Connector> connectors) {
		this._connectors = connectors;
	}

	public Connector getEntry(String pluginId) {
		for (Connector connector : _connectors) {
			if (connector.getId().equals(pluginId)) {
				return connector;
			}
		}
		return null;
	}

	public List<? extends String> getIds(
			Class<? extends Connector> connectorClass) {
		// XXX TODO
		return null;
	}
}
