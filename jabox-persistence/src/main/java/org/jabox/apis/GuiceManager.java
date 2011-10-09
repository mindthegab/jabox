package org.jabox.apis;

import java.util.Set;

import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;

import com.google.inject.Inject;

public class GuiceManager {

	private final Set<ITSConnector> _itsConnectors;

	private final Set<CISConnector> _cisConnectors;

	@Inject
	public GuiceManager(Set<ITSConnector> its, Set<CISConnector> cis) {
		this._itsConnectors = its;
		this._cisConnectors = cis;
	}

	public void getConnectors() {
		for (ITSConnector<ITSConnectorConfig> connector : _itsConnectors) {
			System.out.println("ITS: " + connector.getName());
		}
		for (CISConnector connector : _cisConnectors) {
			System.out.println("CIS: " + connector.getName());
		}
	}
}
