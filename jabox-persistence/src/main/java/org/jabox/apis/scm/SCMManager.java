package org.jabox.apis.scm;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SCMManager {
	@Autowired
	public SCMConnector[] _connectors;

	public SCMConnector[] getConnectors() {
		return _connectors;
	}

	public void setConnectors(SCMConnector[] connectors) {
		_connectors = connectors;
	}
	
	public SCMConnector getConnectorInstance(String connector) {
		List<SCMConnector> connectors = Arrays.asList(_connectors);

		if (connector == null) {
			return null;
		}

		for (SCMConnector connectorInstance : connectors) {
			if (connector.equals(connectorInstance.toString())) {
				return connectorInstance;
			}
		}
		return null;
	}

}
