/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jabox.model.DeployerConfig;
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

	public void setConnectors(T[] connectors) {
		_connectors = connectors;
	}

	public T getConnectorInstance(ConnectorConfig connectorConfig) {
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

	public T getConnectorInstance(String connectorName) {
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
