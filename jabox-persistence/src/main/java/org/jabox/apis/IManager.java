package org.jabox.apis;

import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.apis.cqm.CQMConnector;
import org.jabox.apis.cqm.CQMConnectorConfig;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.apis.rms.RMSConnector;
import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.apis.scm.SCMConnectorConfig;

public interface IManager {

	public abstract Connector getConnectorInstance(
			final ConnectorConfig connectorConfig);

	public abstract SCMConnector<SCMConnectorConfig> getScmConnectorInstance(
			SCMConnectorConfig config);

	public abstract ITSConnector<ITSConnectorConfig> getItsConnectorInstance(
			ITSConnectorConfig config);

	public abstract RMSConnector getRmsConnectorInstance(
			RMSConnectorConfig config);

	public abstract CISConnector getCisConnectorInstance(
			CISConnectorConfig config);

	public abstract CQMConnector getCqmConnectorInstance(
			CQMConnectorConfig config);

}