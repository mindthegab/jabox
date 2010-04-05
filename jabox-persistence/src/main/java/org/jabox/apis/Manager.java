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
package org.jabox.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager<T extends Connector> {
	@Autowired
	public T[] _connectors;

	public List<T> getConnectors(final Class<? extends T> claz) {
		List<T> connectors = Arrays.asList(_connectors);

		List<T> shoppingCart = new ArrayList<T>();
		for (T connector : connectors) {
			if (claz.isAssignableFrom(connector.getClass())) {
				shoppingCart.add(connector);
			}
		}
		return shoppingCart;
	}

	public void setConnectors(final T[] connectors) {
		_connectors = connectors;
	}

	public T getConnectorInstance(final ConnectorConfig connectorConfig) {
		List<T> connectors = Arrays.asList(_connectors);

		if (connectorConfig == null) {
			return null;
		}

		for (T connectorInstance : connectors) {
			if (connectorConfig.getPluginId().equals(connectorInstance.getId())) {
				return connectorInstance;
			}
		}
		return null;
	}

	public T getConnectorInstance(final String connectorName) {
		List<T> connectors = Arrays.asList(_connectors);

		if (connectorName == null) {
			return null;
		}

		for (T connectorInstance : connectors) {
			if (connectorName.equals(connectorInstance.getName())) {
				return connectorInstance;
			}
		}
		return null;
	}

}
