package org.jabox.apis.bts;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BTSManager {
	@Autowired
	public BTSConnector[] _btsConnectors;

	public BTSConnector[] getBtsConnectors() {
		return _btsConnectors;
	}

	public void setBtsConnectors(BTSConnector[] btsConnectors) {
		_btsConnectors = btsConnectors;
	}
	
	public BTSConnector getBTSConnectorInstance(String btsConnector) {
		List<BTSConnector> btsConnectors = Arrays.asList(_btsConnectors);

		if (btsConnector == null) {
			return null;
		}

		for (BTSConnector btsConnectorInstance : btsConnectors) {
			if (btsConnector.equals(btsConnectorInstance.toString())) {
				return btsConnectorInstance;
			}
		}
		return null;
	}

}
