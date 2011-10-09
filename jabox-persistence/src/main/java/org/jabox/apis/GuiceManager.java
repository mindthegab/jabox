package org.jabox.apis;

import java.util.Set;

import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;

import com.google.inject.Inject;

public class GuiceManager {

	private final Set<ITSConnector> _connectors;

	@Inject
	public GuiceManager(Set<ITSConnector> connectors) {
		this._connectors = connectors;
	}

	public void getConnectors() {
		for (ITSConnector<ITSConnectorConfig> connector : _connectors) {
			System.out.println("CONNECTOR: " + connector.getName());
		}
	}
}
