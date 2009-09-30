package org.jabox.apis.rms;

import java.util.Arrays;
import java.util.List;

import org.jabox.apis.bts.BTSConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RMSManager {
	@Autowired
	public RMSConnector[] _rmsConnectors;

	public RMSConnector[] getRmsConnectors() {
		return _rmsConnectors;
	}

	public void setRmsConnectors(RMSConnector[] rmsConnectors) {
		_rmsConnectors = rmsConnectors;
	}

	public RMSConnector getRMSConnectorInstance(String rmsConnector) {
		List<RMSConnector> rmsConnectors = Arrays.asList(_rmsConnectors);

		if (rmsConnector == null) {
			return null;
		}

		for (RMSConnector rmsConnectorInstance : rmsConnectors) {
			if (rmsConnector.equals(rmsConnectorInstance.toString())) {
				return rmsConnectorInstance;
			}
		}
		return null;
	}
}
