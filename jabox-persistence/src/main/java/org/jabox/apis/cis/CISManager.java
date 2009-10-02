package org.jabox.apis.cis;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CISManager {
	@Autowired
	public CISConnector[] _connectors;

	public CISConnector[] getCisConnectors() {
		return _connectors;
	}

	public void setConnectors(CISConnector[] connectors) {
		_connectors = connectors;
	}

	public CISConnector getConnectorInstance(String connector) {
		List<CISConnector> connectors = Arrays.asList(_connectors);

		if (connector == null) {
			return null;
		}

		for (CISConnector connectorInstance : connectors) {
			if (connector.equals(connectorInstance.toString())) {
				return connectorInstance;
			}
		}
		return null;
	}
}
