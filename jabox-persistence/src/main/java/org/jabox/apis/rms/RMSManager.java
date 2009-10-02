package org.jabox.apis.rms;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RMSManager {
	@Autowired
	public RMSConnector[] _connectors;

	public RMSConnector[] getConnectors() {
		return _connectors;
	}

	public void setConnectors(RMSConnector[] connectors) {
		_connectors = connectors;
	}

	public RMSConnector getConnectorInstance(String connector) {
		List<RMSConnector> connectors = Arrays.asList(_connectors);

		if (connector == null) {
			return null;
		}

		for (RMSConnector connectorInstance : connectors) {
			if (connector.equals(connectorInstance.toString())) {
				return connectorInstance;
			}
		}
		return null;
	}
}
