package org.jabox.apis.scm;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SCMManager {
	@Autowired
	public SCMConnector[] _scmConnectors;

	public SCMConnector[] getScmConnectors() {
		return _scmConnectors;
	}

	public void setScmConnectors(SCMConnector[] scmConnectors) {
		_scmConnectors = scmConnectors;
	}
	
	public SCMConnector getSCMConnectorInstance(String scmConnector) {
		List<SCMConnector> scmConnectors = Arrays.asList(_scmConnectors);

		if (scmConnector == null) {
			return null;
		}

		for (SCMConnector scmConnectorInstance : scmConnectors) {
			if (scmConnector.equals(scmConnectorInstance.toString())) {
				return scmConnectorInstance;
			}
		}
		return null;
	}

}
