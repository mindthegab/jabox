package org.jabox.apis.bts;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BTSManager {
	@Autowired
	public BTSConnector[] _connectors;

	public BTSConnector[] getBtsConnectors() {
		return _connectors;
	}

	public void setConnectors(BTSConnector[] btsConnectors) {
		_connectors = btsConnectors;
	}
	
	public BTSConnector getConnectorInstance(String connector) {
		List<BTSConnector> connectors = Arrays.asList(_connectors);

		if (connector == null) {
			return null;
		}

		for (BTSConnector connectorInstance : connectors) {
			if (connector.equals(connectorInstance.toString())) {
				return connectorInstance;
			}
		}
		return null;
	}

}
