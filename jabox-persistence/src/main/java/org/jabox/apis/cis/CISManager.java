package org.jabox.apis.cis;

import java.util.Arrays;
import java.util.List;

import org.jabox.apis.bts.BTSConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CISManager {
	@Autowired
	public CISConnector[] _cisConnectors;

	public CISConnector[] getCisConnectors() {
		return _cisConnectors;
	}

	public void setCisConnectors(CISConnector[] cisConnectors) {
		_cisConnectors = cisConnectors;
	}

	public CISConnector getCISConnectorInstance(String cisConnector) {
		List<CISConnector> cisConnectors = Arrays.asList(_cisConnectors);

		if (cisConnector == null) {
			return null;
		}

		for (CISConnector cisConnectorInstance : cisConnectors) {
			if (cisConnector.equals(cisConnectorInstance.toString())) {
				return cisConnectorInstance;
			}
		}
		return null;
	}
}
