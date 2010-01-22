package org.jabox.mrm.nexus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(NexusConnector.ID)
public class NexusConnectorConfig extends DeployerConfig implements
		RMSConnectorConfig {
	private static final long serialVersionUID = 7792258345940117969L;

	public NexusConnectorConfig() {
		pluginId = NexusConnector.ID;
	}
}
