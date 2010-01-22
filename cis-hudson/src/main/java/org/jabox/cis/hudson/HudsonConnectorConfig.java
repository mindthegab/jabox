package org.jabox.cis.hudson;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(HudsonConnector.ID)
public class HudsonConnectorConfig extends DeployerConfig implements
		CISConnectorConfig {
	private static final long serialVersionUID = -6696934779273872749L;

	public HudsonConnectorConfig() {
		pluginId = HudsonConnector.ID;
	}
}
