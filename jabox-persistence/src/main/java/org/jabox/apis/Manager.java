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

	public void setConnectors(T[] connectors) {
		_connectors = connectors;
	}

	public T getConnectorInstance(String connector) {
		List<T> connectors = Arrays.asList(_connectors);

		if (connector == null) {
			return null;
		}

		for (T connectorInstance : connectors) {
			if (connector.equals(connectorInstance.toString())) {
				return connectorInstance;
			}
		}
		return null;
	}

}
